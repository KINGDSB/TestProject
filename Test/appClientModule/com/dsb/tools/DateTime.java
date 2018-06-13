package com.dsb.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class DateTime {
	
	/**
	 * 字符串转日期
	 * 
	 * @param strDate
	 * @param strFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String strDate, String strFormat) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat(strFormat);
		return format.parse(strDate);
	}
	
	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param strFormat
	 * @return
	 * @throws ParseException
	 */
	public static String getStringDate(Date date, String strFormat) throws ParseException {
		if(date==null){
			return "";
		}
		SimpleDateFormat format=new SimpleDateFormat(strFormat);
		return format.format(date);
	}
	
	public static Timestamp getTimestamp(String strDate, String strFormat) throws ParseException {
		Date date=getDate( strDate, strFormat );
		Timestamp timestamp=new Timestamp(date.getTime());
		return timestamp;
	}
	
	public static Timestamp get24Timestamp(String strDate) throws ParseException {
		Date date=getDate( strDate, "yyyy-MM-dd HH:mm:ss" );
		Timestamp timestamp=new Timestamp(date.getTime());
		return timestamp;
	}
	
	public static Timestamp get12Timestamp(String strDate) throws ParseException {
		Date date=getDate( strDate, "yyyy-MM-dd hh:mm:ss" );
		Timestamp timestamp=new Timestamp(date.getTime());
		return timestamp;
	}
	
	public static String getStringTimestamp(Timestamp timestamp, String strFormat) throws ParseException {
		if(timestamp==null){
			return "";
		}
		return getStringDate((Date)timestamp, strFormat);
	}

	
	public static Timestamp addMonth(Timestamp timestamp, int months) throws ParseException {
		GregorianCalendar grc=new GregorianCalendar();
		grc.setTime((Date)timestamp);
		
		grc.add(GregorianCalendar.MONTH, months);
		
		return new Timestamp(grc.getTime().getTime());
	}
	
	public static Timestamp addYear(Timestamp timestamp, int years) throws ParseException {
		GregorianCalendar grc=new GregorianCalendar();
		grc.setTime((Date)timestamp);
		
		grc.add(GregorianCalendar.YEAR, years);
		
		return new Timestamp(grc.getTime().getTime());
	}	
	
	public static Timestamp addDay(Timestamp timestamp, int days) throws ParseException {
		GregorianCalendar grc=new GregorianCalendar();
		grc.setTime((Date)timestamp);
		
		grc.add(GregorianCalendar.DAY_OF_MONTH, days);
		
		return new Timestamp(grc.getTime().getTime());
	}		
		
	public static Timestamp addHour(Timestamp timestamp, int hours) throws ParseException {
		GregorianCalendar grc=new GregorianCalendar();
		grc.setTime((Date)timestamp);
		
		grc.add(GregorianCalendar.HOUR_OF_DAY, hours);
		
		return new Timestamp(grc.getTime().getTime());
	}	
	
	public static Timestamp addMinute(Timestamp timestamp, int minutes) throws ParseException {
		GregorianCalendar grc=new GregorianCalendar();
		grc.setTime((Date)timestamp);
		
		grc.add(GregorianCalendar.MINUTE, minutes);
		
		return new Timestamp(grc.getTime().getTime());
	}
	
	public static Timestamp addSecond(Timestamp timestamp, int seconds) throws ParseException {
		GregorianCalendar grc=new GregorianCalendar();
		grc.setTime((Date)timestamp);
		
		grc.add(GregorianCalendar.SECOND, seconds);
		
		return new Timestamp(grc.getTime().getTime());
	}
	
	/**
	 * 将参数date增加times的年、月、日、小时、分、秒、毫秒。type为年、月、日、小时、分、秒、毫秒，并对应1,2,3,4,5,6,7。
	 * 
	 * @param date
	 * @param times
	 * @param type
	 * @return
	 */
	public static Date addTime(Date date, int times, int type) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (type == 1) {
				cal.add(Calendar.YEAR, times);
			}
			if (type == 2) {
				cal.add(Calendar.MONTH, times);
			}
			if (type == 3) {
				cal.add(Calendar.DAY_OF_MONTH, times);
			}
			if (type == 4) {
				cal.add(Calendar.HOUR_OF_DAY, times);
			}
			if (type == 5) {
				cal.add(Calendar.MINUTE, times);
			}
			if (type == 6) {
				cal.add(Calendar.SECOND, times);
			}
			if (type == 7) {
				cal.add(Calendar.MILLISECOND, times);
			}
			return cal.getTime();
		}
		return null;
	}
	
	public static String getTime(String time,String strFormat)throws ParseException {
		 Timestamp endLogDateFormated=DateTime.getTimestamp(time, strFormat);
		 String sTime = DateTime.getStringTimestamp(endLogDateFormated, "yyyyMMdd");
		 return sTime;
	}

	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}
	
	/**
	 * 获取上个月最后一天
	 * @return
	 * @throws Exception 
	 */
	public static Timestamp getBeforeLastMonthdate(Timestamp currentTime) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		if (null != currentTime) {
			calendar.setTimeInMillis(currentTime.getTime());
		}
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String time = sf.format(calendar.getTime())+" 23:59:59";
		return getTimestamp(time, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 获取上一天
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	
//    /**
//     * 计算工作日
//     * 具体节日包含哪些,可以在HolidayMap中修改
//     * @param src 日期(源)
//     * @param adddays 要加的天数
//     * @exception throws [违例类型] [违例说明]
//     * @version  [s001, 2010-11-19]
//     * @author  shenjunjie
//     */
//    public static Calendar addDateByWorkDay(Calendar src,int adddays)
//    {
////        Calendar result = null;
//        holidayFlag = false;
//        for (int i = 0; i < adddays; i++)
//        {
//            //把源日期加一天
//            src.add(Calendar.DAY_OF_MONTH, 1);
//            holidayFlag =checkHoliday(src);
//            if(holidayFlag)
//            {
//               i--;
//            }
//            System.out.println(src.getTime());
//        }
//        System.out.println("Final Result:"+src.getTime());
//        return src;
//    }
// 
//    /**
//     * 节假日
//     */
//    private static Set<String> HOLIDAY_SET = new HashSet<String>(){
//    	
//    	
//    };
//    
//    /**
//     * 判断日期(yyyy-MM-dd)是否是节假日
//     */
//    public static boolean checkHoliday(Date date){
//    	
//    	Calendar calendar = Calendar.getInstance();
//    	calendar.setTime(date);
//    	
//        boolean result = false;
//        //先检查是否是周六周日(有些国家是周五周六)
//        try {
//			if (HOLIDAY_SET.contains(getStringDate(date, "yyyy-MM-dd")) ||
//				calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
//				calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
//				){
//			    return true;
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        return result;
//    }
// 
//    /**
//     * 初始化节日List,如果需要加入新的节日,请在这里添加
//     * 加的时候请尽量使用Calendar自带的常量而不是魔鬼数字
//     * 注:年份可以随便写,因为比的时候只比月份和天
//     * @version  [s001, 2010-11-19]
//     * @author  shenjunjie
//     */
//    private static void initHOLIDAY_LIST()
//    {
//        HOLIDAY_LIST = new ArrayList();
// 
//        //五一劳动节
//        Calendar may1 = Calendar.getInstance();
//        may1.set(Calendar.MONTH,Calendar.MAY);
//        may1.set(Calendar.DAY_OF_MONTH,1);
//        HOLIDAY_LIST.add(may1);
// 
//        Calendar may2 = Calendar.getInstance();
//        may2.set(Calendar.MONTH,Calendar.MAY);
//        may2.set(Calendar.DAY_OF_MONTH,2);
//        HOLIDAY_LIST.add(may2);
// 
//        Calendar may3 = Calendar.getInstance();
//        may3.set(Calendar.MONTH,Calendar.MAY);
//        may3.set(Calendar.DAY_OF_MONTH,3);
//        HOLIDAY_LIST.add(may3);
// 
//        Calendar h3 = Calendar.getInstance();
//        h3.set(2000, 1, 1);
//        HOLIDAY_LIST.add(h3);
// 
//        Calendar h4 = Calendar.getInstance();
//        h4.set(2000, 12, 25);
//        HOLIDAY_LIST.add(h4);
// 
//        //中国母亲节：五月的第二个星期日
//        Calendar may5 = Calendar.getInstance();
//        //设置月份为5月
//        may5.set(Calendar.MONTH,Calendar.MAY);
//        //设置星期:第2个星期
//        may5.set(Calendar.DAY_OF_WEEK_IN_MONTH,2);
//        //星期日
//        may5.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
////        System.out.println(may5.getTime());
// 
//        HOLIDAY_LIST.add(may5);
//    }
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(c.getTime());
		tmp.add(Calendar.DAY_OF_YEAR, 10);
		System.out.println(tmp.getTime());
		System.out.println(c.getTime());
	}
	
}

