package com.yunce.gateway.filter;

import com.yunce.api.utils.SignUtils;
import com.yunce.common.core.exception.CheckedException;
import com.yunce.common.core.exception.PreAuthorizeException;
import com.yunce.common.core.utils.FilterUtils;
import com.yunce.common.core.utils.StringUtils;
import com.yunce.common.core.web.domain.AjaxResult;
import com.yunce.gateway.domain.IntInfo;
import com.yunce.gateway.feign.AutowiredBean;
import com.yunce.gateway.feign.IntInfoFeignService;
import com.yunce.gateway.feign.SysUserFignService;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 路由全局匹配过滤器
 */
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(CustomGlobalFilter.class);

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");        //3.设置白名单/黑名单(举例白名单)

    @Autowired
    @Lazy
    IntInfoFeignService intInfoFeignService;

    //1.用户发生请求到API网关
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2.记录请求日志
        logger.info("----------------------------------------------------------");
        logger.info("请求唯一标识:" + request.getId());
        logger.info("请求路径:" + request.getPath().value());
        HttpMethod method = request.getMethod();
        logger.info("请求方法:" + method);
        logger.info("请求参数:" + request.getQueryParams());
        RequestPath path = request.getPath();
        logger.info("请求来源路径:" + path);    //  /auth/login
        URI uri = request.getURI();
        logger.info("请求来源全路径:" + uri);    //  http://localhost:8080/auth/login
        logger.info("----------------------------------------------------------");

        /** 过滤特定的请求 /api/** */
        if (uri.getPath().startsWith("/api")) {
            String host = uri.getHost();     // 127.0.0.1
            logger.info("请求来源ip地址:" + host);
            //3.判断（黑）白名单中是否包含获取到的来源地址 todo 此处白名单后期需要做库，或者直接改为黑名单
            if (!IP_WHITE_LIST.contains(host)) {
                //3.当该ip不存在白名单中时设置响应返回的类型
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }

            //4.用户鉴权，判断ak,sk是否合法
            HttpHeaders headers = request.getHeaders();
            String appKey = headers.getFirst("appKey");
            String nonce = headers.getFirst("nonc");
            String timestamp = headers.getFirst("timestamp");
            String sign = headers.getFirst("sign");
            String body = headers.getFirst("body");
            //通过异步调用openFeign获取需要的
            SysUserFignService userBean = AutowiredBean.getBean(SysUserFignService.class);
            CompletableFuture<AjaxResult> feign = CompletableFuture.supplyAsync(() -> {
                        return userBean.getKey(appKey);
                    }
            );
            HashMap<String, String> userFeignGetKey = FilterUtils.getFuture(feign);
            /* -----------判断ak是否合法----------- */
            if (!appKey.equals(userFeignGetKey.get("appKey"))) throw new PreAuthorizeException();
            /* -----------判断随机数是否越界----------- */
            if (Long.parseLong(nonce) > 10000) throw new PreAuthorizeException();
            /* -----------判断是否超时----------- */
            long currentTime = System.currentTimeMillis() / 1000;
            Long FIVE_MINUTES = 60 * 5L;
            if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) throw new PreAuthorizeException();
            /* -----------判断根据sk生成的签名是否合法----------- */
            String serverSign = SignUtils.genSign(body, userFeignGetKey.get("appSecret"));
            if (!sign.equals(serverSign)) throw new PreAuthorizeException();
            IntInfo intInfo = new IntInfo();
            intInfo.setMethod(method.toString());
            intInfo.setUrl(uri.toString());
            //5.todo 请求的模拟接口是否存储在mysql中
//            IntInfoFeignService infoBean = AutowiredBean.getBean(IntInfoFeignService.class);
//            CompletableFuture<AjaxResult> info = CompletableFuture.supplyAsync(() -> {
//                        return infoBean.judge(intInfo);
//                    }
//            );
//            HashMap<String, String> infoFeignjude = FilterUtils.getFuture(info);
//            if (StringUtils.isEmpty(infoFeignjude)) throw new CheckedException("当前接口不存在");
        }
        //6.请求转发，调用模拟接口
//        Mono<Void> filter = chain.filter(exchange);

        //7.记录响应日志
        return handleResponse(exchange, chain);
    }


    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 处理响应
     */
    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            //获取缓存的数据工厂
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            //拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                //装饰者，增强能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    //等调用完转发后的接口才会调用
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        logger.info("body instanceof Flux: {}", (body instanceof Flux));
                        //判断当前对象是否是响应式的(Flux)
                        if (body instanceof Flux) {
                            //如果是响应式的，从Flux中拿到真正的body
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //  往返回值内部写入数据
                            return super.writeWith(
                                    fluxBody.map(dataBuffer -> {
                                        //8.调用成功，调用次数+1

                                        //拼接缓冲区代码
                                        byte[] content = new byte[dataBuffer.readableByteCount()];
                                        dataBuffer.read(content);
                                        DataBufferUtils.release(dataBuffer);//释放掉内存
                                        // 构建日志
                                        StringBuilder sb2 = new StringBuilder(200);
                                        List<Object> rspArgs = new ArrayList<>();
                                        rspArgs.add(originalResponse.getStatusCode());
                                        //rspArgs.add(requestUrl);
                                        String data = new String(content, StandardCharsets.UTF_8);  //接口返回值
                                        sb2.append(data);
                                        logger.info("响应结果：" + data);
                                        return bufferFactory.wrap(content);
                                    }));
                        } else {
                            //9.调用失败，返回一个规范的错误码
                            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

                            logger.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                //设置Response对象为装饰后的对象
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            logger.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }
}
