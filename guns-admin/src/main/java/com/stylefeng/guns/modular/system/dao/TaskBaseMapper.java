package com.stylefeng.guns.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.dao.vo.TaskListVO;
import com.stylefeng.guns.modular.system.dao.vo.TaskQueryVO;
import com.stylefeng.guns.modular.system.model.TaskBase;

/**
 * <p>
 * 任务基本信息 Mapper 接口
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
public interface TaskBaseMapper extends BaseMapper<TaskBase> {

    List<TaskListVO> selectTasks(TaskQueryVO vo, @Param("page") Page<TaskListVO> page);
}
