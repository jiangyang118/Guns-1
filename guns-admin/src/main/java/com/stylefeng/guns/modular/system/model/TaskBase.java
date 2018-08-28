package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 任务基本信息
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
@TableName("job_task_base")
public class TaskBase extends BaseEntity<TaskBase> {

    private static final long serialVersionUID = 1L;

    /**
     * 开始日期
     */
    @TableField("begin_date")
    private Date beginDate;
    /**
     * 结束日期
     */
    @TableField("end_date")
    private Date endDate;
    /**
     * 任命者
     */
    private Integer appointer;
    /**
     * 执法者1
     */
    private Integer assignee1;
    /**
     * 执法者2
     */
    private Integer assignee2;
    /**
     * 被检查的店铺
     */
    private Integer store;
    /**
     * 任务编号
     */
    private String sn;
    /**
     * 任务状态：0-未检查；1-检查中；2-合格；3-不合格；4-基本合格
     */
    private Integer status;
    /**
     * 通知被检查店铺的状态：0-未通知；1-已通知
     */
    @TableField("noti_status")
    private Integer notiStatus;

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getAppointer() {
        return appointer;
    }

    public void setAppointer(Integer appointer) {
        this.appointer = appointer;
    }

    public Integer getAssignee1() {
        return assignee1;
    }

    public void setAssignee1(Integer assignee1) {
        this.assignee1 = assignee1;
    }

    public Integer getAssignee2() {
        return assignee2;
    }

    public void setAssignee2(Integer assignee2) {
        this.assignee2 = assignee2;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNotiStatus() {
        return notiStatus;
    }

    public void setNotiStatus(Integer notiStatus) {
        this.notiStatus = notiStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TaskBase{" + "id=" + id + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", version="
                + version + ", beginDate=" + beginDate + ", endDate=" + endDate + ", appointer=" + appointer
                + ", assignee1=" + assignee1 + ", assignee2=" + assignee2 + ", store=" + store + ", sn=" + sn
                + ", status=" + status + ", notiStatus=" + notiStatus + "}";
    }
}
