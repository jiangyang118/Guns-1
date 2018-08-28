package com.stylefeng.guns.modular.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.protocol.MobileResonse;
import com.stylefeng.guns.modular.store.service.ITaskBaseService;
import com.stylefeng.guns.modular.store.vo.TaskQueryVO;
import com.stylefeng.guns.modular.system.model.TaskBase;
import com.stylefeng.guns.modular.system.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * ClassName: TaskController <br/>
 * Description: 任务api控制器 <br/>
 * Date: 2018年8月28日 上午11:17:03 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 *
 */
@Api(tags = "TaskController", value = "任务api控制器")
@RestController("apiTaskController")
@RequestMapping("/gunsApi/task")
public class TaskController extends BaseController {

    @Autowired
    private ITaskBaseService taskBaseService;

    @ApiOperation(value = "获取当前登录人的任务列表", httpMethod = "GET", notes = "显示该登录人的任务列表")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public MobileResonse query(User user, @ApiParam(value = "当前页码", required = true) int current,
            @ApiParam(value = "页码数量", required = true) int size) {

        TaskQueryVO vo = new TaskQueryVO();
        vo.setAssignee(user.getId());
        Page<TaskBase> page = new Page<>(current, size);
        return MobileResonse.success(taskBaseService.selectPage(vo, page));
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public MobileResonse send() {
        System.err.println("send message to restaurant");
        return MobileResonse.success();
    }

    @ApiOperation(value = "获取店铺的核查历史", httpMethod = "GET", notes = "默认查询任务状态为已完成的任务")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public MobileResonse history(@ApiParam(value = "店铺id", required = true) int storeId,
            @ApiParam(value = "当前页码", required = true) int current,
            @ApiParam(value = "页码数量", required = true) int size) {

        TaskQueryVO vo = new TaskQueryVO();
        vo.setStore(storeId);
        List<Integer> eqStatus = Lists.newArrayList(2, 3, 4);
        vo.setEqStatus(eqStatus);
        Page<TaskBase> page = new Page<>(current, size);
        return MobileResonse.success(taskBaseService.selectPage(vo, page));
    }
}
