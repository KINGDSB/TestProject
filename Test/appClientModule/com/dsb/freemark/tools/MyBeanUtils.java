package com.dsb.freemark.tools;

import java.lang.reflect.Method;

public class MyBeanUtils {

	public static void setProperty(Object object, String property,Object value) {
		String methodName = "set"+property.substring(0,1).toUpperCase();
		if(property.length()>1){
			methodName += property.substring(1);
		}
		String[] methodNames = new String[1];
		methodNames[0]=methodName;
		setMethodInvoke(object,methodNames,value);
	}
	public static void setMethodInvoke(Object object, String[] methodNames,
			Object value) {
		Method[] methods = object.getClass().getMethods();
		for(String methodName :methodNames){
			for(Method method :methods){
				if(method.getName().equals(methodName)){
					try {
						method.invoke(object, new Object[]{value});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	public static Object getProperty(Object object, String property) {
		String methodName = "get"+property.substring(0,1).toUpperCase();
		if(property.length()>1){
			methodName += property.substring(1);
		}
		String[] methodNames = new String[2];
		methodNames[0]=methodName;
		methodNames[1]=methodName.replaceFirst("get", "is");
		return getMethodInvoke(object,methodNames);
	}
	public static Object getMethodInvoke(Object object, String[] methodNames) {
		Method[] methods = object.getClass().getMethods();
		for(String methodName :methodNames){
			for(Method method :methods){
				if(method.getName().equals(methodName)){
					try {
						return method.invoke(object, new Object[]{});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
