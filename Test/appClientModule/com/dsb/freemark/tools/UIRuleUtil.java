package com.dsb.freemark.tools;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class UIRuleUtil {

	public UIRuleUtil() {
	}

	public static void throwException(String message) throws Exception {
		throw new Exception(message);
	}

	public static Object getObject(int value) {
		return getInt(value);
	}

	public static Object getObject(double value) {
		return getDouble(value);
	}

	public static Object getObject(boolean value) {
		return getBoolean(value);
	}

	public static Object getObject(Object value) {
		return value;
	}

	public static int getIntValue(Object obj) {
		if (obj instanceof String)
			return getIntValue((String) obj);
		if (obj instanceof Integer)
			return getIntValue((Integer) obj);
		if (obj instanceof BigDecimal)
			return getIntValue((BigDecimal) obj);
		if (obj instanceof Double)
			return getIntValue((Double) obj);
		if (obj instanceof Long)
			return getIntValue((Long) obj);
		else
			return 0;
	}

	public static int getIntValue(String obj) {
		return getInt(obj).intValue();
	}

	public static int getIntValue(Integer obj) {
		return obj.intValue();
	}

	public static int getIntValue(BigDecimal obj) {
		return obj.intValue();
	}

	public static int getIntValue(Double obj) {
		return obj.intValue();
	}

	public static int getIntValue(Long obj) {
		return obj.intValue();
	}

	public static int getIntValue(int value) {
		return value;
	}

	public static int getIntValue(double value) {
		return (int) value;
	}

	public static boolean getBooleanValue(Object obj) {
		if (obj instanceof Boolean)
			return getBooleanValue((Boolean) obj);
		else
			return false;
	}

	public static boolean getBooleanValue(Boolean obj) {
		return obj.booleanValue();
	}

	public static Integer getInt(Object obj) {
		if (obj == null)
			return new Integer(0);
		if (obj instanceof String)
			return getInt((String) obj);
		if (obj instanceof Integer)
			return (Integer) obj;
		if (obj instanceof BigDecimal)
			return getInt((BigDecimal) obj);
		if (obj instanceof Double)
			return getInt((Double) obj);
		if (obj instanceof Long)
			return getInt((Long) obj);
		else
			return new Integer(0);
	}

	public static Integer getInt(String value) {
		if (value == null)
			return new Integer(0);
		else
			return Integer.valueOf(value);
	}

	public static Integer getInt(Long value) {
		if (value == null)
			return new Integer(0);
		else
			return new Integer(value.intValue());
	}

	public static Integer getInt(double value) {
		return new Integer((new Double(value)).intValue());
	}

	public static Integer getInt(int value) {
		return new Integer(value);
	}

	public static Integer getInt(BigDecimal value) {
		if (value == null)
			return new Integer(0);
		else
			return new Integer(value.intValue());
	}

	public static Integer getInt(Double value) {
		if (value == null)
			return new Integer(0);
		else
			return new Integer(value.intValue());
	}

	public static Boolean getBoolean(Object obj) {
		if (obj instanceof Boolean)
			return (Boolean) obj;
		else
			return null;
	}

	public static Boolean getBoolean(boolean obj) {
		return new Boolean(obj);
	}

	public static int abs(int value) {
		if (value >= 0)
			return value;
		else
			return -1 * value;
	}

	public static double abs(double value) {
		if (value >= 0.0D)
			return value;
		else
			return -1D * value;
	}

	public static int abs(Integer value) {
		if (value == null)
			return 0;
		if (value.intValue() >= 0)
			return value.intValue();
		else
			return -1 * value.intValue();
	}

	public static double abs(Double value) {
		if (value == null)
			return 0.0D;
		if (value.doubleValue() >= 0.0D)
			return value.doubleValue();
		else
			return -1D * value.doubleValue();
	}

	public static double abs(BigDecimal value) {
		if (value == null)
			return 0.0D;
		if (value.doubleValue() >= 0.0D)
			return value.doubleValue();
		else
			return -1D * value.doubleValue();
	}

	public static long abs(Long value) {
		if (value == null)
			return 0L;
		if (value.doubleValue() >= 0.0D)
			return value.longValue();
		else
			return -1L * value.longValue();
	}

	public static double abs(Object value) {
		if (value == null)
			return 0.0D;
		if (value instanceof Integer)
			return (new Double(abs((Integer) value))).doubleValue();
		if (value instanceof Double)
			return abs((Double) value);
		if (value instanceof BigDecimal)
			return abs((BigDecimal) value);
		if (value instanceof Long)
			return (new Double(abs((Long) value))).doubleValue();
		else
			return 0.0D;
	}

	public static int len(Object value) {
		return len((String) value);
	}

	public static int len(String value) {
		return value.length();
	}

	public static String left(Object value, int length) {
		return left((String) value, length);
	}

	public static String left(String value, int length) {
		if (length > 0 && length <= value.length())
			return value.substring(0, length);
		if (length > 0)
			return value;
		else
			return "";
	}

	public static String right(Object value, int length) {
		return right((String) value, length);
	}

	public static String right(String value, int length) {
		if (length > 0 && length <= value.length())
			return value.substring(value.length() - length);
		if (length > 0)
			return value;
		else
			return "";
	}

	public static String upper(Object value) {
		return upper((String) value);
	}

	public static String upper(String value) {
		return value.toUpperCase();
	}

	public static String lower(Object value) {
		return lower((String) value);
	}

	public static String lower(String value) {
		return value.toLowerCase();
	}

	public static String trim(Object value) {
		return trim((String) value);
	}

	public static String trim(String value) {
		return value.trim();
	}

	public static double getBigDecimalValue(Object obj) {
		if (obj instanceof String)
			return getBigDecimalValue((String) obj);
		if (obj instanceof BigDecimal)
			return getBigDecimalValue((BigDecimal) obj);
		if (obj instanceof Double)
			return getBigDecimalValue((Double) obj);
		if (obj instanceof Integer)
			return getBigDecimalValue((Integer) obj);
		else
			return 0.0D;
	}

	public static double getBigDecimalValue(String obj) {
		if (obj != null)
			obj = obj.trim();
		return getBigDecimal(obj).doubleValue();
	}

	public static double getBigDecimalValue(Double obj) {
		return obj.doubleValue();
	}

	public static double getBigDecimalValue(BigDecimal obj) {
		return obj.doubleValue();
	}

	public static double getBigDecimalValue(Integer obj) {
		return getBigDecimal(obj).doubleValue();
	}

	public static double getBigDecimalValue(int value) {
		return (double) value;
	}

	public static double getBigDecimalValue(double value) {
		return value;
	}

	public static BigDecimal getBigDecimal(Object obj) {
		if (obj == null)
			return new BigDecimal(0.0D);
		if (obj instanceof String)
			return getBigDecimal((String) obj);
		if (obj instanceof BigDecimal)
			return (BigDecimal) obj;
		if (obj instanceof Double)
			return getBigDecimal((Double) obj);
		if (obj instanceof Integer)
			return getBigDecimal((Integer) obj);
		else
			return new BigDecimal(0.0D);
	}

	public static BigDecimal getBigDecimal(Double value) {
		if (value == null)
			return new BigDecimal(0.0D);
		else
			return new BigDecimal(value.doubleValue());
	}

	public static BigDecimal getBigDecimal(Integer value) {
		if (value == null)
			return new BigDecimal(0.0D);
		else
			return new BigDecimal(value.doubleValue());
	}

	public static BigDecimal getBigDecimal(String value) {
		if (value == null)
			return new BigDecimal(0.0D);
		else
			return new BigDecimal(value);
	}

	public static BigDecimal getBigDecimal(double value) {
		return new BigDecimal(value);
	}

	public static BigDecimal getBigDecimal(int value) {
		return BigDecimal.valueOf(value);
	}

	public static double getDouble(Object obj) {
		return 0.0D;
	}

	public static double getDouble(String text) {
		if (StringUtils.isEmpty(text))
			return 0.0D;
		try {
			double value = Double.parseDouble(text);
			return value;
		} catch (Exception e) {
			return 0.0D;
		}
	}

	public static double getDoubleValue(Double obj) {
		return obj.doubleValue();
	}

	public static Double getDouble(double value) {
		return new Double(value);
	}

	public static String getString(int value) {
		return String.valueOf(value);
	}

	public static String getString(double value) {
		return String.valueOf(value);
	}

	public static String getString(Object value) {
		if (value == null)
			return "";
		else
			return String.valueOf(value);
	}

	public static Date now() {
		return new Date();
	}

	public static int year(Object date) {
		if (date == null)
			return 0;
		if (date instanceof String)
			return year((String) date);
		if (date instanceof Date)
			return year((Date) date);
		else
			return 0;
	}

	public static int year(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(1);
	}

	public static int year(String date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(date));
		return calendar.get(1);
	}

	public static int month(Object date) {
		if (date == null)
			return 0;
		if (date instanceof String)
			return month((String) date);
		if (date instanceof Date)
			return month((Date) date);
		else
			return 0;
	}

	public static int month(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(2) + 1;
	}

	public static int month(String date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(date));
		return calendar.get(2) + 1;
	}

	public static int day(Object date) {
		if (date == null)
			return 0;
		if (date instanceof String)
			return day((String) date);
		if (date instanceof Date)
			return day((Date) date);
		else
			return 0;
	}

	public static int day(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(5);
	}

	public static int day(String date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(date));
		return calendar.get(5);
	}

	public static int hour(Object date) {
		if (date == null)
			return 0;
		if (date instanceof String)
			return hour((String) date);
		if (date instanceof Date)
			return hour((Date) date);
		else
			return 0;
	}

	public static int hour(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(10);
	}

	public static int hour(String date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(date));
		return calendar.get(10);
	}

	public static int minute(Object date) {
		if (date == null)
			return 0;
		if (date instanceof String)
			return minute((String) date);
		if (date instanceof Date)
			return minute((Date) date);
		else
			return 0;
	}

	public static int minute(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(12);
	}

	public static int minute(String date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(date));
		return calendar.get(12);
	}

	public static int second(Object date) {
		if (date == null)
			return 0;
		if (date instanceof String)
			return second((String) date);
		if (date instanceof Date)
			return second((Date) date);
		else
			return 0;
	}

	public static int second(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(13);
	}

	public static int second(String date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date(date));
		return calendar.get(13);
	}

	public static Date dateAdd(Object date, Object offSet) {
		if (date == null)
			return null;
		if (offSet == null) {
			if (date instanceof String)
				return new Date((String) date);
			if (date instanceof Date)
				return (Date) date;
			else
				return null;
		}
		if (date instanceof String) {
			if (offSet instanceof String)
				return dateAdd(new Date((String) date), new Date((String) offSet));
			if (offSet instanceof Date)
				return dateAdd(new Date((String) date), (Date) offSet);
			if (offSet instanceof Integer)
				return dateAdd(new Date((String) date), ((Integer) offSet).intValue());
			if (offSet instanceof Double)
				return dateAdd(new Date((String) date), ((Double) offSet).intValue());
			if (offSet instanceof Long)
				return dateAdd(new Date((String) date), ((Long) offSet).intValue());
			if (offSet instanceof BigDecimal)
				return dateAdd(new Date((String) date), ((BigDecimal) offSet).intValue());
			else
				return new Date((String) date);
		}
		if (date instanceof Date) {
			if (offSet instanceof String)
				return dateAdd((Date) date, new Date((String) offSet));
			if (offSet instanceof Date)
				return dateAdd((Date) date, (Date) offSet);
			if (offSet instanceof Integer)
				return dateAdd((Date) date, ((Integer) offSet).intValue());
			if (offSet instanceof Double)
				return dateAdd((Date) date, ((Double) offSet).intValue());
			if (offSet instanceof Long)
				return dateAdd((Date) date, ((Long) offSet).intValue());
			if (offSet instanceof BigDecimal)
				return dateAdd((Date) date, ((BigDecimal) offSet).intValue());
			else
				return (Date) date;
		} else {
			return null;
		}
	}

	public static Date dateAdd(Date date, Date offSet) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(1, year(offSet));
		calendar.add(2, month(offSet));
		calendar.add(5, day(offSet));
		calendar.add(10, hour(offSet));
		calendar.add(12, minute(offSet));
		calendar.add(13, second(offSet));
		return calendar.getTime();
	}

	public static Date dateAdd(Object date, int offSet) {
		if (date == null)
			return null;
		if (date instanceof String)
			return dateAdd(new Date((String) date), offSet);
		if (date instanceof Date)
			return dateAdd((Date) date, offSet);
		else
			return null;
	}

	public static Date dateDiff(Object date, int offSet) {
		if (date == null)
			return null;
		if (date instanceof String)
			return dateDiff(new Date((String) date), offSet);
		if (date instanceof Date)
			return dateDiff((Date) date, offSet);
		else
			return null;
	}

	public static Date dateAdd(Date date, int offSet) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(5, offSet);
		return calendar.getTime();
	}

	public static Date dateDiff(Date date, int offSet) {
		return dateAdd(date, -1 * offSet);
	}

	public static String getNumberFormat(Object precision) {
		return getNumberFormat(getIntValue(precision));
	}

	public static String getNumberFormat(int precision) {
		String format = "%r{0.";
		for (int i = 0; i < precision; i++)
			format = format + "#";

		format = format + "}f";
		return format;
	}

	public static int getLength(String text) {
		if (StringUtils.isEmpty(text))
			return 0;
		else
			return text.length();
	}

	public static boolean isNull(Object value) {
		if (value instanceof String)
			return isNull((String) value);
		if (value instanceof Integer)
			return isNull((Integer) value);
		if (value instanceof Float)
			return isNull((Float) value);
		if (value instanceof Double)
			return isNull((Double) value);
		if (value instanceof BigDecimal)
			return isNull((BigDecimal) value);
		if (value instanceof Date)
			return isNull((Date) value);
		else
			return value == null;
	}

	public static boolean isNull(String value) {
		return StringUtils.isEmpty(value);
	}

	public static boolean isNull(int value) {
		return value == 0 || value == -1;
	}

	public static boolean isNull(float value) {
		return value == 0.0F;
	}

	public static boolean isNull(double value) {
		return value == 0.0D;
	}

	public static boolean isNull(Integer value) {
		return value == null || value.intValue() == 0;
	}

	public static boolean isNull(Float value) {
		return value == null || isNull(value.floatValue());
	}

	public static boolean isNull(Double value) {
		return value == null || isNull(value.doubleValue());
	}

	public static boolean isNull(BigDecimal value) {
		return value == null || isNull(value.doubleValue());
	}

	public static boolean isNull(Date value) {
		return value == null;
	}

	public static boolean isNotNull(Object value) {
		if (value instanceof Integer)
			return isNotNull((Integer) value);
		if (value instanceof Float)
			return isNotNull((Float) value);
		if (value instanceof Double)
			return isNotNull((Double) value);
		if (value instanceof BigDecimal)
			return isNotNull((BigDecimal) value);
		if (value instanceof Date)
			return isNotNull((Date) value);
		else
			return value != null;
	}

	public static boolean isNotNull(String value) {
		return !isNull(value);
	}

	public static boolean isNotNull(int value) {
		return !isNull(value);
	}

	public static boolean isNotNull(float value) {
		return !isNull(value);
	}

	public static boolean isNotNull(double value) {
		return !isNull(value);
	}

	public static boolean isNotNull(Integer value) {
		return !isNull(value);
	}

	public static boolean isNotNull(Float value) {
		return !isNull(value);
	}

	public static boolean isNotNull(Double value) {
		return !isNull(value);
	}

	public static boolean isNotNull(BigDecimal value) {
		return !isNull(value);
	}

	public static boolean isNotNull(Date value) {
		return !isNull(value);
	}

	public static String format(Object value, String format) {
		if (value == null)
			return "";
		if (value instanceof Date)
			return format((Date) value, format);
		else
			return value.toString();
	}

	public static String format(Date value, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(value);
	}

}
