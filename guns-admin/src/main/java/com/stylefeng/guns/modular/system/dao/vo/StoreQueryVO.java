/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.system.dao.vo
 * File Name:StoreQueryVO.java
 * Date:2018年8月31日 下午5:58:01
 * 
 */
package com.stylefeng.guns.modular.system.dao.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * ClassName: StoreQueryVO <br/>
 * Description: 店铺查询vo <br/>
 * Date: 2018年8月31日 下午5:58:01 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@ApiModel("店铺查询vo")
@Data
public class StoreQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺id
     */
    protected Integer id;
    /**
     * 店铺监管类型,0供应商,1餐饮商
     */
    private Integer storeType;
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
}
