/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.vo
 * File Name:TaskQueryVO.java
 * Date:2018年8月28日 上午11:21:01
 * 
 */
package com.stylefeng.guns.modular.system.dao.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * ClassName: TaskQueryVO <br/>
 * Description: 任务查询vo <br/>
 * Date: 2018年8月28日 上午11:21:01 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@ApiModel("任务查询vo")
@Data
public class TaskQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiParam("任务id")
    private Integer id;
    @ApiParam("任务查询类型， 0-查询任务指派者；1-查询任务接受者")
    private Integer type;
    /**
     * 任务分配者
     */
    private Integer appointer;
    /**
     * 执法者
     */
    private Integer assignee;
    /**
     * 被检查的店铺
     */
    private Integer store;
    /**
     * 任务编号
     */
    private String sn;
    /**
     * 任务状态：0-未检查；1-检查中；2-合格；3-不合格；4-基本合格
     */
    private Integer status;
    /**
     * 查询任务状态，相等条件
     */
    private List<Integer> eqStatus;
    /**
     * 查询任务状态，不相等条件
     */
    private List<Integer> nonEqStatus;
}
