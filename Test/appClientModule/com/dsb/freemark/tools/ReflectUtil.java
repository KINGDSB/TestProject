package com.dsb.freemark.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.dsb.freemark.annotation.ExportField;


/**
 * ��ӳ������
 *
 */
public class ReflectUtil {

	/**
	 * ͨ��������һ��������������ֶ�(��������)
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Field> getPropertyFields(Class clazz){
    	List<Field> list = new ArrayList<Field>();
    	while(!clazz.equals(Object.class)){
    		Field[] fields = clazz.getDeclaredFields();
    		for(Field field : fields){
    			if(!field.getName().equals("serialVersionUID") && 
    			   !field.getName().equals("OBJECT_NAME")){
    				list.add(field);
    			}
    		}
    		clazz = clazz.getSuperclass();
    	}
    	return list;
    }
	public static List<Field> getExportFields(Class clazz){
    	List<Field> list = new ArrayList<Field>();
    	while(!clazz.equals(Object.class)){
    		Field[] fields = clazz.getDeclaredFields();
    		for(Field field : fields){
    			ExportField export = field.getAnnotation(ExportField.class);
    			if(export!=null && !field.getName().equals("serialVersionUID")){
    				list.add(field);
    			}
    		}
    		clazz = clazz.getSuperclass();
    	}
    	List<Field> list2 = new ArrayList<Field>();
    	while(list.size()>0){
    		int minIndex = 2000;
        	Field minField = null;
    		for(Field field  :  list){
        		ExportField export = field.getAnnotation(ExportField.class);
        		int index = export.index();
        		if(index<=minIndex){
        			minIndex = index;
        			minField = field;
        		}
        	}
    		list2.add(minField);
    		list.remove(minField);
    	}
    	return list2;
    }
	
	/**
	 * ����������ַ���
	 * @param object
	 * @return
	 */
	public static String reflectObjectToString(Object object){
		return reflectObjectToLimitString(object, 0);
	}
	/**
	 * ����������г������Ƶ��ַ�������󳤶�Ϊ500
	 * @param object
	 * @return
	 */
	public static String reflectObjectToLimitString(Object object){
		return reflectObjectToLimitString(object, 500);
	}
	/**
	 * ����������г������Ƶ��ַ���
	 * @param object
	 * @param maxLength
	 * @return
	 */
	public static String reflectObjectToLimitString(Object object,int maxLength){
		String value = null;
		if(object!=null){
			Class<?> clazz = object.getClass();
			//������û��Զ�������
			if(clazz.getName().startsWith("com.navidata")){
				try {
					object.getClass().getDeclaredMethod("toString");
					value = object.toString();
				} catch (Exception e) {
					value = ToStringBuilder.reflectionToString(object);
				}
			}else if(clazz.isArray()){//�������������
				Object[] objects = (Object[]) object;
				Class<?> componentType = clazz.getComponentType();
				value = componentType.getName()+"����:";
				value += "[";
				for(Object obj : objects){
					value += reflectObjectToLimitString(obj,maxLength)+",";
				}
				if(value.length()>1){
					value = value.substring(0,value.length()-1);
				}
				value += "]";
			}else{
				value = object.toString();
			}
		}
		if(value!=null && value.length()>maxLength - 7 && maxLength>7){
			value = value.substring(0,maxLength-7)+"...";
		}
		return value;
	}
}
