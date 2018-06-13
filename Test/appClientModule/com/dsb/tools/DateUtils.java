package com.dsb.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dsb.freemark.tools.UIRuleUtil;

/**
 * 日期工具类
 * 
 * @author admin
 *
 */
public class DateUtils {
	
	private static final Log log = LogFactory.getLog(DateUtils.class);

	// string to set the date time format
	public final static String FORMAT_YYYY_MM_DD___HH__MM__SS = "yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd-HH-mm-ss";
	public final static String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd-HH-mm";
	public final static String FORMAT_YYYY_MM_DD_HH__MM = "yyyy-MM-dd HH:mm";
	public final static String FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";
	public final static String FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd-HH";
	public final static String FORMAT_YYYYMMDDHH = "yyyyMMddHH";
	public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public final static String FORMAT_YYYY_DD_MM = "yyyy-dd-MM";
	public final static String FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public final static String FORMAT_YY_MM_DD = "yy-MM-dd";
	public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
	public final static String FORMAT_YYYY_MM = "yyyy-MM";
	public final static String FORMAT_YYYYMM = "yyyyMM";
	public final static String FORMAT_YYYY = "yyyy";
	public final static String FORMAT_HH__MM = "HH:mm";
	public final static String FORMAT_HH__MM__SS = "HH:mm:ss";
	
	// sql date formats
	public static final String FORMAT_YYYY_MM_DD___HH__MM__SS_SSSS = "yyyy-MM-dd HH:mm:ss.SSSS";
	public static final String FORMAT_YYYY_MM_DD_HH24MISSFF = "yyyy-MM-dd hh24:mi:ss.ff";
	public static final String FORMAT_YYYY_MM_DD_HH24MISS = "yyyy-MM-dd hh24:mi:ss";
	
	// default Date format instance
	public final static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(FORMAT_YYYY_MM_DD___HH__MM__SS);
	public final static SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
	
	// 
	private static HashMap<String, String> dateRegFormat = new HashMap<String, String>();
	
	static {
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", FORMAT_YYYY_MM_DD_HH_MM_SS);// 2014年3月12日 13时5分34秒，2014-03-12
										// 12:05:34，2014/3/12 12:5:34
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", FORMAT_YYYY_MM_DD_HH_MM);// 2014-03-12
																									// 12:05
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", FORMAT_YYYY_MM_DD_HH);// 2014-03-12
																						// 12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", FORMAT_YYYY_MM_DD);// 2014-03-12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}$", FORMAT_YYYY_MM);// 2014-03
		dateRegFormat.put("^\\d{4}$", FORMAT_YYYY);// 2014
		dateRegFormat.put("^\\d{14}$", FORMAT_YYYYMMDDHHMMSS);// 20140312120534
		dateRegFormat.put("^\\d{12}$", FORMAT_YYYYMMDDHHMM);// 201403121205
		dateRegFormat.put("^\\d{10}$", FORMAT_YYYYMMDDHH);// 2014031212
		dateRegFormat.put("^\\d{8}$", FORMAT_YYYYMMDD);// 20140312
		dateRegFormat.put("^\\d{6}$", FORMAT_YYYYMM);// 201403
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$", FORMAT_YYYY_MM_DD_HH_MM_SS);// 13:05:34
																							// 拼接当前日期
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", FORMAT_YYYY_MM_DD_HH_MM);// 13:05
																			// 拼接当前日期
		dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", FORMAT_YY_MM_DD);// 14.10.18(年.月.日)
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", FORMAT_YYYY_DD_MM);// 30.12(日.月)
																	// 拼接当前年份
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", FORMAT_DD_MM_YYYY);// 12.21.2013(日.月.年)
	}

	/**
	 * 获取当前时间
	 * 系统内部获取时间时统一使用这个方法
	 * 
	 * @return now date
	 */
	public static Date getNowDate() {
		// 
		return new Date();
	}

	public static String getNowDateString(String pattern) {
		if (StringUtils.isBlank(pattern)) {
			return DEFAULT_DATE_FORMAT.format(getNowDate());
		} else {
			return new SimpleDateFormat(pattern).format(getNowDate());
		}
	}

	/**
	 * Date转String
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2Str(Date date, String pattern) {
		if(date == null)
			return "";
		try {
			if (StringUtils.isBlank(pattern)) {
				return DEFAULT_DATE_FORMAT.format(date);
			} else {
				return new SimpleDateFormat(pattern).format(date);
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * String转Date
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String strDate, String pattern) {
		if(strDate == null)
			return null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 * @param pattern 默认 yyyy-MM-dd
	 * @return
	 */
	public static Date formatDate(Date date, String pattern) {
		if(date == null){
			return null;
		}
		
		try {
			
			String strDate;
			if (StringUtils.isBlank(pattern)) {
				strDate = DEFAULT_DATE_FORMAT.format(date);
				return str2Date(strDate, FORMAT_YYYY_MM_DD);
			} else {
				strDate = new SimpleDateFormat(pattern).format(date);
				return str2Date(strDate, pattern);
			}
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * String转Timestamp
	 * 
	 * @param string
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToTimestamp(String strDate, String format) throws ParseException {
		
		Date date;
		if (StringUtils.isBlank(format)) {
			date = DEFAULT_DATE_FORMAT.parse(strDate);
		} else {
			date = new SimpleDateFormat(format).parse(strDate);
		}
		
		if (date != null){
			return new Timestamp(date.getTime());
		} else {
			return null;
		}
	}
	
	public static Timestamp objectToTimestamp(Object object) throws ParseException{
		if(object!=null){
			if(object instanceof Timestamp){
				return (Timestamp) object;
			}else if(object instanceof Date){
				Date date = (Date) object;
				return new Timestamp(date.getTime());
			}else {
				return stringToTimestamp(object.toString(), null);
			}
		}else return null;
	}
	
	public static Date objectToDate(Object object) throws ParseException{
		if(object!=null){
			if(object instanceof Timestamp){
				return (Timestamp) object;
			}else if(object instanceof Date){
				Date date = (Date) object;
				return new Timestamp(date.getTime());
			}else {
				return stringToTimestamp(object.toString(), null);
			}
		}else return null;
	}
	
	public static String getDateForDBString(String date,String type) { 
		date = date.substring(0, 10);
		if ("start".equals(type)) {
			date = date + " 00:00:00.0";
		} else if ("end".equals(type)) {
			date = date + " 23:59:59.0";
		} 
		return date;
	}
	
	public static String getNowdataToMinite(){
		String result=getNowYear()+"";
		String month=getNowMonth()+"";
		if(month.length()<2){
			month="0"+month;
		}
		String date=getTodayDate()+"";
		if(date.length()<2){
			date="0"+date;
		}
	    String hour=getTodayHour()+"";
	    if(hour.length()<2){
	    	hour="0"+hour;
		}
		String minute=getTodayMinute()+"";
		if(minute.length()<2){
			minute="0"+minute;
		}
		result+="-";
		result+=month;
		result+="-";
		result+=date;
		result+=" "+hour;
		result+=":"+minute; 
		return result;
	}
	
	public static int getNowYear() {
		Date nowdate = getNowDate();
		int year = nowdate.getYear() + 1900;
		return year;
	}

	public static int getNowMonth() {
		Date nowdate = getNowDate();
		int month = nowdate.getMonth() + 1;
		return month;
	}

	public static int getTodayDate() {
		Date nowdate = getNowDate();
		int date = nowdate.getDate();
		return date;
	}
	
	public static int getTodayHour() {
		Date nowdate = getNowDate();
		int date = nowdate.getHours();
		return date;
	}
	
	public static int getTodayMinute() {
		Date nowdate = getNowDate();
		int date = nowdate.getMinutes();
		return date;
	}
	
	public static int getTodaySeconds() {
		Date nowdate = getNowDate();
		int date = nowdate.getSeconds();
		return date;
	}
	
	/***
	 * 获取昨天yyyy-MM-dd格式
	 * @return
	 */
	public static String getYestodayYYYMMDD(){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE,-1);
        Date d=calendar.getTime();
        SimpleDateFormat sp=new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        String date=sp.format(d);
		return date;
	}

	/**
	 * 日期加减
	 * 
	 * @param date 源日期 默认为当前系统时间
	 * @param calendarField 增加的类型Calendar类中有
	 * @param amount 数量
	 * @return
	 */
	public static Date dateAdd(Date date, int calendarField, int amount) {
		if (date == null) {
			date = getNowDate();
		} 
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static Date addMonths(Date srcDate, int amount) {
		return dateAdd(srcDate, Calendar.MONTH, amount);
	}
	
	public static Date addDays(Date srcDate, int amount) {
		return dateAdd(srcDate, Calendar.DATE, amount);
	}

	/**
	 * 获取下一天
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(Calendar.DATE,1);
		return lastDate.getTime();
	}
	
	public static String getAfterServenDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(Calendar.DATE,7);
		String str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getMonthFirstDay(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getMonthLastDay(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);
		lastDate.add(Calendar.MONTH,1);
		lastDate.add(Calendar.DATE,-1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getPreviousMonthFirst(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);
		lastDate.add(Calendar.MONTH, -1);
		// lastDate.add(Calendar.DATE,-1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getMonthEnd(Date date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(Calendar.MONTH, -1);
		lastDate.set(Calendar.DATE, 1);
		lastDate.roll(Calendar.DATE, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}
	
	public static String getMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(getNowDate());
		lastDate.set(Calendar.DATE, 1);
		lastDate.add(Calendar.MONTH,0);
		// lastDate.add(Calendar.DATE,-1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(getNowDate());
		lastDate.add(Calendar.MONTH,0);
		lastDate.set(Calendar.DATE, 1);
		lastDate.roll(Calendar.DATE, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}
	
	public static int getNumTime(Date StatTime,Date EndTime){
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(StatTime);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(EndTime);
		long num = startDate.getTimeInMillis()-endDate.getTimeInMillis();
		if(num >=0){
			return -1;
		}
		return 1;
	}

	public static int getNumTime(String StatTime,String EndTime){
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.setTime(str2Date(StatTime, FORMAT_YYYY_MM_DD));
		endDate.setTime(str2Date(EndTime, FORMAT_YYYY_MM_DD));
		long num = startDate.getTimeInMillis()-endDate.getTimeInMillis();
		if(num >=0){
			return -1;
		}
		return 1;
	}

	public static int getSkinDiffHHMM(String StatTime,String EndTime){
		if(null==StatTime||null==EndTime){
			return 0;
		}
		String []startArr =StatTime.split(":");
		int mmStart=UIRuleUtil.getIntValue(startArr[0])*60+UIRuleUtil.getIntValue(startArr[1]);
		String []endArr =EndTime.split(":");
		int mmEnd=UIRuleUtil.getIntValue(endArr[0])*60+UIRuleUtil.getIntValue(endArr[1]);
		return mmEnd-mmStart;
	}

	public static int getSkinDiffHHMMDESC(String StatTime,String EndTime){
		if(null==StatTime||null==EndTime){
			return 0;
		}
		String []startArr =StatTime.split(":");
		int mmStart=UIRuleUtil.getIntValue(startArr[0])*60+UIRuleUtil.getIntValue(startArr[1]);
		String []endArr =EndTime.split(":");
		int mmEnd=UIRuleUtil.getIntValue(endArr[0])*60+UIRuleUtil.getIntValue(endArr[1]);
		return mmStart-mmEnd;
	}

	public static String AddSkinPreTime(String time,int mmvalue){
		String []startArr =time.split(":");
		int mmStart=UIRuleUtil.getIntValue(startArr[0])*60+UIRuleUtil.getIntValue(startArr[1])+mmvalue;
		String hh=mmStart/60>=10?UIRuleUtil.getString(mmStart/60):"0"+mmStart/60;
		return hh+":"+(mmStart%60!=0?mmStart%60:"00");
	}
	
	/**
	 * 获取两个日期之间的差 时:分:秒
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String dateCompare(Date begin, Date end) {
		if (begin == null || end == null) {
			return "";
		}
        long diff = end.getTime() - begin.getTime(); // 得到的差值是微秒级别，可以忽略
//        long days = diff / (1000 * 60 * 60 * 24);
//        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
//        long seconds = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
        long hours = diff / (1000 * 60 * 60);
        long minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (diff - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000);
		return hours + ":" + minutes + ":" + seconds;
	}
	
	/**
	 * 获取两个日期之间的差 X日X时X分
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String dateCompareDHM(Date begin, Date end) {
		if (begin == null || end == null) {
			return "";
		}
        long diff = end.getTime() - begin.getTime(); // 得到的差值是微秒级别，可以忽略
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
		return days + "天" + hours + "时" + minutes + "秒";
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		   java.util.Date now = df.parse("2004-03-27 02:30:24");   
		   java.util.Date date=df.parse("2004-03-27 11:30:24");   
		   
		   System.out.println(dateCompare(now, date));
	}

	public static Date getNextMonth(Date date, int i) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(Calendar.MONTH,i);
		return lastDate.getTime();
	}

	public static Date getNextMonth(String sDate, int i) {
		Date date = str2Date(sDate, FORMAT_YYYY_MM_DD___HH__MM__SS);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.add(Calendar.MONTH,i);
		return lastDate.getTime();
	}
	public final static String timestamp2String(Timestamp timestamp) {	   
		SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
	    return df.format(timestamp);
	}
	
	public static List<String> getBetweenTimes(String beginTime,String endTime) {
		Date beginDate = str2Date(beginTime, FORMAT_YYYY_MM_DD___HH__MM__SS);
		Date endDate = str2Date(endTime, FORMAT_YYYY_MM_DD___HH__MM__SS);
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(beginDate);
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DATE));
		max.setTime(endDate);
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DATE));
		Calendar curr = min;
		while (curr.before(max)) {
			result.add(sdf.format(curr.getTime()));
			curr.add(Calendar.DATE, 1);
//			logger.info(sdf.format(curr.getTime()));
		}
		result.add(endTime);
		return result;

	}
	
	private static int getMondayPlus() {  
	    Calendar cd = Calendar.getInstance();  
	    int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);  
	    if (dayOfWeek == 1) {  
	        return -6;  
	    } else {  
	        return 2 - dayOfWeek;  
	    }  
	}
	
	public static String getCurrentMonday() {  
	    int mondayPlus = getMondayPlus();  
	    GregorianCalendar currentDate = new GregorianCalendar();  
	    currentDate.add(GregorianCalendar.DATE, mondayPlus);  
	    Date monday = currentDate.getTime();  
	    DateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
	    String preMonday = df.format(monday);  
	    return preMonday;  
	}
	  
	public static String getSunday() {  
		int weeks = 0;  
	    int mondayPlus = getMondayPlus();  
	    GregorianCalendar currentDate = new GregorianCalendar();  
	    currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);  
	    Date monday = currentDate.getTime();  
	    DateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
	    String preMonday = df.format(monday);  
	    return preMonday;  
	}
	
	 /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w == 0){
        	w += 7;
        }
        return String.valueOf(w);
    }

	/**
	 * 两个日期之间的间隔天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static Long getTwoDateDistance(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			return null;
		} else {
			long distance = Math.abs((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
			return distance;
		}
	}
	
	/**
	 * 比较两个日期是否相等
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			return date2Str(date1, null).equals(date2Str(date2, null));
		} else if (date1 == null && date2 == null) {
			return true;
		}
		return false;
	}

	public static String getDateString() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String ss = calendar.get(Calendar.YEAR) + (month > 9 ? "" + month : "0" + month)
				+ (day > 9 ? "" + day : "0" + day) + "-" + (hour > 9 ? "" + hour : "0" + hour)
				+ (minute > 9 ? "" + minute : "0" + minute) + (second > 9 ? "" + second : "0" + second);
		return ss;
	}
	
	@SuppressWarnings("deprecation")
	public static Date maxDate(Date time) {
		if(0==time.getHours()){
			time.setHours(23);
		}
		if(0==time.getMinutes()){
			time.setMinutes(59);
		}
		if(0==time.getSeconds()){
			time.setSeconds(59);
		}
		return time;
	}

	public static Date[] getDateRange(String text) {
		Date[] dates = new Date[2];
		try {
			Date today = getNowDate();
			Calendar c = Calendar.getInstance();
			Date start = null;
			if (text.equals("今天")) {
				dates[0] = formatDate(today, null);
				dates[1] = maxDate(today);
			} else if (text.equals("昨天")) {
				start = dateAdd(today, Calendar.DATE, -1);
				dates[0] = formatDate(today, null);
				dates[1] = maxDate(start);
			} else if (text.equals("过期")) {
				start = dateAdd(today, Calendar.DATE, -1);
				dates[0] = str2Date("1900-01-01", FORMAT_YYYY_MM_DD);
				dates[1] = maxDate(start);
			} else if (text.equals("明天")) {
				start = dateAdd(today, Calendar.DATE, 1);
				dates[0] = formatDate(today, null);
				dates[1] = maxDate(start);
			} else if (text.equals("本周")) {
				int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
				start = dateAdd(today, Calendar.DATE, -dayOfWeek);
				dates[0] = formatDate(today, null);
				dates[1] = maxDate(dateAdd(today, Calendar.DATE, 6));
			} else if (text.equals("本月")) {
				start = dateAdd(today, Calendar.DATE, c.get(Calendar.DAY_OF_MONTH) * -1 + 1);
				dates[0] = formatDate(today, null);
				dates[1] = maxDate(dateAdd(today, Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH) - 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(dateToString(dates[0]));
		// System.out.println(dateToString(dates[1]));
		return dates;
	}

	/**
	 * 获得指定日期格式的日期及对应的星期
	 * 
	 * @param days
	 *            天数
	 * @param distance
	 *            从今天起多少天（包括今天）
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static List<Map<String, Object>> getDatesAndweek(int days, int distance, String format) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < days; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			Date dateI = dateAdd(date, Calendar.DATE, i + distance);
			calendar.setTime(dateI);
			String dateIString = date2Str(dateI, format);
			int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			map.put("week", week);
			map.put("date", dateIString);
			dateList.add(map);
		}
		return dateList;
	}
	
	@SuppressWarnings("finally")
	public static Date getDate(String dateStr) {

		Date time = null;

		String curDate = new SimpleDateFormat(FORMAT_YYYY_MM_DD).format(getNowDate());
		DateFormat formatter1 = new SimpleDateFormat(FORMAT_YYYY_MM_DD___HH__MM__SS);
		DateFormat formatter2;
		String dateReplace;
		String strSuccess = "";
		try {
			for (String key : dateRegFormat.keySet()) {
				if (Pattern.compile(key).matcher(dateStr).matches()) {
					formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
					if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$") || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {// 13:05:34
																														// 或
																														// 13:05
																														// 拼接当前日期
						dateStr = curDate + "-" + dateStr;
					} else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {// 21.1
																		// (日.月)
																		// 拼接当前年份
						dateStr = curDate.substring(0, 4) + "-" + dateStr;
					}
					dateReplace = dateStr.replaceAll("\\D+", "-");
					// System.out.println(dateRegExpArr[i]);
					strSuccess = formatter1.format(formatter2.parse(dateReplace));
					time = formatter2.parse(dateReplace);
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("-----------------日期格式无效:" + dateStr);
			throw new Exception("日期格式无效");
		} finally {
			return time;
		}
	}
	
}

