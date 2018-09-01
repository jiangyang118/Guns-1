/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.vo
 * File Name:ExamineResultVO.java
 * Date:2018年8月29日 下午1:58:30
 * 
 */
package com.stylefeng.guns.modular.store.vo;

import lombok.Data;

/**
 * ClassName: ExamineResultVO <br/>
 * Description: 检查结果vo <br/>
 * Date: 2018年8月29日 下午1:58:30 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Data
public class ExamineResultVO {

    private Integer id;

    /** 检查项结果，0-是 1-否 */
    private Integer result;

    private String memo;

    public ExamineResultVO(Integer id, Integer result, String memo) {
        super();
        this.id = id;
        this.result = result;
        this.memo = memo;
    }

    public ExamineResultVO() {
        super();
    }

}
