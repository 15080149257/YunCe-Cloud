package com.yunce.interfaces.service;


import com.yunce.interfaces.controller.IntInfoController;
import com.yunce.interfaces.domain.IntInfo;
import com.yunce.interfaces.domain.IntUserInfo;
import com.yunce.interfaces.service.impl.IntUserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.zip.Inflater;

@SpringBootTest
public class IntUserInfoService {
    @Autowired
    private IntUserInfoServiceImpl intUserInfoService;

    @Autowired
    private IntInfoController intInfoController;

    @Test
    public void invokeCount(){
        boolean b = intUserInfoService.invokeCount(11L, 1L);
        System.err.println(b);
    }

    @Test
    public void selectIntUserInfoById(){
        IntUserInfo intUserInfo = intUserInfoService.selectIntUserInfoById(1L);

    }

    @Test
    public void txt(){
        IntInfo intInfo = new IntInfo();
        intInfo.setMethod("POST");
        intInfo.setUrl("http://127.0.0.1:8080/api/name/user");
        intInfoController.judge(intInfo);

    }
}
