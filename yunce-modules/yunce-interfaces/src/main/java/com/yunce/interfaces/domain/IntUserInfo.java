package com.yunce.interfaces.domain;

import com.yunce.common.core.group.Type;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yunce.common.core.annotation.Excel;
import com.yunce.common.core.web.domain.BaseEntity;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * 用户调用接口关系对象 int_user_info
 *
 * @author linjunfa
 * @date 2023-06-08
 */
public class IntUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 接口id */
    @NotBlank(message = "接口id不能为空")
    @Excel(name = "接口id")
    private Long infoId;

    /** 总调用次数 */
    @NotBlank(message = "总调用次数不能为空")
    @Excel(name = "总调用次数")
    private Long totalNum;

    /** 状态 0-启用 1-停用 */
    @Excel(name = "状态 0-启用 1-停用")
    private Long status;

    /** 是否删除 Y是 N否 */
    @Excel(name = "是否删除 Y是 N否")
    private String isDelect;

    /** 剩余调用次数 */
    @NotBlank(message = "剩余调用次数不能为空", groups = {Type.Add.class, Type.Edit.class})
    @Excel(name = "剩余调用次数")
    private Long leftNum;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setInfoId(Long infoId)
    {
        this.infoId = infoId;
    }

    public Long getInfoId()
    {
        return infoId;
    }
    public void setTotalNum(Long totalNum)
    {
        this.totalNum = totalNum;
    }

    public Long getTotalNum()
    {
        return totalNum;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setIsDelect(String isDelect)
    {
        this.isDelect = isDelect;
    }

    public String getIsDelect()
    {
        return isDelect;
    }
    public void setLeftNum(Long leftNum)
    {
        this.leftNum = leftNum;
    }

    public Long getLeftNum()
    {
        return leftNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("infoId", getInfoId())
            .append("totalNum", getTotalNum())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("isDelect", getIsDelect())
            .append("leftNum", getLeftNum())
            .toString();
    }
}
