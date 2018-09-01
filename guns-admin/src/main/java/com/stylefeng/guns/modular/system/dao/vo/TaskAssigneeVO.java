/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.system.dao.vo
 * File Name:TakAssignVO.java
 * Date:2018年8月30日 下午4:14:24
 * 
 */
package com.stylefeng.guns.modular.system.dao.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * ClassName: TakAssignVO <br/>
 * Description: TODO <br/>
 * Date: 2018年8月30日 下午4:14:24 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Data
public class TaskAssigneeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer assigneeId;

    /**
     * 执法者
     */
    private String assigneeName;

    /**
     * 是否接收，0-未查看；1-已接受；2-未接受
     */
    private Integer receive;

    /** 历史核查记录-核查详情的签名 */
    private String assigneeSign;

    /**
     * 是否回避，0-回避；1-不回避
     */
    private Integer withdrawal;
}
