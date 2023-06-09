package com.yunce.interfaces.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yunce.common.core.annotation.Excel;
import com.yunce.common.core.web.domain.BaseEntity;

/**
 * 接口组对象 int_group
 *
 * @author linjunfa
 * @date 2023-06-07
 */
public class IntGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private Long id;

    /** 组名 */
    @Excel(name = "组名")
    private String groupName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("group", getGroupName())
            .toString();
    }
}
