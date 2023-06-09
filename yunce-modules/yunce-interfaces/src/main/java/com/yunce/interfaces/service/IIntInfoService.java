package com.yunce.interfaces.service;

import java.util.List;
import java.util.Map;

import com.yunce.interfaces.domain.IntInfo;
import com.yunce.interfaces.domain.vo.IntInfoVO;

/**
 * 接口信息Service接口
 *
 * @author 林俊发
 * @date 2023-06-06
 */
public interface IIntInfoService
{
    /**
     * 查询接口信息
     *
     * @param id 接口信息主键
     * @return 接口信息
     */
    public IntInfo selectIntInfoById(Long id);

    /**
     * 查询接口信息列表
     *
     * @param intInfo 接口信息
     * @return 接口信息集合
     */
    public List<IntInfo> selectIntInfoList(IntInfo intInfo);

    /**
     * 新增接口信息
     *
     * @param intInfo 接口信息
     * @return 结果
     */
    public int insertIntInfo(IntInfo intInfo);

    /**
     * 修改接口信息
     *
     * @param intInfo 接口信息
     * @return 结果
     */
    public int updateIntInfo(IntInfo intInfo);

    /**
     * 批量删除接口信息
     *
     * @param ids 需要删除的接口信息主键集合
     * @return 结果
     */
    public int deleteIntInfoByIds(Long[] ids);

    /**
     * 删除接口信息信息
     *
     * @param id 接口信息主键
     * @return 结果
     */
    public int deleteIntInfoById(Long id);

    Map<String, List<IntInfoVO>> selectIntInfoListAll();

    IntInfo judge(IntInfo intInfo);
}
