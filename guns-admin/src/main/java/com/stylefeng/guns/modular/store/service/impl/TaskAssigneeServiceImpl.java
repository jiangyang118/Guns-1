package com.stylefeng.guns.modular.store.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.constant.ApiConstants;
import com.stylefeng.guns.modular.store.service.ITaskAssigneeService;
import com.stylefeng.guns.modular.store.service.ITaskBaseService;
import com.stylefeng.guns.modular.store.vo.TaskDetailVO;
import com.stylefeng.guns.modular.system.dao.TaskAssigneeMapper;
import com.stylefeng.guns.modular.system.dao.vo.TaskAssigneeVO;
import com.stylefeng.guns.modular.system.model.TaskAssignee;
import com.stylefeng.guns.modular.system.model.TaskBase;

/**
 * <p>
 * 任务接受者 服务实现类
 * </p>
 *
 * @author jackial
 * @since 2018-08-30
 */
@Service
@Transactional
public class TaskAssigneeServiceImpl extends ServiceImpl<TaskAssigneeMapper, TaskAssignee>
        implements ITaskAssigneeService {

    @Resource
    private TaskAssigneeMapper taskAssigneeMapper;

    @Autowired
    private ITaskBaseService taskBaseService;

    @Override
    public TaskBase receive(Integer assigneeId, Integer userId) {
        TaskAssignee task = selectById(assigneeId);
        Assert.isTrue(task.getAssignee().equals(userId), ApiConstants.checkUserCanDo);
        Assert.isTrue(task.getReceive().equals(0), ApiConstants.checkReceive);
        task.setReceive(1);
        task.setReceiveDate(new Date());
        updateById(task);

        TaskBase bTask = taskBaseService.selectById(task.getTaskId());
        bTask.setReceive(bTask.getReceive() + 1);
        taskBaseService.updateById(bTask);
        return bTask;
    }

    @Override
    public TaskBase refuse(Integer assigneeId, Integer userId) {
        TaskAssignee task = selectById(assigneeId);
        Assert.isTrue(task.getAssignee().equals(userId), ApiConstants.checkUserCanDo);
        Assert.isTrue(task.getReceive().equals(0), ApiConstants.checkReceive);
        task.setReceive(2);
        task.setReceiveDate(new Date());
        updateById(task);

        TaskBase bTask = taskBaseService.selectById(task.getTaskId());
        bTask.setRefuse(bTask.getRefuse());
        taskBaseService.updateById(bTask);
        return bTask;
    }

    @Override
    public void sign(Integer assigneeId, Integer userId, String sign) {
        TaskAssignee task = selectById(assigneeId);
        Assert.isTrue(task.getAssignee().equals(userId), ApiConstants.checkUserCanDo);
        task.setAssigneeSign(sign);
        task.setSignDate(new Date());
        updateById(task);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasUser(Integer taskId, Integer userId) {
        Wrapper<TaskAssignee> wrapper = new EntityWrapper<>();
        wrapper = wrapper.andNew("task_id = {0}", taskId);
        wrapper = wrapper.andNew("assignee = {0}", userId);
        List<TaskAssignee> list = selectList(wrapper);
        return !list.isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskAssigneeVO> selectAssignees(Integer taskBaseId) {
        return taskAssigneeMapper.selectAssignees(taskBaseId);
    }

    @Override
    public void save(TaskDetailVO vo, Integer userId) {
        TaskAssignee taskAssignee = selectById(vo.getAssigneeId());
        Assert.isTrue(taskAssignee.getAssignee().equals(userId), ApiConstants.checkUserCanDo);

        taskAssignee.setAssigneeSign(vo.getAssigneeSign());
        taskAssignee.setWithdrawal(vo.getWithdrawal());
        taskAssignee.setSignDate(new Date());
        updateById(taskAssignee);
    }
}
