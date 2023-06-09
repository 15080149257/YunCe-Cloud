package com.yunce.interfaces.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunce.interfaces.mapper.IntReturnParamsMapper;
import com.yunce.interfaces.domain.IntReturnParams;
import com.yunce.interfaces.service.IIntReturnParamsService;

/**
 * 返回类型Service业务层处理
 *
 * @author linjunfa
 * @date 2023-06-08
 */
@Service
public class IntReturnParamsServiceImpl implements IIntReturnParamsService
{
    @Autowired
    private IntReturnParamsMapper intReturnParamsMapper;

    /**
     * 查询返回类型
     *
     * @param id 返回类型主键
     * @return 返回类型
     */
    @Override
    public List<IntReturnParams> selectIntReturnParamsById(Long id)
    {
        return intReturnParamsMapper.selectIntReturnParamsById(id);
    }

    /**
     * 查询返回类型列表
     *
     * @param intReturnParams 返回类型
     * @return 返回类型
     */
    @Override
    public List<IntReturnParams> selectIntReturnParamsList(IntReturnParams intReturnParams)
    {
        return intReturnParamsMapper.selectIntReturnParamsList(intReturnParams);
    }

    /**
     * 新增返回类型
     *
     * @param intReturnParams 返回类型
     * @return 结果
     */
    @Override
    public int insertIntReturnParams(IntReturnParams intReturnParams)
    {
        return intReturnParamsMapper.insertIntReturnParams(intReturnParams);
    }

    /**
     * 修改返回类型
     *
     * @param intReturnParams 返回类型
     * @return 结果
     */
    @Override
    public int updateIntReturnParams(IntReturnParams intReturnParams)
    {
        return intReturnParamsMapper.updateIntReturnParams(intReturnParams);
    }

    /**
     * 批量删除返回类型
     *
     * @param ids 需要删除的返回类型主键
     * @return 结果
     */
    @Override
    public int deleteIntReturnParamsByIds(Long[] ids)
    {
        return intReturnParamsMapper.deleteIntReturnParamsByIds(ids);
    }

    /**
     * 删除返回类型信息
     *
     * @param id 返回类型主键
     * @return 结果
     */
    @Override
    public int deleteIntReturnParamsById(Long id)
    {
        return intReturnParamsMapper.deleteIntReturnParamsById(id);
    }
}
