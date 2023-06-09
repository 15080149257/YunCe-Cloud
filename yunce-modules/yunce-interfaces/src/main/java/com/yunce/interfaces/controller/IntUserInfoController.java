package com.yunce.interfaces.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.yunce.common.core.exception.PreAuthorizeException;
import com.yunce.common.core.exception.ServiceException;
import com.yunce.common.core.group.Type;
import com.yunce.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yunce.common.log.annotation.Log;
import com.yunce.common.log.enums.BusinessType;
import com.yunce.common.security.annotation.RequiresPermissions;
import com.yunce.interfaces.domain.IntUserInfo;
import com.yunce.interfaces.service.IIntUserInfoService;
import com.yunce.common.core.web.controller.BaseController;
import com.yunce.common.core.web.domain.AjaxResult;
import com.yunce.common.core.utils.poi.ExcelUtil;
import com.yunce.common.core.web.page.TableDataInfo;

/**
 * 用户调用接口关系Controller
 *
 * @author linjunfa
 * @date 2023-06-08
 */
@RestController
@RequestMapping("/userInfo")
public class IntUserInfoController extends BaseController
{
    @Autowired
    private IIntUserInfoService intUserInfoService;

    /**
     * 查询用户调用接口关系列表
     */
    @RequiresPermissions("interfaces:info:list")
    @GetMapping("/list")
    public TableDataInfo list(IntUserInfo intUserInfo)
    {
        startPage();
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) throw new PreAuthorizeException();
        List<IntUserInfo> list = intUserInfoService.selectIntUserInfoList(intUserInfo);
        return getDataTable(list);
    }

    /**
     * 获取用户调用接口关系详细信息
     */
    @RequiresPermissions("interfaces:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) throw new PreAuthorizeException();
        return success(intUserInfoService.selectIntUserInfoById(id));
    }

    /**
     * 新增用户调用接口关系
     */
    @RequiresPermissions("interfaces:info:add")
    @Log(title = "用户调用接口关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(Type.Add.class) @RequestBody IntUserInfo intUserInfo)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) throw new PreAuthorizeException();
        if (intUserInfo.getLeftNum()<=0) throw new ServiceException("接口调用统计异常");
        return toAjax(intUserInfoService.insertIntUserInfo(intUserInfo));
    }

    /**
     * 修改用户调用接口关系
     */
    @RequiresPermissions("interfaces:info:edit")
    @Log(title = "用户调用接口关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(Type.Edit.class) @RequestBody IntUserInfo intUserInfo)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) throw new PreAuthorizeException();
        return toAjax(intUserInfoService.updateIntUserInfo(intUserInfo));
    }

    /**
     * 删除用户调用接口关系
     */
    @RequiresPermissions("interfaces:info:remove")
    @Log(title = "用户调用接口关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        if (!SecurityUtils.isAdmin(SecurityUtils.getUserId())) throw new PreAuthorizeException();
        return toAjax(intUserInfoService.deleteIntUserInfoByIds(ids));
    }
}
