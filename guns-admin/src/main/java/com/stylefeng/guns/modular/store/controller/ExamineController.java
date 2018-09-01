package com.stylefeng.guns.modular.store.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.store.service.IExamineService;
import com.stylefeng.guns.modular.system.model.Examine;

/**
 * 检查项信息控制器
 *
 * @author fengshuonan
 * @Date 2018-08-29 10:40:21
 */
@Controller
@RequestMapping("/examine")
public class ExamineController extends BaseController {

    private String PREFIX = "/store/examine/";

    @Autowired
    private IExamineService examineService;

    /**
     * 跳转到检查项信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "examine.html";
    }

    /**
     * 跳转到添加检查项信息
     */
    @RequestMapping("/examine_add")
    public String examineAdd() {
        return PREFIX + "examine_add.html";
    }

    /**
     * 跳转到修改检查项信息
     */
    @RequestMapping("/examine_update/{examineId}")
    public String examineUpdate(@PathVariable Integer examineId, Model model) {
        Examine examine = examineService.selectById(examineId);
        model.addAttribute("item",examine);
        LogObjectHolder.me().set(examine);
        return PREFIX + "examine_edit.html";
    }

    /**
     * 获取检查项信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return examineService.selectList(null);
    }

    /**
     * 新增检查项信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Examine examine) {
        Date now = new Date();
        examine.setCreateDate(now);
        examine.setModifyDate(now);
        examineService.insert(examine);
        return SUCCESS_TIP;
    }

    /**
     * 删除检查项信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer examineId) {
        examineService.deleteById(examineId);
        return SUCCESS_TIP;
    }

    /**
     * 修改检查项信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Examine examine) {
        Date now = new Date();
        examine.setModifyDate(now);
        examineService.updateById(examine);
        return SUCCESS_TIP;
    }

    /**
     * 检查项信息详情
     */
    @RequestMapping(value = "/detail/{examineId}")
    @ResponseBody
    public Object detail(@PathVariable("examineId") Integer examineId) {
        return examineService.selectById(examineId);
    }
}
