package com.yunce.interfaces.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yunce.common.core.annotation.Excel;
import com.yunce.common.core.web.domain.BaseEntity;

/**
 * 请求参数对象 int_request_params
 *
 * @author linjunfa
 * @date 2023-06-08
 */
public class IntRequestParams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数名称 */
    @Excel(name = "参数名称")
    private String paramsName;

    /** 参数说明 */
    @Excel(name = "参数说明")
    private String paramsDesc;

    /** 请求类型 */
    @Excel(name = "请求类型")
    private String paramsType;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String type;

    /** info关联id */
    @Excel(name = "info关联id")
    private Long infoId;

    /** 是否必须 */
    @Excel(name = "是否必须")
    private String must;

    /** 主键id */
    private Long id;
    private boolean isEditing;

    public boolean getIsEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public void setParamsName(String paramsName)
    {
        this.paramsName = paramsName;
    }

    public String getParamsName()
    {
        return paramsName;
    }
    public void setParamsDesc(String paramsDesc)
    {
        this.paramsDesc = paramsDesc;
    }

    public String getParamsDesc()
    {
        return paramsDesc;
    }
    public void setParamsType(String paramsType)
    {
        this.paramsType = paramsType;
    }

    public String getParamsType()
    {
        return paramsType;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setInfoId(Long infoId)
    {
        this.infoId = infoId;
    }

    public Long getInfoId()
    {
        return infoId;
    }
    public void setMust(String must)
    {
        this.must = must;
    }

    public String getMust()
    {
        return must;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("paramsName", getParamsName())
            .append("paramsDesc", getParamsDesc())
            .append("paramsType", getParamsType())
            .append("type", getType())
            .append("infoId", getInfoId())
            .append("must", getMust())
            .append("id", getId())
            .toString();
    }
}
