/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.vo
 * File Name:ExamineItemVO.java
 * Date:2018年8月29日 上午11:37:04
 * 
 */
package com.stylefeng.guns.modular.store.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * ClassName: ExamineItemVO <br/>
 * Description: TODO <br/>
 * Date: 2018年8月29日 上午11:37:04 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Data
public class ExamineItemVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 排序序号
     */
    private Integer orders;
    /**
     * 检查项的显示序号，例如1；1.1；1.2.1；
     */
    private String code;
    /**
     * 是否废弃，0-不废弃；1-废弃
     */
    private Integer deprecate;
    /**
     * 检查项每条详情内容
     */
    private String item;
}
