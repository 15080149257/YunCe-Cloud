package com.yunce.interfaces.mapper;

import java.util.List;
import com.yunce.interfaces.domain.IntUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户调用接口关系Mapper接口
 *
 * @author linjunfa
 * @date 2023-06-08
 */
@Mapper
public interface IntUserInfoMapper
{
    /**
     * 查询用户调用接口关系
     *
     * @param id 用户调用接口关系主键
     * @return 用户调用接口关系
     */
    public IntUserInfo selectIntUserInfoById(Long id);

    /**
     * 查询用户调用接口关系列表
     *
     * @param intUserInfo 用户调用接口关系
     * @return 用户调用接口关系集合
     */
    public List<IntUserInfo> selectIntUserInfoList(IntUserInfo intUserInfo);

    /**
     * 新增用户调用接口关系
     *
     * @param intUserInfo 用户调用接口关系
     * @return 结果
     */
    public int insertIntUserInfo(IntUserInfo intUserInfo);

    /**
     * 修改用户调用接口关系
     *
     * @param intUserInfo 用户调用接口关系
     * @return 结果
     */
    public int updateIntUserInfo(IntUserInfo intUserInfo);

    /**
     * 删除用户调用接口关系
     *
     * @param id 用户调用接口关系主键
     * @return 结果
     */
    public int deleteIntUserInfoById(Long id);

    /**
     * 批量删除用户调用接口关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIntUserInfoByIds(Long[] ids);

}
