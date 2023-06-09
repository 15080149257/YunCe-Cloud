package com.yunce.interfaces.mapper;

import java.util.List;
import com.yunce.interfaces.domain.IntRequestParams;

/**
 * 请求参数Mapper接口
 *
 * @author linjunfa
 * @date 2023-06-08
 */
public interface IntRequestParamsMapper
{
    /**
     * 查询请求参数
     *
     * @param id 请求参数主键
     * @return 请求参数
     */
    public List<IntRequestParams> selectIntRequestParamsById(Long id);

    /**
     * 查询请求参数列表
     *
     * @param intRequestParams 请求参数
     * @return 请求参数集合
     */
    public List<IntRequestParams> selectIntRequestParamsList(IntRequestParams intRequestParams);

    /**
     * 新增请求参数
     *
     * @param intRequestParams 请求参数
     * @return 结果
     */
    public int insertIntRequestParams(IntRequestParams intRequestParams);

    /**
     * 修改请求参数
     *
     * @param intRequestParams 请求参数
     * @return 结果
     */
    public int updateIntRequestParams(IntRequestParams intRequestParams);

    /**
     * 删除请求参数
     *
     * @param id 请求参数主键
     * @return 结果
     */
    public int deleteIntRequestParamsById(Long id);

    /**
     * 批量删除请求参数
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIntRequestParamsByIds(Long[] ids);
}
