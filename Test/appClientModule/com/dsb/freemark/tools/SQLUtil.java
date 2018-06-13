package com.dsb.freemark.tools;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 处理SQL的工具类，主要用于JDBC
 * 
 * @author 沈坚丰
 *
 */
public class SQLUtil {

	public static final String YYYY_MM_DD_HH24MISSFF = "yyyy-MM-dd hh24:mi:ss.ff";
	public static final String YYYY_MM_DD_HH24MISS = "yyyy-MM-dd hh24:mi:ss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	/**
	 * 日期转化为sql
	 * 
	 * @param date
	 * @return
	 */
	public static String date2SqlString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return "to_date('" + dateFormat.format(date) + "','yyyy-MM-dd')";
	}

	public static String wholeDate2SqlString(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(Utils.YYYY_MM_DD_HHMMSS);
		return "to_date('" + dateFormat.format(date) + "','yyyy-MM-dd hh24:mi:ss')";
	}

	public static String timestamp2SqlString(Timestamp date) {
		if (date == null)
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(Utils.YYYY_MM_DD_HHMMSSSSSS);
		return "to_timestamp('" + dateFormat.format(date) + "','yyyy-MM-dd hh24:mi:ss.ff')";
	}

	public static String getSqlDateString() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return "'" + dateFormat.format(date) + "'";
	}

	public static String array2SqlString(Number[] array) {
		if (array == null || array.length == 0)
			return "(-0.00001)";
		String in = "(";
		for (Number number : array) {
			in += number + ",";
		}
		in = in.substring(0, in.length() - 1) + ")";
		return in;
	}

	public static String buildInParam(List<String> values) {
		String in = "";
		for (String value : values) {
			in += string2SqlString(value) + ",";
		}
		if (in.length() > 0) {
			in = in.substring(0, in.length() - 1);
		}
		return in;
	}

	public static String convertArray(Serializable[] array) {
		if (array == null || array.length == 0)
			return "(null)";
		String in = "(";
		Class<?> type = array.getClass().getComponentType();
		for (Serializable id : array) {
			if (type.equals(Long.class) || type.equals(Integer.class)) {
				in += id + ",";
			} else if (type.equals(String.class)) {
				in += "'" + id + "',";
			}
		}
		in = in.substring(0, in.length() - 1) + ")";
		return in;
	}

	public static String list2SqlString(List<? extends Number> list) {
		if (list == null || list.size() == 0)
			return "(-0.00001)";
		String in = "(";
		for (Number number : list) {
			in += number + ",";
		}
		in = in.substring(0, in.length() - 1) + ")";
		return in;
	}

	public static String buildSQLValues(Object object) {
		String temp = null;
		if (object != null) {
			if (object instanceof Number) {
				Double value = Double.valueOf(object.toString());
				temp = Utils.doubleToString(value);
			} else if (object instanceof Date) {
				temp = date2SqlString((Date) object);
			} else {
				temp = string2SqlString(object.toString());
			}
		}
		return temp;
	}

	public static String fieldToPropertyName(String fieldName) {
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < fieldName.length(); i++) {
			char cur = fieldName.charAt(i);
			if (cur == '_') {
				flag = true;

			} else {
				if (flag) {
					sb.append(Character.toUpperCase(cur));
					flag = false;
				} else {
					sb.append(Character.toLowerCase(cur));
				}

			}
		}
		return sb.toString();
	}

	public static String tableToClassName(String tableName) {
		return fieldToPropertyName(tableName);
	}

	/**
	 * 属性转字段
	 * 
	 * @param prepertyName
	 * @return
	 */
	public static String propertyToFieldName(String prepertyName) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < prepertyName.length(); i++) {
			char cur = prepertyName.charAt(i);
			if (Character.isUpperCase(cur)) {
				if (i != 0)
					sb.append("_");
				sb.append(cur);
			} else {
				sb.append(cur);
			}
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 属性转字段
	 * 
	 * @param prepertyName
	 * @return
	 */
	public static String prefixPropertyToFieldName(String prepertyName) {

		return "f" + prepertyName;
	}

	public static String classToTableName(String className) {
		return propertyToFieldName(className);
	}

	public static String classTatbleName(String className) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < className.length(); i++) {
			char cur = className.charAt(i);
			if (Character.isUpperCase(cur)) {
				if (i != 0){
					sb.append("_");
				}					
				sb.append(cur);
			} else {
				sb.append(cur);
			}
		}
		return ("t_"+sb.toString()).toUpperCase();
	}

	/**
	 * 字符串转化为sql
	 * 
	 * @param string
	 * @return
	 */
	public static String string2SqlString(String string) {
		if (string == null || string.equals(""))
			return null;
		string = string.replace("'", "''");
		return "'" + string + "'";
	}

	public static String sql(String string) {
		return string2SqlString(string);
	}
	
	public String readSQL(String sqlFile) throws IOException{
		String file = getClass().getResource(sqlFile).getFile();
		file = file.replace("%20", " ");
		return FileUtils.readFileToString(new File(file));
	}

	public static void main(String[] args) {
		System.out.println(timestamp2SqlString(new Timestamp(System.currentTimeMillis())));
	}
}
