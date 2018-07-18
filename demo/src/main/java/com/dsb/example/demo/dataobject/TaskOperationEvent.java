package com.dsb.example.demo.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 任务操作事件
 */
@Data
@Entity
@Table(name = "t_task_operation_event")
public class TaskOperationEvent {
    @Id
    @Column(name = "fid")
    private String id;
    /**
     * 任务id
     */
    @Column(name = "ftask_id")
    private String taskId;
    /**
     * 事件类型
     */
    @Column(name = "ftype")
    private String type;
    /**
     * 排序
     */
    @Column(name = "fsort")
    private Integer sort;
    /**
     * 操作事件名称
     */
    @Column(name = "fname")
    private String name;
    /**
     * 是否必须做
     */
    @Column(name = "frequired")
    private String required;
    /**
     * 数量
     */
    @Column(name = "famount")
    private Integer amount;
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
