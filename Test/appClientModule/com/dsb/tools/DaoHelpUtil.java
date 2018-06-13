package com.dsb.tools;

import java.util.Map;

public class DaoHelpUtil {
	/**
	 * 自动生成查询条件
	 * 
	 * @param param
	 *            查询参数
	 * @param clazz
	 *            要查询的对象的Class
	 * @return 查询条件字符串
	 * @throws Exception
	 */
	public static String generateSearchCondition(Map param, Class clazz) throws Exception {
		StringBuilder condition = new StringBuilder("1 = 1 ");
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldTmp = fields[i].get(clazz).toString();
			if (param.get(fieldTmp) != null) {
				String tmp = new StringBuilder().append("val").append(i).toString();
				condition.append(" and ").append(fieldTmp).append(" = :").append(fieldTmp);
			}
		}
		String result = condition.toString();
		return result;
	}

	/**
	 * 生成排序查询的sql,该查询排序基于给出基准点的方式
	 * 
	 * @param param
	 * @param orderCol
	 *            排序列
	 * @param isForward
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static String generateOrderSearchCondition(Map param, String orderCol, Boolean isForward, Class clazz) throws Exception {
		String result = gerOrderSearchCondition(param, orderCol, isForward, clazz, true);
		return result;
	}

	public static String gerOrderSearchCondition(Map param, String orderCol, Boolean isForward, Class clazz, Boolean hasBase) throws IllegalAccessException {
		StringBuilder condition = new StringBuilder("1 = 1 ");
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldTmp = fields[i].get(clazz).toString();
			if (param.get(fieldTmp) != null && !fieldTmp.equals(orderCol)) {
				String tmp = new StringBuilder().append("val").append(i).toString();
				condition.append(" and ").append(fieldTmp).append(" = :").append(fieldTmp);
			}
		}
		if (isForward) {
			if (hasBase) {
				condition.append(" and ").append(orderCol).append(" > :").append(orderCol).append(" ");
			}
			condition.append(" order by ").append(orderCol).append(" asc ");
		} else {
			if (hasBase) {
				condition.append(" and ").append(orderCol).append(" < :").append(orderCol).append(" ");
			}
			condition.append(" order by ").append(orderCol).append(" desc ");
		}
		String result = condition.toString();
		return result;
	}

	/**
	 * 生成不包含基准点的排序方式
	 * 
	 * @param param
	 * @param orderCol
	 * @param isForward
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static String generateNoBaseOrderSearchCondition(Map param, String orderCol, Boolean isForward, Class clazz) throws Exception {
		return gerOrderSearchCondition(param, orderCol, isForward, clazz, false);
	}
}
