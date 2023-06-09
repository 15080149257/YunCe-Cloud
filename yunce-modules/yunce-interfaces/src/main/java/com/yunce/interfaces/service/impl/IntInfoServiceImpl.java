package com.yunce.interfaces.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yunce.common.core.utils.DateUtils;
import com.yunce.common.security.utils.SecurityUtils;
import com.yunce.interfaces.domain.vo.IntInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunce.interfaces.mapper.IntInfoMapper;
import com.yunce.interfaces.domain.IntInfo;
import com.yunce.interfaces.service.IIntInfoService;

/**
 * 接口信息Service业务层处理
 *
 * @author 林俊发
 * @date 2023-06-06
 */
@Service
public class IntInfoServiceImpl implements IIntInfoService
{
    @Autowired
    private IntInfoMapper intInfoMapper;

    /**
     * 查询接口信息
     *
     * @param id 接口信息主键
     * @return 接口信息
     */
    @Override
    public IntInfo selectIntInfoById(Long id)
    {
        return intInfoMapper.selectIntInfoById(id);
    }

    /**
     * 查询接口信息列表
     *
     * @param intInfo 接口信息
     * @return 接口信息
     */
    @Override
    public List<IntInfo> selectIntInfoList(IntInfo intInfo)
    {
        return intInfoMapper.selectIntInfoList(intInfo);
    }

    /**
     * 新增接口信息
     *
     * @param intInfo 接口信息
     * @return 结果
     */
    @Override
    public int insertIntInfo(IntInfo intInfo)
    {
        intInfo.setCreateBy(SecurityUtils.getUsername());
        intInfo.setCreateTime(DateUtils.getNowDate());
        return intInfoMapper.insertIntInfo(intInfo);
    }

    /**
     * 修改接口信息
     *
     * @param intInfo 接口信息
     * @return 结果
     */
    @Override
    public int updateIntInfo(IntInfo intInfo)
    {
        intInfo.setUpdateBy(SecurityUtils.getUsername());
        intInfo.setUpdateTime(DateUtils.getNowDate());
        return intInfoMapper.updateIntInfo(intInfo);
    }

    /**
     * 批量删除接口信息
     *
     * @param ids 需要删除的接口信息主键
     * @return 结果
     */
    @Override
    public int deleteIntInfoByIds(Long[] ids)
    {
        return intInfoMapper.deleteIntInfoByIds(ids);
    }

    /**
     * 删除接口信息信息
     *
     * @param id 接口信息主键
     * @return 结果
     */
    @Override
    public int deleteIntInfoById(Long id)
    {
        return intInfoMapper.deleteIntInfoById(id);
    }

    @Override
    public Map<String, List<IntInfoVO>> selectIntInfoListAll() {
        List<IntInfoVO> list = intInfoMapper.selectIntInfoListAll();
        return list.stream().collect(Collectors.groupingBy(IntInfoVO::getGroupName));
    }

    @Override
    public IntInfo judge(IntInfo intInfo) {
        return this.selectIntInfoList(intInfo).get(0);
    }
}
