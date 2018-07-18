package com.dsb.example.demo.service;

import com.dsb.example.demo.dataobject.Task;

import java.util.List;
import java.util.Map;

/**
 * @author Created by DSB
 * @date 2018/5/15 10:56 .任务
 */
public interface TaskService {

    /**
     * 获取任务执行中的图片根据
     * @param storeCode
     * @param taskOperationEventName
     * @return
     */
    List<String> getTaskPicture(String storeCode, String taskOperationEventName);

    /**
     * 获取该门店任务中上传的所有图片 根据事件名称日期分组
     * @param storeCode
     * @return
     */
    List<Map<String, Object>> getAllTaskPicture(String storeCode);

    /**
     * 根据客户获取
     * @param storeCode
     * @return
     */
    List<Task> getByObjectId(String storeCode);

}
