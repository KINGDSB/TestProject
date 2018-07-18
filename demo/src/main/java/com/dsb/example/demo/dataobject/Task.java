package com.dsb.example.demo.dataobject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 任务
 */
@Data
@Entity
@Table(name = "t_task")
public class Task {
    @Id
    @Column(name = "fid")
    private String id;
    /**
     * 任务名称
     */
    @Column(name = "fname")
    private String name;
    /**
     * 任务编号
     */
    @Column(name = "fnumber")
    private String number;
    /**
     * 任务类型
     */
    @Column(name = "ftask_type")
    private String taskType;
    /**
     * 基础id
     */
    @Column(name = "fbase_id")
    private String baseId;
    /**
     * 节点级别
     */
    @Column(name = "flevel")
    private Integer level;
    /**
     * 执行人
     */
    @Column(name = "fexecutor")
    private String executor;
    /**
     * 审核人
     */
    @Column(name = "fauditor")
    private String auditor;
    /**
     * 对象id 一般是客户编码
     */
    @Column(name = "fobject_id")
    private String objectId;
    /**
     * 开始时间
     */
    @Column(name = "fbegin_time")
    private Date beginTime;
    /**
     * 结束时间
     */
    @Column(name = "fend_time")
    private Date endTime;
    /**
     * 实际开始时间
     */
    @Column(name = "fture_begin_time")
    private Date tureBeginTime;
    /**
     * 实际结束时间
     */
    @Column(name = "fture_end_time")
    private Date tureEndTime;
    /**
     * 排序
     */
    @Column(name = "fsort")
    private Integer sort;
    /**
     * 父级id
     */
    @Column(name = "fparent_id")
    private String parentId;
    /**
     * 周期id 周期相关的 没人用的功能
     */
    @Column(name = "fcycle_id")
    private String cycleId;
    /**
     * 任务优先级
     */
    @Column(name = "fpriority")
    private Integer priority;
    /**
     * 是否可跳过
     */
    @Column(name = "fcan_skip")
    private String canSkip;
    /**
     * 节点类型
     */
    @Column(name = "fnode_type")
    private String nodeType;
    /**
     * 提醒对象id 没人用的功能
     */
    @Column(name = "fremind_ids")
    private String remindIds;
    /**
     * 状态
     */
    @Column(name = "fstatus")
    private String status;
    /**
     * 完成类型
     */
    @Column(name = "ffinish_type")
    private String finishType;
    /**
     * 创建人
     */
    @Column(name = "fcreater")
    private String creater;
    /**
     * 更新人
     */
    @Column(name = "fchanger")
    private String changer;
    /**
     * 操作类型
     */
    @Column(name = "foperation_type")
    private String operationType;
    /**
     * 流程类型
     */
    @Column(name = "fprocess_type")
    private String processType;
    /**
     * 模板id
     */
    @Column(name = "ftemplate_id")
    private String templateId;
    /**
     * 创建时所使用的模板id
     */
    @Column(name = "fdepartment_id")
    private String departmentId;
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
     * 任务描述
     */
    @Column(name = "fdescription")
    private String description;
}
