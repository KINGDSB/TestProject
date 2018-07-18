package com.dsb.example.demo.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.dsb.example.demo.dataobject.Task;
import com.dsb.example.demo.repository.TaskRepository;
import com.dsb.example.demo.service.TaskService;
import com.dsb.example.demo.utils.DateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Created by DSB
 * @date 2018/5/15 10:56 .任务
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<String> getTaskPicture(String storeCode, String taskOperationEventName) {
        String sql = " SELECT t4.fvalue FROM t_task t1 \n" +
                "LEFT JOIN t_task t2 ON t2.fparent_id = t1.fid \n" +
                "LEFT JOIN t_task_operation_event t3 ON t3.ftask_id = t2.fid \n" +
                "LEFT JOIN t_task_operation_event_value t4 ON t4.ftask_operation_event_id = t3.fid \n" +
                "WHERE t1.fvalid = 1 AND t1.flevel = 1 AND t1.ftask_type = 1 \n" +
                "AND t1.fobject_id = '" + storeCode + "' AND t4.fid IS NOT NULL \n";

        if (!StringUtils.isEmpty(taskOperationEventName)) {
            sql += "AND t3.fname = '" + taskOperationEventName + "' \n";
        }
        sql += "ORDER BY t4.fcreate_time DESC";

        Query query =  entityManager.createNativeQuery(sql);
        List<String> resultList = query.getResultList();
        Set<String> set = new LinkedHashSet<>();
        resultList.forEach(str -> {
            if (str.contains(",")) {
                set.addAll(Arrays.asList(str.split(",")));
            } else {
                set.add(str);
            }
        });
        return new ArrayList<>(set);
    }

    @Override
    public List<Map<String, Object>> getAllTaskPicture(String storeCode) {
        String sql = " SELECT t3.fname, t4.fvalue, t4.fcreate_time FROM t_task t1 \n" +
                "LEFT JOIN t_task t2 ON t2.fparent_id = t1.fid \n" +
                "LEFT JOIN t_task_operation_event t3 ON t3.ftask_id = t2.fid \n" +
                "LEFT JOIN t_task_operation_event_value t4 ON t4.ftask_operation_event_id = t3.fid \n" +
                "WHERE t1.fvalid = 1 AND t1.flevel = 1 AND t1.ftask_type = 1 AND t4.fvalue LIKE 'http%' \n" +
                "AND t1.fobject_id = '" + storeCode + "' AND t4.fid IS NOT NULL \n";

        sql += "ORDER BY t4.fcreate_time DESC";

        Query query = entityManager.createNativeQuery(sql.toString());
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> resultList = query.getResultList();

        List<Map<String, Object>> result = resultList.stream().collect(Collectors.groupingBy(map -> map.get("fname")))
                .entrySet().stream().map(e -> {
                    Map<String, Object> map = new HashMap<>(2);
                    map.put("name", e.getKey());
                    List<Map<String, Object>> list = (List) e.getValue();
                    List<Map<String, Object>> list1 = list.stream().collect(Collectors.groupingBy(map1 -> DateUtil.formatDateYMd((Date) map1.get("fcreate_time"))))
                            .entrySet().stream().map(e1 -> {
                        Map<String, Object> hashMap = new HashMap<>(2);
                        hashMap.put("date", e1.getKey());
                        List result2 = (List) e1.getValue();
                        List<String> picList = new ArrayList<>();
                        result2.forEach(valueMap -> {
                            Map<String, Object> picMap = (Map<String, Object>) valueMap;
                            String picStr = picMap.get("fvalue").toString();
                            if (picStr.contains(",")) {
                                picList.addAll(Arrays.asList(picStr.split(",")));
                            } else {
                                picList.add(picStr);
                            }
                        });
                        hashMap.put("list", picList);
                        return hashMap;
                    }).collect(Collectors.toList());
                    map.put("list", list1);
                    return map;
                }).collect(Collectors.toList());


        return result;
    }

    @Override
    public List<Task> getByObjectId(String storeCode) {
        return taskRepository.findByObjectId(storeCode);
    }
}
