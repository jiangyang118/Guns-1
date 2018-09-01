package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 任务接受者
 * </p>
 *
 * @author jackial
 * @since 2018-08-30
 */
@TableName("job_task_assignee")
public class TaskAssignee extends BaseEntity<TaskAssignee> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务执行者
     */
    private Integer assignee;
    /**
     * 任务基本信息id
     */
    @TableField("task_id")
    private Integer taskId;
    /**
     * 是否接收，0-未查看；1-已接受；2-未接受
     */
    private Integer receive;
    /**
     * 执法者签名
     */
    @TableField("assignee_sign")
    private String assigneeSign;
    /**
     * 是否回避，0-回避；1-不回避
     */
    private Integer withdrawal;
    
    @TableField("sign_date")
    private Date signDate;

    @TableField("receive_date")
    private Date receiveDate;

    public Integer getAssignee() {
        return assignee;
    }

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public Integer getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Integer withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public String getAssigneeSign() {
        return assigneeSign;
    }

    public void setAssigneeSign(String assigneeSign) {
        this.assigneeSign = assigneeSign;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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
