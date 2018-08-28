package com.stylefeng.guns.modular.store.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.store.service.IStoreBaseService;
import com.stylefeng.guns.modular.store.vo.StoreVO;
import com.stylefeng.guns.modular.system.model.StoreBase;

/**
 * 餐饮商管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-27 11:31:19
 */
@Controller
@RequestMapping("/storeBase")
public class StoreBaseController extends BaseController {

    private String PREFIX = "/store/storeBase/";

    @Autowired
    private IStoreBaseService storeBaseService;

    /**
     * 跳转到餐饮商管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "storeBase.html";
    }

    /**
     * 跳转到添加餐饮商管理
     */
    @RequestMapping("/storeBase_add")
    public String storeBaseAdd() {
        return PREFIX + "storeBase_add.html";
    }

    /**
     * 跳转到修改餐饮商管理
     */
    @RequestMapping("/storeBase_update/{storeBaseId}")
    public String storeBaseUpdate(@PathVariable Integer storeBaseId, Model model) {
        StoreBase storeBase = storeBaseService.selectById(storeBaseId);
        model.addAttribute("item", storeBase);
        LogObjectHolder.me().set(storeBase);
        return PREFIX + "storeBase_edit.html";
    }

    /**
     * 获取餐饮商管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<StoreBase> list = storeBaseService.selectList(null);
        List<StoreVO> stores = new ArrayList<>();
        for (StoreBase storeBase : list) {
            StoreVO vo = new StoreVO();
            BeanUtils.copyProperties(storeBase, vo);
            vo.setStoreTypeStr(ConstantFactory.me().getDictsByName("店铺类型", storeBase.getStoreType()));
            stores.add(vo);
        }
        return stores;
    }

    /**
     * 新增餐饮商管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(StoreBase storeBase) {
        Date now = new Date();
        storeBase.setCreateDate(now);
        storeBase.setModifyDate(now);

        storeBaseService.insert(storeBase);
        return SUCCESS_TIP;
    }

    /**
     * 删除餐饮商管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer storeBaseId) {
        storeBaseService.deleteById(storeBaseId);
        return SUCCESS_TIP;
    }

    /**
     * 修改餐饮商管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(StoreBase storeBase) {
        Date now = new Date();
        storeBase.setModifyDate(now);
        storeBaseService.updateById(storeBase);
        return SUCCESS_TIP;
    }

    /**
     * 餐饮商管理详情
     */
    @RequestMapping(value = "/detail/{storeBaseId}")
    @ResponseBody
    public Object detail(@PathVariable("storeBaseId") Integer storeBaseId) {
        return storeBaseService.selectById(storeBaseId);
    }
}
