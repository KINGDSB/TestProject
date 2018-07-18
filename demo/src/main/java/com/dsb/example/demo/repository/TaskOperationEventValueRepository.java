package com.dsb.example.demo.repository;

import com.dsb.example.demo.dataobject.TaskOperationEventValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Created by SY
 * @date 2018/6/26 13:45
 * .任务数据交互层
 */
public interface TaskOperationEventValueRepository extends JpaRepository<TaskOperationEventValue, String>, JpaSpecificationExecutor<TaskOperationEventValue> {

}
