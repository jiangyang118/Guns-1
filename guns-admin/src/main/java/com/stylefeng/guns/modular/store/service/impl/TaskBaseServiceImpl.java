package com.stylefeng.guns.modular.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.constant.EntityConstants;
import com.stylefeng.guns.modular.store.service.IStoreBaseService;
import com.stylefeng.guns.modular.store.service.ITaskBaseService;
import com.stylefeng.guns.modular.store.vo.TaskListVO;
import com.stylefeng.guns.modular.store.vo.TaskQueryVO;
import com.stylefeng.guns.modular.system.dao.TaskBaseMapper;
import com.stylefeng.guns.modular.system.model.StoreBase;
import com.stylefeng.guns.modular.system.model.TaskBase;
import com.stylefeng.guns.modular.system.service.IUserService;

/**
 * <p>
 * 任务基本信息 服务实现类
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
@Service
public class TaskBaseServiceImpl extends ServiceImpl<TaskBaseMapper, TaskBase> implements ITaskBaseService {

    @Resource
    private TaskBaseMapper taskBaseMapper;
    @Resource
    private IStoreBaseService storeBaseService;
    @Resource
    private IUserService userService;

    /**
     * 性能差，主要实现功能
     */
    @Override
    public Page<TaskListVO> selectPage(TaskQueryVO vo, Page<TaskBase> page) {
        Page<TaskBase> tPage = selectPage(page, construct(vo));
        List<TaskListVO> list = new ArrayList<>();
        for (TaskBase taskBase : tPage.getRecords()) {
            TaskListVO tvo = new TaskListVO();
            StoreBase storeBase = storeBaseService.selectById(taskBase.getStore());
            BeanUtils.copyProperties(storeBase, tvo, EntityConstants.UPDATE_IGNORE_PROPERTIES);
            BeanUtils.copyProperties(taskBase, tvo);
            tvo.setAppointerName(userService.selectById(taskBase.getAppointer()).getName());
            tvo.setAssignee1Name(userService.selectById(taskBase.getAssignee1()).getName());
            tvo.setAssignee2Name(userService.selectById(taskBase.getAssignee2()).getName());
            tvo.setNotiStatusName(ConstantFactory.me().getDictsByCode("task_noti_status", taskBase.getNotiStatus()));
            tvo.setStatusName(ConstantFactory.me().getDictsByCode("task_status", taskBase.getNotiStatus()));
            list.add(tvo);
        }
        Page<TaskListVO> tkPage = new Page<>(page.getCurrent(), page.getSize());
        BeanUtils.copyProperties(page, tkPage);
        tkPage.setRecords(list);
        return tkPage;
    }

    private Wrapper<TaskBase> construct(TaskQueryVO vo) {
        Wrapper<TaskBase> wrapper = new EntityWrapper<>();
        if (null == vo) {
            return wrapper;
        }
        if (null != vo.getAssignee()) {
            wrapper = wrapper.and("assignee1 = {0}", vo.getAssignee());
            wrapper = wrapper.or("assignee2 = {0}", vo.getAssignee());
        }
        if (null != vo.getAppointer()) {
            wrapper = wrapper.andNew("appointer = {0}", vo.getAppointer());
        }
        if (null != vo.getStore()) {
            wrapper = wrapper.andNew("store = {0}", vo.getStore());
        }
        if (null != vo.getSn()) {
            wrapper = wrapper.andNew("sn like {0}", "%" + vo.getSn() + "%");
        }
        if (null != vo.getStatus()) {
            wrapper = wrapper.andNew("status = {0}", vo.getStatus());
        }
        if (null != vo.getEqStatus() && !vo.getEqStatus().isEmpty()) {
            wrapper = wrapper.in("status", vo.getEqStatus());
        }
        return wrapper;
    }
}
