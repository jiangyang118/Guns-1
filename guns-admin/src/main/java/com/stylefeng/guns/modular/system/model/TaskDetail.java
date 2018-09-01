package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 任务详细信息
 * </p>
 *
 * @author jackial
 * @since 2018-08-27
 */
@TableName("job_task_detail")
public class TaskDetail extends BaseEntity<TaskDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 任务基本信息id
     */
    @TableField("task_id")
    private Integer taskId;

    /**
     * 被检查者签名
     */
    @TableField("store_sign")
    private String storeSign;

    /**
     * 检查项详情
     */
    private String item;
    /**
     * 备注
     */
    private String memo;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }


    public String getStoreSign() {
        return storeSign;
    }

    public void setStoreSign(String storeSign) {
        this.storeSign = storeSign;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
