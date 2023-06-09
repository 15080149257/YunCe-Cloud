package com.yunce.interfaces.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunce.interfaces.mapper.IntGroupMapper;
import com.yunce.interfaces.domain.IntGroup;
import com.yunce.interfaces.service.IIntGroupService;

/**
 * 接口组Service业务层处理
 *
 * @author linjunfa
 * @date 2023-06-07
 */
@Service
public class IntGroupServiceImpl implements IIntGroupService
{
    @Autowired
    private IntGroupMapper intGroupMapper;

    /**
     * 查询接口组
     *
     * @param id 接口组主键
     * @return 接口组
     */
    @Override
    public IntGroup selectIntGroupById(Long id)
    {
        return intGroupMapper.selectIntGroupById(id);
    }

    /**
     * 查询接口组列表
     *
     * @param intGroup 接口组
     * @return 接口组
     */
    @Override
    public List<IntGroup> selectIntGroupList(IntGroup intGroup)
    {
        return intGroupMapper.selectIntGroupList(intGroup);
    }

    /**
     * 新增接口组
     *
     * @param intGroup 接口组
     * @return 结果
     */
    @Override
    public int insertIntGroup(IntGroup intGroup)
    {
        return intGroupMapper.insertIntGroup(intGroup);
    }

    /**
     * 修改接口组
     *
     * @param intGroup 接口组
     * @return 结果
     */
    @Override
    public int updateIntGroup(IntGroup intGroup)
    {
        return intGroupMapper.updateIntGroup(intGroup);
    }

    /**
     * 批量删除接口组
     *
     * @param ids 需要删除的接口组主键
     * @return 结果
     */
    @Override
    public int deleteIntGroupByIds(Long[] ids)
    {
        return intGroupMapper.deleteIntGroupByIds(ids);
    }

    /**
     * 删除接口组信息
     *
     * @param id 接口组主键
     * @return 结果
     */
    @Override
    public int deleteIntGroupById(Long id)
    {
        return intGroupMapper.deleteIntGroupById(id);
    }
}
