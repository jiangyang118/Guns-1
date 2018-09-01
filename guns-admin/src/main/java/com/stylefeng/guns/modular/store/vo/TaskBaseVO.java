/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.vo
 * File Name:TaskBaseVO.java
 * Date:2018年8月31日 下午6:25:04
 * 
 */
package com.stylefeng.guns.modular.store.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * ClassName: TaskBaseVO <br/>
 * Description: 创建任务vo <br/>
 * Date: 2018年8月31日 下午6:25:04 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Data
public class TaskBaseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始日期
     */
    private Date beginDate;
    /**
     * 结束日期
     */
    private Date endDate;
    /**
     * 任务类型
     */
    private Integer type;
    /**
     * 被检查的店铺列表
     */
    private List<Integer> stores;
    /**
     * 分配的任务执行者
     */
    private List<Integer> assignees;
}
