package com.stylefeng.guns.modular.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.store.service.ITaskDetailService;
import com.stylefeng.guns.modular.store.vo.TaskDetailVO;
import com.stylefeng.guns.modular.system.dao.TaskDetailMapper;
import com.stylefeng.guns.modular.system.model.TaskDetail;

/**
 * <p>
 * 任务详细信息 服务实现类
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
@Service
@Transactional
public class TaskDetailServiceImpl extends ServiceImpl<TaskDetailMapper, TaskDetail> implements ITaskDetailService {

    @Override
    public void save(TaskDetailVO vo) {
        TaskDetail task = new TaskDetail();
        task.setStoreSign(vo.getStoreSign());
        task.setItem(vo.getItem());
        task.setMemo(vo.getMemo());
        task.setCreateDate(new Date());
        task.setModifyDate(new Date());
        task.setTaskId(vo.getId());
        insert(task);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasBaseTask(Integer taskId) {
        Wrapper<TaskDetail> wrapper = new EntityWrapper<>();
        wrapper = wrapper.andNew("task_id = {0}", taskId);
        List<TaskDetail> list = selectList(wrapper);
        return !list.isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDetail findByTaskId(Integer taskId) {
        Wrapper<TaskDetail> wrapper = new EntityWrapper<>();
        wrapper = wrapper.andNew("task_id = {0}", taskId);
        return selectOne(wrapper);
    }

}
