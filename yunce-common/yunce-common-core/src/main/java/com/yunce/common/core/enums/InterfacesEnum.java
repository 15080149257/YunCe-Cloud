package com.yunce.common.core.enums;

public enum InterfacesEnum {
    open("开启","0"),
    close("关闭","1");


    private final String text;
    private final String value;

    InterfacesEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
