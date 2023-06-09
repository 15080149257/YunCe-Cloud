package com.yunce.interfaces.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunce.interfaces.mapper.IntRequestParamsMapper;
import com.yunce.interfaces.domain.IntRequestParams;
import com.yunce.interfaces.service.IIntRequestParamsService;

/**
 * 请求参数Service业务层处理
 *
 * @author linjunfa
 * @date 2023-06-08
 */
@Service
public class IntRequestParamsServiceImpl implements IIntRequestParamsService
{
    @Autowired
    private IntRequestParamsMapper intRequestParamsMapper;

    /**
     * 查询请求参数
     *
     * @param id 请求参数主键
     * @return 请求参数
     */
    @Override
    public List<IntRequestParams> selectIntRequestParamsById(Long id)
    {
        return intRequestParamsMapper.selectIntRequestParamsById(id);
    }

    /**
     * 查询请求参数列表
     *
     * @param intRequestParams 请求参数
     * @return 请求参数
     */
    @Override
    public List<IntRequestParams> selectIntRequestParamsList(IntRequestParams intRequestParams)
    {
        return intRequestParamsMapper.selectIntRequestParamsList(intRequestParams);
    }

    /**
     * 新增请求参数
     *
     * @param intRequestParams 请求参数
     * @return 结果
     */
    @Override
    public int insertIntRequestParams(IntRequestParams intRequestParams)
    {
        return intRequestParamsMapper.insertIntRequestParams(intRequestParams);
    }

    /**
     * 修改请求参数
     *
     * @param intRequestParams 请求参数
     * @return 结果
     */
    @Override
    public int updateIntRequestParams(IntRequestParams intRequestParams)
    {
        return intRequestParamsMapper.updateIntRequestParams(intRequestParams);
    }

    /**
     * 批量删除请求参数
     *
     * @param ids 需要删除的请求参数主键
     * @return 结果
     */
    @Override
    public int deleteIntRequestParamsByIds(Long[] ids)
    {
        return intRequestParamsMapper.deleteIntRequestParamsByIds(ids);
    }

    /**
     * 删除请求参数信息
     *
     * @param id 请求参数主键
     * @return 结果
     */
    @Override
    public int deleteIntRequestParamsById(Long id)
    {
        return intRequestParamsMapper.deleteIntRequestParamsById(id);
    }
}
