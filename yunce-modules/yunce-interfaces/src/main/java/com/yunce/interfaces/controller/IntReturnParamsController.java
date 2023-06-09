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
import com.yunce.interfaces.domain.IntReturnParams;
import com.yunce.interfaces.service.IIntReturnParamsService;
import com.yunce.common.core.web.controller.BaseController;
import com.yunce.common.core.web.domain.AjaxResult;
import com.yunce.common.core.utils.poi.ExcelUtil;
import com.yunce.common.core.web.page.TableDataInfo;

/**
 * 返回类型Controller
 *
 * @author linjunfa
 * @date 2023-06-08
 */
@RestController
@RequestMapping("/param")
public class IntReturnParamsController extends BaseController
{
    @Autowired
    private IIntReturnParamsService intReturnParamsService;

    /**
     * 查询返回类型列表
     */
    @RequiresPermissions("interfaces:info:list")
    @GetMapping("/list")
    public TableDataInfo list(IntReturnParams intReturnParams)
    {
        startPage();
        List<IntReturnParams> list = intReturnParamsService.selectIntReturnParamsList(intReturnParams);
        return getDataTable(list);
    }

    /**
     * 新增返回类型
     */
    @RequiresPermissions("interfaces:info:add")
    @Log(title = "返回类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntReturnParams intReturnParams)
    {
        return toAjax(intReturnParamsService.insertIntReturnParams(intReturnParams));
    }

    /**
     * 修改返回类型
     */
    @RequiresPermissions("interfaces:info:edit")
    @Log(title = "返回类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntReturnParams intReturnParams)
    {
        return toAjax(intReturnParamsService.updateIntReturnParams(intReturnParams));
    }

    /**
     * 删除返回类型
     */
    @RequiresPermissions("interfaces:info:remove")
    @Log(title = "返回类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long ids)
    {
        return toAjax(intReturnParamsService.deleteIntReturnParamsById(ids));
    }
}
