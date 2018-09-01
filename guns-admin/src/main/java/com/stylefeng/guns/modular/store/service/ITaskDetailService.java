package com.stylefeng.guns.modular.store.service;

import com.stylefeng.guns.modular.store.vo.TaskDetailVO;
import com.stylefeng.guns.modular.system.model.TaskDetail;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 任务详细信息 服务类
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
public interface ITaskDetailService extends IService<TaskDetail> {

    void save(TaskDetailVO vo);

    boolean hasBaseTask(Integer taskId);

    TaskDetail findByTaskId(Integer taskId);
}
