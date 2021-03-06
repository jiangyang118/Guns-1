package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 店铺
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
@TableName("str_store_base")
public class StoreBase extends BaseEntity<StoreBase> {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺监管类型,0供应商,1餐饮商
     */
    @TableField("store_type")
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

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
