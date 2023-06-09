package com.yunce.gateway.domain;

import com.yunce.common.core.annotation.Excel;
import com.yunce.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

/**
 * 接口信息对象 int_info
 *
 * @author 林俊发
 * @date 2023-06-07
 */
public class IntInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 名称 */
    @NotBlank(message = "接口名称不能为空")
    @Excel(name = "名称")
    private String name;

    /** 描述 */
    @NotBlank(message = "接口描述不能为空")
    @Excel(name = "接口描述")
    private String description;

    /** 接口地址 */
    @NotBlank(message = "接口地址不能为空")
    @Excel(name = "接口地址")
    private String url;

    /** 请求头 */
    @NotBlank(message = "请求头不能为空")
    @Excel(name = "请求头")
    private String requestHeader;

    /** 响应头 */
    @NotBlank(message = "响应头不能为空")
    @Excel(name = "响应头")
    private String responseHeader;

    /** 接口状态 0-关闭 1-开启 */
    @NotBlank(message = "接口状态不能为空")
    @Excel(name = "接口状态 0-关闭 1-开启")
    private String status;

    /** 请求类型 */
    @NotBlank(message = "请求类型不能为空")
    @Excel(name = "请求类型")
    private String method;

    /** 是否删除 Y是 N否 */
    private String isDelect;

    private Long groupId;
    private String requestParams;
    private String returnParams;

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public String getReturnParams() {
        return returnParams;
    }

    public void setReturnParams(String returnParams) {
        this.returnParams = returnParams;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setRequestHeader(String requestHeader)
    {
        this.requestHeader = requestHeader;
    }

    public String getRequestHeader()
    {
        return requestHeader;
    }
    public void setResponseHeader(String responseHeader)
    {
        this.responseHeader = responseHeader;
    }

    public String getResponseHeader()
    {
        return responseHeader;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getMethod()
    {
        return method;
    }
    public void setIsDelect(String isDelect)
    {
        this.isDelect = isDelect;
    }

    public String getIsDelect()
    {
        return isDelect;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("url", getUrl())
            .append("requestHeader", getRequestHeader())
            .append("responseHeader", getResponseHeader())
            .append("status", getStatus())
            .append("method", getMethod())
            .append("isDelect", getIsDelect())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
