package com.stylefeng.guns.modular.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.protocol.MobileResonse;
import com.stylefeng.guns.core.constant.ApiConstants;
import com.stylefeng.guns.core.shiro.UserContext;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.store.service.IExamineService;
import com.stylefeng.guns.modular.store.service.IStoreBaseService;
import com.stylefeng.guns.modular.store.service.ITaskAssigneeService;
import com.stylefeng.guns.modular.store.service.ITaskBaseService;
import com.stylefeng.guns.modular.store.service.ITaskDetailService;
import com.stylefeng.guns.modular.store.service.SnService;
import com.stylefeng.guns.modular.store.vo.ExamineResultVO;
import com.stylefeng.guns.modular.store.vo.TaskBaseVO;
import com.stylefeng.guns.modular.store.vo.TaskDetailVO;
import com.stylefeng.guns.modular.system.dao.vo.StoreListVO;
import com.stylefeng.guns.modular.system.dao.vo.StoreQueryVO;
import com.stylefeng.guns.modular.system.dao.vo.TaskListVO;
import com.stylefeng.guns.modular.system.dao.vo.TaskQueryVO;
import com.stylefeng.guns.modular.system.model.TaskBase;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.system.service.IUserService;

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
    @Autowired
    private IStoreBaseService storeBaseService;
    @Autowired
    private ITaskAssigneeService taskAssigneeService;
    @Autowired
    private IExamineService examineService;
    @Autowired
    private ITaskDetailService taskDetailService;
    @Autowired
    private IDictService dictService;
    @Autowired
    private IUserService userService;
    @Autowired
    private SnService snService;

    /*********************************** 我的任务页面接口 ******************************************************/

    @ApiOperation(value = "获取当前登录人的任务列表", httpMethod = "GET", notes = "显示该登录人的任务列表，可以查询自己指派的任务，或者查询自己接收的任务")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public MobileResonse query(TaskQueryVO vo, @ApiParam(value = "当前页码", required = true) int current,
            @ApiParam(value = "页码数量", required = true) int size) {
        if (Integer.valueOf(1).equals(vo.getType())) {
            vo.setAssignee(UserContext.getUser().getId());
        } else {
            vo.setAppointer(UserContext.getUser().getId());
        }
        Page<TaskListVO> page = new Page<>(current, size);
        return MobileResonse.success(taskBaseService.selectTasks(vo, page));
    }

    @ApiOperation(value = "接受任务", httpMethod = "POST", notes = "接受任务")
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public MobileResonse receive(@ApiParam(value = "任务指派表id", required = true) Integer assigneeId) {
        Integer userId = UserContext.getUser().getId();
        TaskBase taskBase = taskAssigneeService.receive(assigneeId, userId);
        if (taskBaseService.canChangeState(taskBase, userId)) {
            taskBaseService.changeState(taskBase, userId);
        }
        if (taskBaseService.canStartTask(taskBase, userId)) {
            taskBaseService.startTask(taskBase, userId);
        }
        return MobileResonse.success();
    }

    @ApiOperation(value = "拒绝任务", httpMethod = "POST", notes = "拒绝任务")
    @RequestMapping(value = "/refuse", method = RequestMethod.POST)
    public MobileResonse refuse(@ApiParam(value = "任务指派表id", required = true) Integer assigneeId) {
        Integer userId = UserContext.getUser().getId();
        TaskBase taskBase = taskAssigneeService.refuse(assigneeId, userId);
        if (taskBaseService.canEndTask(taskBase, userId)) {
            // TODO 告知任务指派者，拒绝任务的人数达到最低任务执行者数，需要重新指派
        }
        return MobileResonse.success();
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public MobileResonse send() {
        // TODO 改变发送状态，并记录到数据库中
        return MobileResonse.success();
    }

    /*********************************** 历史页面接口 ******************************************************/

    @ApiOperation(value = "获取店铺的核查历史", httpMethod = "GET", notes = "默认查询任务状态为已完成的任务")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public MobileResonse history(@ApiParam(value = "店铺id", required = true) int storeId,
            @ApiParam(value = "当前页码", required = true) int current,
            @ApiParam(value = "页码数量", required = true) int size) {
        TaskQueryVO vo = new TaskQueryVO();
        vo.setStore(storeId);
        List<Integer> eqStatus = Lists.newArrayList(2, 3, 4);
        vo.setEqStatus(eqStatus);
        Page<TaskListVO> page = new Page<>(current, size);
        return MobileResonse.success(taskBaseService.selectTasks(vo, page));
    }

    @ApiOperation(value = "获取店铺的核查历史详情", httpMethod = "GET", notes = "默认查询任务状态为已完成的任务详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public MobileResonse detail(@ApiParam(value = "任务id", required = true) int id) {
        return MobileResonse.success(taskBaseService.findByTaskId(id));
    }

    /*********************************** 执行任务详情接口 ******************************************************/

    @ApiOperation(value = "通知单提交", httpMethod = "POST", notes = "通知单提交，包含执法者签名和是否回避")
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public MobileResonse sign(@RequestBody TaskDetailVO vo) {
        taskAssigneeService.save(vo, UserContext.getUser().getId());
        return MobileResonse.success();
    }

    @ApiOperation(value = "核查结果提交", httpMethod = "POST", notes = "核查结果提交，包含被检查单位签字，核查项详情，检查结果")
    @RequestMapping(value = "/inspect", method = RequestMethod.POST)
    public MobileResonse inspect(@RequestBody TaskDetailVO vo) {
        Assert.isTrue(!taskDetailService.hasBaseTask(vo.getId()), ApiConstants.checkTakDetail);
        taskDetailService.save(vo);
        return MobileResonse.success();
    }

    /*********************************** 发布任务接口 ******************************************************/
    @ApiOperation(value = "获取所有检查类型", httpMethod = "GET", notes = "获取所有检查类型")
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public MobileResonse type() {
        return MobileResonse.success(dictService.selectByParentCode("task_type"));
    }

    @ApiOperation(value = "获取所有餐饮商", httpMethod = "GET", notes = "获取所有餐饮商")
    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public MobileResonse company(StoreQueryVO vo, @ApiParam(value = "当前页码", required = true) int current,
            @ApiParam(value = "页码数量", required = true) int size) {
        Page<StoreListVO> page = new Page<>(current, size);
        return MobileResonse.success(storeBaseService.selectStores(vo, page));
    }

    @ApiOperation(value = "获取所有执法者", httpMethod = "GET", notes = "获取所有执法者")
    @RequestMapping(value = "/assignee", method = RequestMethod.GET)
    public MobileResonse assignee() {
        return MobileResonse.success(userService.selectList(null));
    }

    @ApiOperation(value = "获取所有检查项", httpMethod = "GET", notes = "获取所有检查项")
    @RequestMapping(value = "/examine", method = RequestMethod.GET)
    public MobileResonse examine() {
        return MobileResonse.success(examineService.selectList());
    }

    @ApiOperation(value = "提交任务", httpMethod = "POST", notes = "提交任务")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MobileResonse create(@RequestBody TaskBaseVO vo) {
        taskBaseService.create(vo, UserContext.getUser().getId());
        return MobileResonse.success();
    }

    /*********************************** 测试接口 ******************************************************/

    @ApiOperation(value = "测试获取检查结果", httpMethod = "GET", notes = "测试获取检查结果")
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public MobileResonse demo() {
        List<ExamineResultVO> list = Lists.newArrayList(new ExamineResultVO(1, 0, ""), new ExamineResultVO(1, 0, ""),
                new ExamineResultVO(1, 0, ""), new ExamineResultVO(1, 0, ""));

        return MobileResonse.success(list);
    }

    @ApiOperation(value = "测试构造创建的任务vo", httpMethod = "GET", notes = "测试构造创建的任务vo")
    @RequestMapping(value = "/demo_create", method = RequestMethod.GET)
    public MobileResonse demoCreate() {
        TaskBaseVO vo = new TaskBaseVO();
        vo.setAssignees(Lists.newArrayList(1, 2));
        vo.setBeginDate(DateUtil.parseDate("2018-08-01"));
        vo.setEndDate(DateUtil.parseDate("2018-08-07"));
        vo.setStores(Lists.newArrayList(1, 2, 3, 4));
        vo.setType(1);
        return MobileResonse.success(vo);
    }

    @RequestMapping(value = "/demo_sn", method = RequestMethod.GET)
    public MobileResonse demoSN() {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            res.add(snService.get());
        }
        return MobileResonse.success(res);
    }

    @RequestMapping(value = "/demo_set_sn", method = RequestMethod.GET)
    public MobileResonse demoSetSN() {
        snService.set();
        return MobileResonse.success();
    }
}
