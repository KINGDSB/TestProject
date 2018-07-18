package com.dsb.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dsb.example.demo.dataobject.TaskOperationEvent;

/**
 * @author Created by SY
 * @date 2018/6/26 13:45
 * .任务数据交互层
 */
public interface TaskOperationEventRepository  extends JpaRepository<TaskOperationEvent, String>, JpaSpecificationExecutor<TaskOperationEvent> {

}
