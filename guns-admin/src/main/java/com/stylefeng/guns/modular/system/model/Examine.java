package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 检查项信息
 * </p>
 *
 * @author jackial
 * @since 2018-08-30
 */
@TableName("job_examine")
public class Examine extends BaseEntity<Examine> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private Integer type;
    /**
     * 排序序号
     */
    private Integer orders;
    private Integer parent;
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
    /**
     * 备注
     */
    private String memo;
    private Integer key;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDeprecate() {
        return deprecate;
    }

    public void setDeprecate(Integer deprecate) {
        this.deprecate = deprecate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Examine{" + "id=" + id + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", version="
                + version + ", type=" + type + ", orders=" + orders + ", parent=" + parent + ", code=" + code
                + ", deprecate=" + deprecate + ", item=" + item + ", memo=" + memo + ", key=" + key + "}";
    }
}
