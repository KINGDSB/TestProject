package com.dsb.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.dsb.entity.MsgObject;
import com.dsb.entity.MsgObject2;

/**
 * There is no royal road to learning.
 * Description:提交实体对象中的null赋值
 * Created by 贤领·周 on 2018年04月10日 15:26
 */
public class ObjectUtils {
    /**
     * 将目标源中不为空的字段过滤，将数据库中查出的数据源复制到提交的目标源中
     * (把 source 的值copy到target为null的属性上)
     * @param source 用id从数据库中查出来的数据源
     * @param target 提交的实体，目标源
     */
    public static void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNoNullProperties(target));
    }

    /**
     * 将 source 中不为null的值copy到 target 中
     * @param source
     * @param target
     */
    public static void copyNotNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullProperties(source));
    }

    /**
     * @param target 目标源数据
     * @return 将目标源中不为空的字段取出
     */
    private static String[] getNoNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        return Arrays.stream(srcBean.getPropertyDescriptors()).filter(p -> srcBean.getPropertyValue(p.getName()) != null).map(PropertyDescriptor::getName).collect(Collectors.toSet()).toArray(new String[0]);
    }

    /**
     * @param target 目标源数据
     * @return 将目标源中为空的字段取出
     */
    private static String[] getNullProperties(Object target) {
        BeanWrapper srcBean = new BeanWrapperImpl(target);
        return Arrays.stream(srcBean.getPropertyDescriptors()).filter(p -> srcBean.getPropertyValue(p.getName()) == null).map(PropertyDescriptor::getName).collect(Collectors.toSet()).toArray(new String[0]);
    }
    
    /**
     * @Title map2Bean 
     * @Description 
     * @param list
     * @param clazz
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> List<T> map2Bean(List<Map> list, Class clazz) {
        List<T> listT = new ArrayList<>();
        try {
            T t = (T)clazz.newInstance();
            List<Field> fields = getPropertyFields(clazz);
            list.forEach(map -> {
                fields.forEach(field -> {
//                    try {
//                        Method method = clazz.getMethod("get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1), field.getType());
//                        method.invoke(t, map.get(field.getName()));
//                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//                        e.printStackTrace();
//                    }
                    try {
//                      field.setAccessible(true);
                        field.set(t, map.get(field.getName()));
//                      field.setAccessible(false);
                    } catch (SecurityException | IllegalAccessException | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                });
                listT.add(t);
            });
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return listT;
    }
    
    /**
     * 通过反射获得一个类的所有属性字段(包含父类)
     * 
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Field> getPropertyFields(Class clazz) {
        List<Field> list = new ArrayList<Field>();
        while (!clazz.equals(Object.class)) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("serialVersionUID") && !field.getName().equals("OBJECT_NAME")) {
                    list.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return list;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T forceChange(Object value) {
        return (T)value;
    }
    
    public static void main(String[] args) {
        MsgObject m1 = new MsgObject();
        m1.setId(1);
//        
//        MsgObject2 m2 = new MsgObject2();
////        BeanUtils.copyProperties(m1, m2);
//        copyNotNullProperties(m1, m2);
//        
//        System.out.println(m2);
        
        
        String[] props = getNullProperties(m1);
        System.out.println(props);
        
    }
    
}
