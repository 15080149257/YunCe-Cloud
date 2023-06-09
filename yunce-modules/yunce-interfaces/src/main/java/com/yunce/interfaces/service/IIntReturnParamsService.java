package com.yunce.interfaces.service;

import java.util.List;
import com.yunce.interfaces.domain.IntReturnParams;

/**
 * 返回类型Service接口
 *
 * @author linjunfa
 * @date 2023-06-08
 */
public interface IIntReturnParamsService
{
    /**
     * 查询返回类型
     *
     * @param id 返回类型主键
     * @return 返回类型
     */
    public List<IntReturnParams> selectIntReturnParamsById(Long id);

    /**
     * 查询返回类型列表
     *
     * @param intReturnParams 返回类型
     * @return 返回类型集合
     */
    public List<IntReturnParams> selectIntReturnParamsList(IntReturnParams intReturnParams);

    /**
     * 新增返回类型
     *
     * @param intReturnParams 返回类型
     * @return 结果
     */
    public int insertIntReturnParams(IntReturnParams intReturnParams);

    /**
     * 修改返回类型
     *
     * @param intReturnParams 返回类型
     * @return 结果
     */
    public int updateIntReturnParams(IntReturnParams intReturnParams);

    /**
     * 批量删除返回类型
     *
     * @param ids 需要删除的返回类型主键集合
     * @return 结果
     */
    public int deleteIntReturnParamsByIds(Long[] ids);

    /**
     * 删除返回类型信息
     *
     * @param id 返回类型主键
     * @return 结果
     */
    public int deleteIntReturnParamsById(Long id);
}
