package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
     * 任务类型
     */
    private Integer type;
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

    @TableField("inspec_date")
    private Date inspectDate;
    @TableField("inspec_done_date")
    private Date inspectDoneDate;
    /** 指派人数 */
    private Integer assignee;
    /** 接受任务人数 */
    private Integer receive;
    /** 拒绝任务人数 */
    private int refuse;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Date getInspectDoneDate() {
        return inspectDoneDate;
    }

    public void setInspectDoneDate(Date inspectDoneDate) {
        this.inspectDoneDate = inspectDoneDate;
    }

    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
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

    public int getAssignee() {
        return assignee;
    }

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public int getRefuse() {
        return refuse;
    }

    public void setRefuse(int refuse) {
        this.refuse = refuse;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
