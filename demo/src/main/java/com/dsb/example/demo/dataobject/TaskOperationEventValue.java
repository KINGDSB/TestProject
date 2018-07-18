package com.dsb.example.demo.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 任务操作事件值
 */
@Data
@Entity
@Table(name = "t_task_operation_event_value")
public class TaskOperationEventValue {
    @Id
    @Column(name = "fid")
    private String id;
    /**
     * 任务操作事件id
     */
    @Column(name = "ftask_operation_event_id")
    private String taskOperationEventId;
    /**
     * 值
     */
    @Column(name = "fvalue")
    private String value;
    /**
     * 创建时间
     */
    @Column(name = "fcreate_time")
    private String createTime;
    /**
     * 修改时间
     */
    @Column(name = "fupdate_time")
    private String updateTime;
    /**
     * 有效性。1有效，0无效
     */
    @Column(name = "fvalid")
    private Integer valid;
    /**
     * 备注
     */
    @Column(name = "fremark")
    private String remark;
}
