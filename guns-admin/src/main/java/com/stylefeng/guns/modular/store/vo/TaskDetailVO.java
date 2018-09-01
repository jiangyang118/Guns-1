/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.vo
 * File Name:TaskDetailVO.java
 * Date:2018年8月28日 上午11:21:20
 * 
 */
package com.stylefeng.guns.modular.store.vo;

import java.io.Serializable;
import java.util.List;

import com.stylefeng.guns.modular.system.dao.vo.TaskAssigneeVO;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * ClassName: TaskDetailVO <br/>
 * Description: 任务详情vo <br/>
 * Date: 2018年8月28日 上午11:21:20 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Data
public class TaskDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiParam(value = "任务id", required = true)
    private Integer id;
    @ApiParam(value = "店铺签名数据", required = true)
    private String storeSign;
    @ApiParam(value = "备注信息", required = false)
    private String memo;
    @ApiParam(value = "检查项详情", required = true)
    private String item;

    @ApiParam(value = "任务指派表id", required = true)
    private Integer assigneeId;
    @ApiParam(value = "执法者签名数据", required = true)
    private String assigneeSign;
    @ApiParam(value = "是否回避，0-回避；1-不回避", required = true)
    private Integer withdrawal;

    /** 历史核查记录-核查详情的签名 */
    private List<TaskAssigneeVO> assigneeVOs;
}
