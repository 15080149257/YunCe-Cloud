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
import com.yunce.interfaces.domain.IntRequestParams;
import com.yunce.interfaces.service.IIntRequestParamsService;
import com.yunce.common.core.web.controller.BaseController;
import com.yunce.common.core.web.domain.AjaxResult;
import com.yunce.common.core.utils.poi.ExcelUtil;
import com.yunce.common.core.web.page.TableDataInfo;

/**
 * 请求参数Controller
 *
 * @author linjunfa
 * @date 2023-06-08
 */
@RestController
@RequestMapping("/params")
public class IntRequestParamsController extends BaseController
{
    @Autowired
    private IIntRequestParamsService intRequestParamsService;

    /**
     * 查询请求参数列表
     */
    @RequiresPermissions("interfaces:info:list")
    @GetMapping("/list")
    public TableDataInfo list(IntRequestParams intRequestParams)
    {
        startPage();
        List<IntRequestParams> list = intRequestParamsService.selectIntRequestParamsList(intRequestParams);
        return getDataTable(list);
    }

    /**
     * 新增请求参数
     */
    @RequiresPermissions("interfaces:info:add")
    @Log(title = "请求参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntRequestParams intRequestParams)
    {
        return toAjax(intRequestParamsService.insertIntRequestParams(intRequestParams));
    }

    /**
     * 修改请求参数
     */
    @RequiresPermissions("interfaces:info:edit")
    @Log(title = "请求参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntRequestParams intRequestParams)
    {
        return toAjax(intRequestParamsService.updateIntRequestParams(intRequestParams));
    }

    /**
     * 删除请求参数
     */
    @RequiresPermissions("interfaces:info:remove")
    @Log(title = "请求参数", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long ids)
    {
        return toAjax(intRequestParamsService.deleteIntRequestParamsById(ids));
    }
}
