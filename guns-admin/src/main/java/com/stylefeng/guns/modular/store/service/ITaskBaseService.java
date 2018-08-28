package com.stylefeng.guns.modular.store.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.store.vo.TaskListVO;
import com.stylefeng.guns.modular.store.vo.TaskQueryVO;
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
}
