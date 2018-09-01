package com.stylefeng.guns.modular.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.constant.ApiConstants;
import com.stylefeng.guns.core.constant.EntityConstants;
import com.stylefeng.guns.modular.store.service.IStoreBaseService;
import com.stylefeng.guns.modular.store.service.ITaskAssigneeService;
import com.stylefeng.guns.modular.store.service.ITaskBaseService;
import com.stylefeng.guns.modular.store.service.ITaskDetailService;
import com.stylefeng.guns.modular.store.service.SnService;
import com.stylefeng.guns.modular.store.vo.TaskBaseVO;
import com.stylefeng.guns.modular.store.vo.TaskDetailVO;
import com.stylefeng.guns.modular.system.dao.TaskBaseMapper;
import com.stylefeng.guns.modular.system.dao.vo.TaskListVO;
import com.stylefeng.guns.modular.system.dao.vo.TaskQueryVO;
import com.stylefeng.guns.modular.system.model.StoreBase;
import com.stylefeng.guns.modular.system.model.TaskAssignee;
import com.stylefeng.guns.modular.system.model.TaskBase;
import com.stylefeng.guns.modular.system.model.TaskDetail;
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
@Transactional
public class TaskBaseServiceImpl extends ServiceImpl<TaskBaseMapper, TaskBase> implements ITaskBaseService {

    @Resource
    private TaskBaseMapper taskBaseMapper;
    @Resource
    private ITaskAssigneeService taskAssigneeService;
    @Resource
    private ITaskDetailService taskDetailService;
    @Resource
    private IStoreBaseService storeBaseService;
    @Resource
    private IUserService userService;
    @Resource
    private SnService snService;

    /**
     * 性能差，主要实现功能
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskListVO> selectPage(TaskQueryVO vo, Page<TaskBase> page) {
        Page<TaskBase> tPage = selectPage(page, construct(vo));
        List<TaskListVO> list = new ArrayList<>();
        for (TaskBase taskBase : tPage.getRecords()) {
            TaskListVO tvo = new TaskListVO();
            StoreBase storeBase = storeBaseService.selectById(taskBase.getStore());
            BeanUtils.copyProperties(storeBase, tvo, EntityConstants.UPDATE_IGNORE_PROPERTIES);
            BeanUtils.copyProperties(taskBase, tvo);
            tvo.setName(userService.selectById(taskBase.getAppointer()).getName());
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

    @Override
    @Transactional(readOnly = true)
    public Page<TaskListVO> selectTasks(TaskQueryVO vo, Page<TaskListVO> page) {
        Page<TaskListVO> tPage = page.setRecords(this.baseMapper.selectTasks(vo, page));
        for (TaskListVO tvo : tPage.getRecords()) {
            tvo.setAssigneeVOs(taskAssigneeService.selectAssignees(tvo.getId()));
        }
        return tPage;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean canStartTask(TaskBase taskBase, Integer userId) {
        return taskBase.getReceive() >= ApiConstants.START_NUM && taskAssigneeService.hasUser(taskBase.getId(), userId);

    }

    @Override
    @Transactional(readOnly = true)
    public boolean canEndTask(TaskBase taskBase, Integer userId) {
        return taskBase.getRefuse() == taskBase.getAssignee() && taskAssigneeService.hasUser(taskBase.getId(), userId);
    }

    @Override
    public void startTask(TaskBase taskBase, Integer userId) {
        TaskBase tBase = selectById(taskBase.getId());
        Assert.isTrue(taskAssigneeService.hasUser(tBase.getId(), userId), ApiConstants.checkUserCanDo);
        tBase.setStatus(1);
        tBase.setInspectDate(new Date());
        updateById(tBase);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean canChangeState(TaskBase taskBase, Integer userId) {
        return taskBase.getReceive() > 0 && taskBase.getStatus().equals(0)
                && taskAssigneeService.hasUser(taskBase.getId(), userId);
    }

    @Override
    public void changeState(TaskBase taskBase, Integer userId) {
        TaskBase tBase = selectById(taskBase.getId());
        Assert.isTrue(taskAssigneeService.hasUser(tBase.getId(), userId), ApiConstants.checkUserCanDo);
        Assert.isTrue(tBase.getStatus().equals(0), ApiConstants.checkStatusStringBegin);
        tBase.setStatus(5);
        tBase.setInspectDoneDate(new Date());
        updateById(tBase);
    }

    @Override
    public void approval(TaskBase taskBase, Integer userId) {
        TaskBase tBase = selectById(taskBase.getId());
        Assert.isTrue(taskAssigneeService.hasUser(tBase.getId(), userId), ApiConstants.checkUserCanDo);
        Assert.isTrue(tBase.getStatus().equals(1), ApiConstants.checkStatusStringDoing);
        tBase.setStatus(2);
        tBase.setInspectDoneDate(new Date());
        updateById(tBase);
    }

    @Override
    public void refuse(TaskBase taskBase, Integer userId) {
        TaskBase tBase = selectById(taskBase.getId());
        Assert.isTrue(taskAssigneeService.hasUser(tBase.getId(), userId), ApiConstants.checkUserCanDo);
        Assert.isTrue(tBase.getStatus().equals(1), ApiConstants.checkStatusStringDoing);
        tBase.setStatus(3);
        tBase.setInspectDoneDate(new Date());
        updateById(tBase);
    }

    @Override
    public void almost(TaskBase taskBase, Integer userId) {
        TaskBase tBase = selectById(taskBase.getId());
        Assert.isTrue(taskAssigneeService.hasUser(tBase.getId(), userId), ApiConstants.checkUserCanDo);
        Assert.isTrue(tBase.getStatus().equals(1), ApiConstants.checkStatusStringDoing);
        tBase.setStatus(4);
        tBase.setInspectDoneDate(new Date());
        updateById(tBase);
    }

    @Override
    public void create(TaskBaseVO vo, Integer userId) {
        Assert.notNull(vo, ApiConstants.checkNOTNULL);
        Assert.notEmpty(vo.getStores(), ApiConstants.checkCreateStores);
        Assert.notEmpty(vo.getStores(), ApiConstants.checkCreateAssignees);

        for (Integer store : vo.getStores()) {
            TaskBase task = new TaskBase();
            task.setCreateDate(new Date());
            task.setModifyDate(new Date());
            BeanUtils.copyProperties(vo, task);
            task.setAppointer(userId);
            task.setStore(store);
            task.setSn(snService.get());
            task.setStatus(0);
            task.setNotiStatus(0);
            task.setAssignee(vo.getAssignees().size());
            task.setReceive(0);
            task.setRefuse(0);
            insert(task);

            for (Integer assignee : vo.getAssignees()) {
                TaskAssignee taskAssignee = new TaskAssignee();
                taskAssignee.setCreateDate(new Date());
                taskAssignee.setModifyDate(new Date());
                taskAssignee.setAssignee(assignee);
                taskAssignee.setTaskId(task.getId());
                taskAssigneeService.insert(taskAssignee);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDetailVO findByTaskId(Integer taskId) {
        TaskDetail detail = taskDetailService.findByTaskId(taskId);
        TaskDetailVO vo = new TaskDetailVO();
        BeanUtils.copyProperties(detail, vo);
        vo.setAssigneeVOs(taskAssigneeService.selectAssignees(taskId));
        return vo;
    }

}
