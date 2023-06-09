package com.yunce.gateway.feign;

import com.yunce.common.core.web.domain.AjaxResult;
import com.yunce.gateway.domain.IntInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient("yunce-interfaces")
public interface IntInfoFeignService {
    @GetMapping("/info/judge")
    public AjaxResult judge(@RequestBody IntInfo intInfo);
}
