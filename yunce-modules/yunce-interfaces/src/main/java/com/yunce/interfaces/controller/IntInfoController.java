package com.yunce.interfaces.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.yunce.api.client.YunCeApiClient;
import com.yunce.api.model.User;
import com.yunce.common.core.domain.IdRequest;
import com.yunce.common.core.enums.InterfacesEnum;
import com.yunce.common.core.exception.ServiceException;
import com.yunce.common.core.group.Type;
import com.yunce.common.core.utils.StringUtils;
import com.yunce.common.security.utils.SecurityUtils;
import com.yunce.interfaces.domain.IntInfoInvokeRequest;
import com.yunce.interfaces.domain.IntRequestParams;
import com.yunce.interfaces.domain.IntReturnParams;
import com.yunce.interfaces.domain.vo.IntInfoVO;
import com.yunce.interfaces.service.IIntRequestParamsService;
import com.yunce.interfaces.service.IIntReturnParamsService;
import com.yunce.interfaces.service.impl.IntRequestParamsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.yunce.common.log.annotation.Log;
import com.yunce.common.log.enums.BusinessType;
import com.yunce.common.security.annotation.RequiresPermissions;
import com.yunce.interfaces.domain.IntInfo;
import com.yunce.interfaces.service.IIntInfoService;
import com.yunce.common.core.web.controller.BaseController;
import com.yunce.common.core.web.domain.AjaxResult;
import com.yunce.common.core.utils.poi.ExcelUtil;
import com.yunce.common.core.web.page.TableDataInfo;

/**
 * 接口信息Controller
 *
 * @author 林俊发
 * @date 2023-06-06
 */
@RestController
@RequestMapping("/info")
public class IntInfoController extends BaseController
{
    @Autowired
    private IIntInfoService intInfoService;

    @Autowired
    private YunCeApiClient yunCeApiClient;

    @Autowired
    private IIntRequestParamsService intRequestParamsService;

    @Autowired
    private IIntReturnParamsService intReturnParamsService;


    /**
     * 查询接口信息列表
     */
    @RequiresPermissions("interfaces:info:list")
    @GetMapping("/list")
    public TableDataInfo list(IntInfo intInfo)
    {
        startPage();
        List<IntInfo> list = intInfoService.selectIntInfoList(intInfo);
        return getDataTable(list);
    }

    /**
     * 导出接口信息列表
     */
    @RequiresPermissions("interfaces:info:export")
    @Log(title = "接口信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntInfo intInfo)
    {
        List<IntInfo> list = intInfoService.selectIntInfoList(intInfo);
        ExcelUtil<IntInfo> util = new ExcelUtil<IntInfo>(IntInfo.class);
        util.exportExcel(response, list, "接口信息数据");
    }

    /**
     * 获取接口信息详细信息
     */
    @RequiresPermissions("interfaces:info:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(intInfoService.selectIntInfoById(id))
                .put("RequestParamsList",intRequestParamsService.selectIntRequestParamsById(id))
                .put("ReturnParamsList",intReturnParamsService.selectIntReturnParamsById(id));
    }

    /**
     * 新增接口信息
     */
    @RequiresPermissions("interfaces:info:add")
    @Log(title = "接口信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated(Type.Add.class) @RequestBody IntInfo intInfo)
    {
        intInfoService.insertIntInfo(intInfo);
        return success(intInfo.getId());
    }

    /**
     * 修改接口信息
     */
    @RequiresPermissions("interfaces:info:edit")
    @Log(title = "接口信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated(Type.Edit.class) @RequestBody IntInfo intInfo)
    {
        intInfoService.updateIntInfo(intInfo);
        return success(intInfo.getId());
    }

    /**
     * 删除接口信息
     */
    @RequiresPermissions("interfaces:info:remove")
    @Log(title = "接口信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(intInfoService.deleteIntInfoByIds(ids));
    }

    /**
     * 发布接口信息
     */
    @RequiresPermissions("interfaces:info:online")  //1.判断用户是否拥有该权限
    @Log(title = "接口信息", businessType = BusinessType.UPDATE)
    @PutMapping("/online")
    public AjaxResult online(@Validated(Type.Edit.class) @RequestBody IdRequest idRequest)  //1.通过参数校验id是否为空
    {
        //2.校验该接口是否存在
        IntInfo intInfo = intInfoService.selectIntInfoById(idRequest.getId());
        if (intInfo==null){
            throw new ServiceException("接口发布失败，未查到接口信息");
        }
        //3.判断该接口是否可以调用
        User user = new User();
        user.setUsername("yupi");
        String usernameByPost = yunCeApiClient.getUsernameByPost(user);
        if (StringUtils.isEmpty(usernameByPost)) throw new ServiceException("接口调用异常");
        //4.修改接口数据库中的状态
        intInfo.setStatus(InterfacesEnum.open.getValue());
        return toAjax(intInfoService.updateIntInfo(intInfo));
    }

    /**
     * 下线接口信息
     */
    @RequiresPermissions("interfaces:info:offline")
    @Log(title = "接口信息", businessType = BusinessType.UPDATE)
    @PutMapping("/offline")
    public AjaxResult offline(@Validated(Type.Edit.class) @RequestBody IdRequest idRequest)
    {
        //2.校验该接口是否存在
        IntInfo intInfo = intInfoService.selectIntInfoById(idRequest.getId());
        if (intInfo==null){
            throw new ServiceException("接口发布失败，未查到接口信息");
        }
        //3.修改接口数据库中的状态
        intInfo.setStatus(InterfacesEnum.close.getValue());
        return toAjax(intInfoService.updateIntInfo(intInfo));
    }

    /**
     * 查询所有接口信息列表
     *
     * @return
     */
    @RequiresPermissions("interfaces:info:list")
    @GetMapping("/listAll")
    public AjaxResult listAll()
    {
        return success(intInfoService.selectIntInfoListAll());
    }

    /**
     * 测试调用
     */
    @RequiresPermissions("interfaces:info:invoke")
    @Log(title = "接口信息", businessType = BusinessType.INSERT)
    @PostMapping("/invoke")
    public AjaxResult invoke(@Validated(Type.Add.class) @RequestBody IntInfoInvokeRequest intInfoInvokeRequest)
    {
        IntInfo intInfo = intInfoService.selectIntInfoById(intInfoInvokeRequest.getId());
        if (intInfo==null) throw new ServiceException("接口异常");
        if (!intInfo.getStatus().equals(InterfacesEnum.open.getValue()) ) throw new ServiceException("未找到该接口");
        String appKey = SecurityUtils.getLoginUser().getSysUser().getAppKey();
        String appSecret = SecurityUtils.getLoginUser().getSysUser().getAppSecret();
        YunCeApiClient tempClient = new YunCeApiClient(appKey, appSecret);
        String userRequestParams = intInfoInvokeRequest.getUserRequestParams();
        Gson gson = new Gson();
        /** 获取自定义sdk的user */
        User user = gson.fromJson(userRequestParams, User.class);
        String usernameByPost = tempClient.getUsernameByPost(user);
        return success().put("invoke",usernameByPost);
    }

    /**
     * 判断接口是否存在
     * */
    @GetMapping("/judge")
    public AjaxResult judge(@RequestBody IntInfo intInfo)
    {
        return success(intInfoService.judge(intInfo));
    }
}
