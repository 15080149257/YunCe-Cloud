package com.yunce.interfaces.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.yunce.common.core.exception.CheckedException;
import com.yunce.common.core.exception.ServiceException;
import com.yunce.common.core.utils.DateUtils;
import com.yunce.common.security.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunce.interfaces.mapper.IntUserInfoMapper;
import com.yunce.interfaces.domain.IntUserInfo;
import com.yunce.interfaces.service.IIntUserInfoService;

/**
 * 用户调用接口关系Service业务层处理
 *
 * @author linjunfa
 * @date 2023-06-08
 */
@Service
public class IntUserInfoServiceImpl implements IIntUserInfoService {
    @Autowired
    private IntUserInfoMapper intUserInfoMapper;

    /**
     * 查询用户调用接口关系
     *
     * @param id 用户调用接口关系主键
     * @return 用户调用接口关系
     */
    @Override
    public IntUserInfo selectIntUserInfoById(Long id) {
        return intUserInfoMapper.selectIntUserInfoById(id);
    }

    /**
     * 查询用户调用接口关系列表
     *
     * @param intUserInfo 用户调用接口关系
     * @return 用户调用接口关系
     */
    @Override
    public List<IntUserInfo> selectIntUserInfoList(IntUserInfo intUserInfo) {
        return intUserInfoMapper.selectIntUserInfoList(intUserInfo);
    }

    /**
     * 新增用户调用接口关系
     *
     * @param intUserInfo 用户调用接口关系
     * @return 结果
     */
    @Override
    public int insertIntUserInfo(IntUserInfo intUserInfo) {
        intUserInfo.setCreateTime(DateUtils.getNowDate());
        intUserInfo.setCreateBy(SecurityUtils.getUsername());
        return intUserInfoMapper.insertIntUserInfo(intUserInfo);
    }

    /**
     * 修改用户调用接口关系
     *
     * @param intUserInfo 用户调用接口关系
     * @return 结果
     */
    @Override
    public int updateIntUserInfo(IntUserInfo intUserInfo) {
        intUserInfo.setUpdateTime(DateUtils.getNowDate());
        intUserInfo.setUpdateBy(SecurityUtils.getUsername());
        return intUserInfoMapper.updateIntUserInfo(intUserInfo);
    }

    /**
     * 批量删除用户调用接口关系
     *
     * @param ids 需要删除的用户调用接口关系主键
     * @return 结果
     */
    @Override
    public int deleteIntUserInfoByIds(Long[] ids) {
        return intUserInfoMapper.deleteIntUserInfoByIds(ids);
    }

    /**
     * 删除用户调用接口关系信息
     *
     * @param id 用户调用接口关系主键
     * @return 结果
     */
    @Override
    public int deleteIntUserInfoById(Long id) {
        return intUserInfoMapper.deleteIntUserInfoById(id);
    }

    @Override
    public boolean invokeCount(long infoId, long userId) {
        if (infoId <= 0 || userId <= 0) {
            throw new CheckedException("系统检测到您当前的权限异常");
        }
        IntUserInfo intUserInfo = new IntUserInfo();
        intUserInfo.setUserId(SecurityUtils.getUserId());
        List<IntUserInfo> intUserInfos = this.selectIntUserInfoList(intUserInfo);
        //TODO 1.此处需要分布式锁防止高并发下系统被攻击 2.统计数据需要放到redis中进行统计然后同步到数据库持久化
        IntUserInfo obj = intUserInfos.stream()
                .filter(i -> i.getInfoId() == infoId && i.getUserId()==userId)
                .map(i -> {
                    if (i.getLeftNum()>0){
                        i.setLeftNum(i.getLeftNum() - 1);
                        i.setTotalNum(i.getTotalNum() + 1);
                        return i;
                    }
                    return null;
                }).findFirst().orElse(null);
        return this.updateIntUserInfo(obj)>0?true:false;
    }
}
