package com.stylefeng.guns.modular.store.controller;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.constant.EntityConstants;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.store.service.ITaskBaseService;
import com.stylefeng.guns.modular.system.model.TaskBase;

/**
 * 任务管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-27 11:32:12
 */
@Controller
@RequestMapping("/taskBase")
public class TaskController extends BaseController {

    private String PREFIX = "/store/taskBase/";

    @Autowired
    private ITaskBaseService taskBaseService;

    /**
     * 跳转到任务管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "taskBase.html";
    }

    /**
     * 跳转到添加任务管理
     */
    @RequestMapping("/taskBase_add")
    public String taskBaseAdd() {
        return PREFIX + "taskBase_add.html";
    }

    /**
     * 跳转到修改任务管理
     */
    @RequestMapping("/taskBase_update/{taskBaseId}")
    public String taskBaseUpdate(@PathVariable Integer taskBaseId, Model model) {
        TaskBase taskBase = taskBaseService.selectById(taskBaseId);
        model.addAttribute("item", taskBase);
        LogObjectHolder.me().set(taskBase);
        return PREFIX + "taskBase_edit.html";
    }

    /**
     * 获取任务管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return taskBaseService.selectList(null);
    }

    /**
     * 新增任务管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TaskBase taskBase) {
        Date now = new Date();
        taskBase.setCreateDate(now);
        taskBase.setModifyDate(now);
        taskBaseService.insert(taskBase);
        return SUCCESS_TIP;
    }

    /**
     * 删除任务管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer taskBaseId) {
        taskBaseService.deleteById(taskBaseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改任务管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TaskBase taskBase) {
        Date now = new Date();
        taskBase.setModifyDate(now);
        TaskBase dbTaskBase = taskBaseService.selectById(taskBase.getId());
        BeanUtils.copyProperties(taskBase, dbTaskBase, EntityConstants.UPDATE_IGNORE_PROPERTIES);
        taskBaseService.updateById(dbTaskBase);
        return SUCCESS_TIP;
    }

    /**
     * 任务管理详情
     */
    @RequestMapping(value = "/detail/{taskBaseId}")
    @ResponseBody
    public Object detail(@PathVariable("taskBaseId") Integer taskBaseId) {
        return taskBaseService.selectById(taskBaseId);
    }
}
