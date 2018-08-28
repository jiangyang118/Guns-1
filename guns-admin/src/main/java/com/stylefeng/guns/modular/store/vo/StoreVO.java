/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.store.vo
 * File Name:StoreVO.java
 * Date:2018年8月27日 下午2:20:08
 * 
 */
package com.stylefeng.guns.modular.store.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * ClassName: StoreVO <br/>
 * Description: TODO <br/>
 * Date: 2018年8月27日 下午2:20:08 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@Data
public class StoreVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 店铺监管类型,0供应商,1餐饮商
     */
    private String storeTypeStr;
    /**
     * 店铺门头名称
     */
    private String name;
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
}
