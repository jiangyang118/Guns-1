package com.stylefeng.guns.modular.store.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.store.vo.TaskDetailVO;
import com.stylefeng.guns.modular.system.dao.vo.TaskAssigneeVO;
import com.stylefeng.guns.modular.system.model.TaskAssignee;
import com.stylefeng.guns.modular.system.model.TaskBase;

/**
 * <p>
 * 任务接受者 服务类
 * </p>
 *
 * @author jackial
 * @since 2018-08-30
 */
public interface ITaskAssigneeService extends IService<TaskAssignee> {

    /**
     * 
     * receive:接受任务 并更新接受者人数
     * 
     * @param assigneeId
     *            任务id
     */
    TaskBase receive(Integer assigneeId, Integer userId);

    /**
     * 
     * refuse:拒绝任务 并更新拒绝者人数
     * 
     * @param assigneeId
     *            任务id
     */
    TaskBase refuse(Integer assigneeId, Integer userId);

    /**
     * 
     * sign:签名
     * 
     * @param assigneeId
     *            任务id
     */
    void sign(Integer assigneeId, Integer userId, String sign);

    boolean hasUser(Integer taskId, Integer userId);

    List<TaskAssigneeVO> selectAssignees(Integer taskBaseId);

    void save(TaskDetailVO vo, Integer userId);

}
