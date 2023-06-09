package com.yunce.common.core.utils;

import com.yunce.common.core.exception.ServiceException;
import com.yunce.common.core.web.domain.AjaxResult;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class FilterUtils {
    public static HashMap<String, String> getFuture(CompletableFuture<AjaxResult> feign) {
        HashMap<String,String> data = (HashMap<String, String>) Optional.ofNullable(feign).map(item -> {
            try {
                return item.get().get("data");
            } catch (Exception e) {
                throw new ServiceException("调用接口失败，账户异常");
            }
        }).orElse(new HashMap<>());
        return data;
    }
}
