package com.yunce.gateway.feign;

import com.yunce.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("yunce-system")
public interface SysUserFignService {
    @GetMapping("/user/getKey/{appKey}")
    public AjaxResult getKey(@PathVariable("appKey") String appKey);
}
