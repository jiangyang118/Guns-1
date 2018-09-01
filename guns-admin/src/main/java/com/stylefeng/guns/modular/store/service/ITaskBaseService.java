package com.stylefeng.guns.modular.store.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.store.vo.TaskBaseVO;
import com.stylefeng.guns.modular.store.vo.TaskDetailVO;
import com.stylefeng.guns.modular.system.dao.vo.TaskListVO;
import com.stylefeng.guns.modular.system.dao.vo.TaskQueryVO;
import com.stylefeng.guns.modular.system.model.TaskBase;

/**
 * <p>
 * 任务基本信息 服务类
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
public interface ITaskBaseService extends IService<TaskBase> {

    Page<TaskListVO> selectPage(TaskQueryVO vo, Page<TaskBase> page);

    Page<TaskListVO> selectTasks(TaskQueryVO vo, Page<TaskListVO> page);

    /**
     * 
     * canStartTask:是否能开始任务 true-可以
     * 
     * @param taskBase
     *            任务基本类
     */
    boolean canStartTask(TaskBase taskBase, Integer userId);

    /**
     * 
     * canEndTask:是否能结束任务 true-可以
     * 
     * @param taskBase
     *            任务基本类
     */
    boolean canEndTask(TaskBase taskBase, Integer userId);

    /**
     * 
     * startTask:开始任务
     * 
     * @param taskBase
     *            任务基本类
     */
    void startTask(TaskBase taskBase, Integer userId);

    /**
     * 
     * canChangeState:能否变更为等待他人接受 <br>
     * 只有状态为未检查状态下才能变更
     * 
     * @param taskBase
     *            任务基本类
     */
    boolean canChangeState(TaskBase taskBase, Integer userId);

    /**
     * 
     * changeState:变更状态为等待他人接受
     * 
     * @param taskBase
     *            任务基本类
     */
    void changeState(TaskBase taskBase, Integer userId);

    /**
     * 
     * approval:合格
     * 
     * @param taskBase
     *            任务基本类
     */
    void approval(TaskBase taskBase, Integer userId);

    /**
     * 
     * refuse:不合格
     * 
     * @param taskBase
     *            任务基本类
     */
    void refuse(TaskBase taskBase, Integer userId);

    /**
     * 
     * almost:基本合格
     * 
     * @param taskBase
     *            任务基本类
     */
    void almost(TaskBase taskBase, Integer userId);

    void create(TaskBaseVO vo, Integer userId);

    TaskDetailVO findByTaskId(Integer taskId);
}
