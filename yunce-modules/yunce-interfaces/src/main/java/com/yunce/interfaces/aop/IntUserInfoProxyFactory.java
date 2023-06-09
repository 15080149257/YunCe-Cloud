package com.yunce.interfaces.aop;

import com.yunce.interfaces.service.IIntUserInfoService;
import com.yunce.interfaces.service.impl.IntUserInfoServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//public class IntUserInfoProxyFactory {
//    private IntUserInfoServiceImpl intUserInfo=new IntUserInfoServiceImpl();
//
//    public IIntUserInfoService getIntUserInfoProxyObject(){
//        IIntUserInfoService intUserInfoService = (IIntUserInfoService) Proxy.newProxyInstance(
//                intUserInfo.getClass().getClassLoader(),
//                intUserInfo.getClass().getInterfaces(),
//                new InvocationHandler() {
//                    @Override
//                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                        return method.invoke(intUserInfo, args);
//                    }
//                });
//        return intUserInfoService;
//    }
//}
