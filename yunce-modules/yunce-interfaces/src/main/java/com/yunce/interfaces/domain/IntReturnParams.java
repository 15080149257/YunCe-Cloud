package com.yunce.interfaces.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yunce.common.core.annotation.Excel;
import com.yunce.common.core.web.domain.BaseEntity;

/**
 * 返回类型对象 int_return_params
 *
 * @author linjunfa
 * @date 2023-06-08
 */
public class IntReturnParams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数说明 */
    @Excel(name = "参数说明")
    private String paramsDesc;

    /** 数据类型 */
    @Excel(name = "数据类型")
    private String type;

    /** 参数名称 */
    @Excel(name = "参数名称")
    private String paramsName;

    /** 关联id */
    @Excel(name = "关联id")
    private Long infoId;

    /** 主键id */
    private Long id;
    private boolean isEditing;

    public boolean getIsEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public void setParamsDesc(String paramsDesc)
    {
        this.paramsDesc = paramsDesc;
    }

    public String getParamsDesc()
    {
        return paramsDesc;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setParamsName(String paramsName)
    {
        this.paramsName = paramsName;
    }

    public String getParamsName()
    {
        return paramsName;
    }
    public void setInfoId(Long infoId)
    {
        this.infoId = infoId;
    }

    public Long getInfoId()
    {
        return infoId;
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
            .append("paramsDesc", getParamsDesc())
            .append("type", getType())
            .append("paramsName", getParamsName())
            .append("infoId", getInfoId())
            .append("id", getId())
            .toString();
    }
}
