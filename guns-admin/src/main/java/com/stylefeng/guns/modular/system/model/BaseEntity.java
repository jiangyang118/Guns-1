/**
 * Copyright (c) 2018,sunnybs. 
 * All Rights Reserved.
 * 
 * Project Name:guns-admin
 * Package Name:com.stylefeng.guns.modular.system.model
 * File Name:BaseEntity.java
 * Date:2018年8月27日 上午11:43:58
 * 
 */
package com.stylefeng.guns.modular.system.model;

import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * ClassName: BaseEntity <br/>
 * Description: 实体类基类 <br/>
 * Date: 2018年8月27日 上午11:43:58 <br/>
 * <br/>
 * 
 * @author jiang(邮箱)
 * 
 *         修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息<br/>
 * 
 */
@SuppressWarnings("rawtypes")
public abstract class BaseEntity<T extends Model> extends Model {

    private static final long serialVersionUID = 1L;


    /**
     * 店铺id
     */
    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;
    /**
     * 创建日期
     */
    @TableField("create_date")
    protected Date createDate;
    /**
     * 修改日期
     */
    @TableField("modify_date")
    protected Date modifyDate;

    /**
     * 版本号
     */
    @Version
    protected Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "BaseEntity [id=" + id + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
    }

}
