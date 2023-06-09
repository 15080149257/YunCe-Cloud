package com.yunce.interfaces.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.yunce.interfaces.domain.IntGroup;
import com.yunce.interfaces.service.IIntGroupService;
import com.yunce.common.core.web.controller.BaseController;
import com.yunce.common.core.web.domain.AjaxResult;
import com.yunce.common.core.utils.poi.ExcelUtil;
import com.yunce.common.core.web.page.TableDataInfo;

/**
 * 接口组Controller
 *
 * @author linjunfa
 * @date 2023-06-07
 */
@RestController
@RequestMapping("/group")
public class IntGroupController extends BaseController
{
    @Autowired
    private IIntGroupService intGroupService;

    /**
     * 查询接口组列表
     */
    @RequiresPermissions("interfaces:group:list")
    @GetMapping("/list")
    public TableDataInfo list(IntGroup intGroup)
    {
        startPage();
        List<IntGroup> list = intGroupService.selectIntGroupList(intGroup);
        return getDataTable(list);
    }

    /**
     * 导出接口组列表
     */
    @RequiresPermissions("interfaces:group:export")
    @Log(title = "接口组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntGroup intGroup)
    {
        List<IntGroup> list = intGroupService.selectIntGroupList(intGroup);
        ExcelUtil<IntGroup> util = new ExcelUtil<IntGroup>(IntGroup.class);
        util.exportExcel(response, list, "接口组数据");
    }

    /**
     * 获取接口组详细信息
     */
    @RequiresPermissions("interfaces:group:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intGroupService.selectIntGroupById(id));
    }

    /**
     * 新增接口组
     */
    @RequiresPermissions("interfaces:group:add")
    @Log(title = "接口组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntGroup intGroup)
    {
        return toAjax(intGroupService.insertIntGroup(intGroup));
    }

    /**
     * 修改接口组
     */
    @RequiresPermissions("interfaces:group:edit")
    @Log(title = "接口组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntGroup intGroup)
    {
        return toAjax(intGroupService.updateIntGroup(intGroup));
    }

    /**
     * 删除接口组
     */
    @RequiresPermissions("interfaces:group:remove")
    @Log(title = "接口组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intGroupService.deleteIntGroupByIds(ids));
    }

    /**
     * 查询所有接口组列表
     */
    @RequiresPermissions("interfaces:group:list")
    @GetMapping("/listAll")
    public TableDataInfo listAll()
    {
        List<IntGroup> list = intGroupService.selectIntGroupList(new IntGroup());
        return getDataTable(list);
    }

}
