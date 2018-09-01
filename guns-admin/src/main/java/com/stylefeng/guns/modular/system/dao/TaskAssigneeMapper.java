package com.stylefeng.guns.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.dao.vo.TaskAssigneeVO;
import com.stylefeng.guns.modular.system.model.TaskAssignee;

/**
 * <p>
 * 任务接受者 Mapper 接口
 * </p>
 *
 * @author jackial
 * @since 2018-08-30
 */
public interface TaskAssigneeMapper extends BaseMapper<TaskAssignee> {

    List<TaskAssigneeVO> selectAssignees(@Param("task") Integer taskBaseId);
}
