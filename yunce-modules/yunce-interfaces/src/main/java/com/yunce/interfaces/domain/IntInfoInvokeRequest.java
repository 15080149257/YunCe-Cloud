package com.yunce.interfaces.domain;

import com.yunce.common.core.annotation.Excel;
import com.yunce.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

/**
 * 接口调用请求
 *
 * @author 林俊发
 * @date 2023-06-07
 */
public class IntInfoInvokeRequest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    @NotBlank(message = "接口参数不能为空")
    private String userRequestParams;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRequestParams() {
        return userRequestParams;
    }

    public void setUserRequestParams(String userRequestParams) {
        this.userRequestParams = userRequestParams;
    }
}
