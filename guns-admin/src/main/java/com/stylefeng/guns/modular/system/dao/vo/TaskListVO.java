/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.vo
 * File Name:TaskListVO.java
 * Date:2018年8月28日 上午11:21:12
 * 
 */
package com.stylefeng.guns.modular.system.dao.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * ClassName: TaskListVO <br/>
 * Description: 任务列表vo <br/>
 * Date: 2018年8月28日 上午11:21:12 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Data
@ApiModel
public class TaskListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date createDate;
    /**
     * 开始日期
     */
    private Date beginDate;
    /**
     * 结束日期
     */
    private Date endDate;
    /**
     * 任命者
     */
    private String name;

    /**
     * 店铺门头名称
     */
    private String storeName;
    /**
     * 生产经营地址
     */
    private String address;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 店铺电话
     */
    private String phone;
    /**
     * 任务编号
     */
    private String sn;
    /**
     * 任务状态：0-未检查；1-检查中；2-合格；3-不合格；4-基本合格
     */
    private Integer status;
    private String statusName;
    /**
     * 通知被检查店铺的状态：0-未通知；1-已通知
     */
    private Integer notiStatus;
    private String notiStatusName;

    private List<TaskAssigneeVO> assigneeVOs;
}
