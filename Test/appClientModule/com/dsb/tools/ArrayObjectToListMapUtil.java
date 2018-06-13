package com.dsb.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsb.test.RRR;

public class ArrayObjectToListMapUtil {
	/***
	 * 
	 * 用于将对象数组 转换成list<Map> 2016/3/12 还要改造 一下 传一个字符串过来也可以完成的才行
	 * 
	 * 参数二：指定要返回的字段，如果没有则默认执行 暂时没有用到这个参数 这个方法应该放到baseDao下调用
	 * 
	 * */
	public static Map ArrayObjectToMap(Object[] objs,
			Map<String, Object> map) throws Exception {

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < objs.length; i++) {
			Object obj = objs[i];
			Method[] method = obj.getClass().getDeclaredMethods();
			Map<String, Object> attributeMap = new HashMap<String, Object>();

			for (int j = 0; j < method.length; j++) {
				String methodName = method[j].getName();
				if (methodName.startsWith("set")
						|| methodName.equals("getObjectTypeStatic")
						|| methodName.lastIndexOf("InitialValue") > -1) {
					continue;
				}

				// 只调用get的方法
				int index = method[j].getName().indexOf("get");
				if (index == 0) {
					String attributeName = (String) method[j].getName()
							.replaceFirst("get", "");
					char[] chars = new char[1];
					chars[0] = attributeName.charAt(0);
					String temp = new String(chars);

					// 把getXddX 变成xddXdd 驼峰
					attributeName = attributeName.replaceFirst(temp,
							temp.toLowerCase());

					if (null != method[j].invoke(obj)) {
						String value = method[j].invoke(obj).toString();

						attributeMap.put(attributeName, value);
					}

				}

			}
			listMap.add(attributeMap);
		}

		return JsonUtil.getMapFromJsObject(JsonUtil.getJsonFromList(listMap.toArray()));
	}

	/***
	 * 
	 * 用于将对象数组 转换成list<Map> add by chenzx 2016/3/12 还要改造 一下 传一个字符串过来也可以完成的才行
	 * 
	 * 
	 * */
	public static List<Map<String, Object>> ArrayObjectToListMap(Object[] objs)
			throws Exception {

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < objs.length; i++) {
			Object obj = objs[i];
			Method[] method = obj.getClass().getDeclaredMethods();
			Map<String, Object> attributeMap = new HashMap<String, Object>();

			for (int j = 0; j < method.length; j++) {
				String methodName = method[j].getName();
				if (methodName.startsWith("set")
						|| methodName.equals("getObjectTypeStatic")
						|| methodName.lastIndexOf("InitialValue") > -1) {
					continue;
				}

				// 只调用get的方法
				int index = method[j].getName().indexOf("get");
				if (index == 0) {
					String attributeName = (String) method[j].getName()
							.replaceFirst("get", "");
					char[] chars = new char[1];
					chars[0] = attributeName.charAt(0);
					String temp = new String(chars);

					// 把getXddX 变成xddXdd 驼峰
					attributeName = attributeName.replaceFirst(temp,
							temp.toLowerCase());

					if (null != method[j].invoke(obj)) {
						String value = method[j].invoke(obj).toString();
						attributeMap.put(attributeName, value);

						/*
						 * Object value = method[j].invoke(obj); if(value
						 * instanceof Long){
						 * attributeMap.put(attributeName,value); }else{
						 * attributeMap.put(attributeName,value.toString());
						 * 
						 * }
						 */

					}

				}

			}
			listMap.add(attributeMap);
		}

		return listMap;
	}

	
	
	public static <T> List<T> getListObjFromListMap(Class<T> t,List<Map> list) throws Exception{
		
		List<T> rtnList = new ArrayList<T>();
		
		Field[] fields = t.getFields();
		Method[] methods = t.getMethods();
		
		for (Map map : list) {
			//必须有无参的构造方法
			T obj = t.newInstance();
			String prefix = "set"; 
			for (Object key : map.keySet()) {
				String name = key.toString();
				String i = String.valueOf(name.charAt(0));
				String methodName = prefix + i.toUpperCase() + name.substring(1);
				
				//name.replaceFirst(regex, replacement)				
				
				for (Method method : methods) {
//					methodName.equalsIgnoreCase(method.getName())
					if (methodName.equals(method.getName())) {
						
						Object value = map.get(key);
						
						if (value instanceof Integer) {
							method.invoke(obj, Integer.valueOf(value.toString()));
						}else if (value instanceof Double) {
							method.invoke(obj, Double.valueOf(value.toString()));
						} else if (value instanceof Float) {
							method.invoke(obj, Float.valueOf(value.toString()));
						} else if (value instanceof Long) {
							method.invoke(obj, Long.valueOf(value.toString()));
						} else if (value instanceof Boolean) {
							method.invoke(obj, Boolean.valueOf(value.toString()));
						} else if (value instanceof Timestamp) {
							method.invoke(obj, DateTime.get24Timestamp(value.toString()));
						}else if (value instanceof Date) {
							Date date = Util.parseDateTime(value.toString());
							method.invoke(obj, date);
						}else if (value instanceof String) {
							method.invoke(obj, value.toString());
						} 
					}
				}
			}
			rtnList.add(obj);
		}
		
		return rtnList;
	}
	
	public static void main(String[] args) {
		List<Map> listMap = new ArrayList<Map>();

		try {
			for (int i = 0; i < 2; i++) {
				Map map = new HashMap();
				
				map.put("id", i);
				map.put("name", "张"+i);
				map.put("age", (i+10)+"");
				map.put("birthday", DateTime.addDay(new Timestamp(new Date().getTime()), i));
				
				listMap.add(map);
			}
		
			List<RRR> list = getListObjFromListMap(RRR.class, listMap);
			for (RRR rrr : list) {
				System.out.println("id:"+rrr.getId()+"\tname:"+rrr.getName()+"\tbirthday:"+rrr.getBirthday()+"\tage:"+rrr.getAge());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
