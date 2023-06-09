package com.yunce.interfaces.service;

import java.util.List;
import com.yunce.interfaces.domain.IntGroup;

/**
 * 接口组Service接口
 * 
 * @author linjunfa
 * @date 2023-06-07
 */
public interface IIntGroupService 
{
    /**
     * 查询接口组
     * 
     * @param id 接口组主键
     * @return 接口组
     */
    public IntGroup selectIntGroupById(Long id);

    /**
     * 查询接口组列表
     * 
     * @param intGroup 接口组
     * @return 接口组集合
     */
    public List<IntGroup> selectIntGroupList(IntGroup intGroup);

    /**
     * 新增接口组
     * 
     * @param intGroup 接口组
     * @return 结果
     */
    public int insertIntGroup(IntGroup intGroup);

    /**
     * 修改接口组
     * 
     * @param intGroup 接口组
     * @return 结果
     */
    public int updateIntGroup(IntGroup intGroup);

    /**
     * 批量删除接口组
     * 
     * @param ids 需要删除的接口组主键集合
     * @return 结果
     */
    public int deleteIntGroupByIds(Long[] ids);

    /**
     * 删除接口组信息
     * 
     * @param id 接口组主键
     * @return 结果
     */
    public int deleteIntGroupById(Long id);
}
