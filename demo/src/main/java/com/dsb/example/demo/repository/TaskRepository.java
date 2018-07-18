package com.dsb.example.demo.repository;

import com.dsb.example.demo.dataobject.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Created by SY
 * @date 2018/6/26 13:45
 * .任务数据交互层
 */
public interface TaskRepository extends JpaRepository<Task, String>, JpaSpecificationExecutor<Task> {

    /**
     * 命名查询 根据objectId查询任务
     * @param storeCode
     * @return
     */
    List<Task> findByObjectId(String storeCode);
    
}
