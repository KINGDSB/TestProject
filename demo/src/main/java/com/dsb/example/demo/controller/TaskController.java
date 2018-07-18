package com.dsb.example.demo.controller;

import java.util.List;
import java.util.Map;

import com.dsb.example.demo.dataobject.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsb.example.demo.service.TaskService;
import com.dsb.example.demo.utils.ResultVO;
import com.dsb.example.demo.utils.ResultVOUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 任务模块
 * 
 * @author Created by DSB
 * @date 2018/07/06 15:42
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 获取任务中上传的图片
     * 
     * @param storeCode
     * @param taskOperationEventName
     * @return
     */
    @GetMapping("/get-task-picture")
    public List<String> getTaskPicture(String storeCode, String taskOperationEventName) {
        if (StringUtils.isEmpty(storeCode) || StringUtils.isEmpty(taskOperationEventName)) {
            log.error("【获取任务中上传的图片】 参数不存在");
            // throw new Exception("【获取任务中上传的图片】 参数不存在");
        }
        return taskService.getTaskPicture(storeCode, taskOperationEventName);
    }

    /**
     * 获取该门店任务中上传的所有图片 根据事件名称日期分组
     *
     * @param storeCode
     * @return
     */
    @GetMapping("/get-all-task-picture")
    public ResultVO getAllTaskPicture(String storeCode) {
        if (StringUtils.isEmpty(storeCode)) {
            log.error("【获取任务中上传的图片】 参数不存在");
            // throw new YYTApiException(ResultEnum.PARAMS_NULL);
        }
        List<Map<String, Object>> list = taskService.getAllTaskPicture(storeCode);
        return ResultVOUtil.success(list);
    }

    /**
     * 获取该门店任务中上传的所有图片 根据事件名称日期分组
     *
     * @param storeCode
     * @return
     */
    @GetMapping("/get-list-by-object-id")
    public List<Task> getListByObjectId(String storeCode) {
        if (StringUtils.isEmpty(storeCode)) {
            log.error("【获取任务中上传的图片】 参数不存在");
            // throw new YYTApiException(ResultEnum.PARAMS_NULL);
        }
        return taskService.getByObjectId(storeCode);
    }
}