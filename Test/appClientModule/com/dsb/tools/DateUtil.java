package com.dsb.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

    private static HashMap<String, String> dateRegFormat2 = new HashMap<String, String>();


    static {
        dateRegFormat2.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$", "yyyy-MM-dd-HH-mm-ss");// 2014年3月12日 13时5分34秒，2014-03-12
        // 12:05:34，2014/3/12 12:5:34
        dateRegFormat2.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH-mm");// 2014-03-12
        // 12:05
        dateRegFormat2.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH");// 2014-03-12
        // 12
        dateRegFormat2.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");// 2014-03-12
        dateRegFormat2.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");// 2014-03
        dateRegFormat2.put("^\\d{4}$", "yyyy-MM");// 2014
        dateRegFormat2.put("^\\d{14}$", "yyyyMMddHHmmss");// 20140312120534
        dateRegFormat2.put("^\\d{12}$", "yyyyMMddHHmm");// 201403121205
        dateRegFormat2.put("^\\d{10}$", "yyyyMMddHH");// 2014031212
        dateRegFormat2.put("^\\d{8}$", "yyyyMMdd");// 20140312
        dateRegFormat2.put("^\\d{6}$", "yyyyMM");// 201403
        dateRegFormat2.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$", "\"yyyy-MM-dd-HH-mm-ss\"");// 13:05:34
        // 拼接当前日期
        dateRegFormat2.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");// 13:05
        // 拼接当前日期
        dateRegFormat2.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");// 14.10.18(年.月.日)
        dateRegFormat2.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");// 30.12(日.月)
        // 拼接当前年份
        dateRegFormat2.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");// 12.21.2013(日.月.年)
    }

	private static HashMap<String, String> dateRegFormat = new HashMap<String, String>();

	private static final ThreadLocal<DateFormat> datetimeFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddHHmmss");
		}
	};
	private static final ThreadLocal<DateFormat> datetimeFormatByyMdhs = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMddhhss");
		}
	};

	private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
	};

	private static final ThreadLocal<DateFormat> dateFormatYM = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMM");
		}
	};

    private static final ThreadLocal<DateFormat> dateFormatY_M = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM");
        }
    };

	private static final ThreadLocal<DateFormat> dateFormatYMd = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private static final ThreadLocal<DateFormat> dateFormatMd = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("MM-dd");
		}
	};

    private static final ThreadLocal<DateFormat> timeFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };

    private static final ThreadLocal<DateFormat> timeFormatHm = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm");
        }
    };

	private static final ThreadLocal<DateFormat> displayFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
	};

	private static final ThreadLocal<DateFormat> dateTimeDispalyFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private static final ThreadLocal<DateFormat> dateDisplayFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private static final DateFormat DATE_FORMAT_TIME2 = new SimpleDateFormat("yyyy-MM-dd");

	private static final DateFormat DATE_FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date parseDateYMD(String dateString) {
		try {
			return dateDisplayFormat.get().parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date formatStringDateTime(String datetimeString) {
		try {
			return dateTimeDispalyFormat.get().parse(datetimeString);
		} catch (ParseException e) {
			throw new RuntimeException();
		}
	}

	public static String formatDateTimeDispaly(Date date) {
		return dateTimeDispalyFormat.get().format(date);
	}

	public static String formatDisplay(Date date) {
		return displayFormat.get().format(date);
	}

	public static String formatDatetime(Date date) {
		return datetimeFormat.get().format(date);
	}
	public static String formatDateByyMdhs(Date date) {
		return datetimeFormatByyMdhs.get().format(date);
	}

	public static Date parseDatetime(String dateString) {
		try {
			return datetimeFormat.get().parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date parseDisplayDatetime(String dateString) {
		try {
			return displayFormat.get().parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static String formatDate(Date date) {
		return dateFormat.get().format(date);
	}

	public static String formatDateYMd(Date date) {
		return dateFormatYMd.get().format(date);
	}

	public static String formatDateMd(Date date) {
		return dateFormatMd.get().format(date);
	}

	public static Date stringToDate(String str) {
		if(StringUtils.isBlank(str)){
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date stringToDateyMdHms(String str) {
		if(StringUtils.isBlank(str)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	public static String formatTime(Date date) {
        return timeFormat.get().format(date);
    }

    public static String formatTimeHm(Date date) {
        return timeFormatHm.get().format(date);
    }

	public static Date parseDate(String dateString) {
		try {
			return dateFormat.get().parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date getSysMaxDatetime() {
		return parseDatetime("21990101000000");
	}

	public static final int SECOND = 0;
	public static final int MINUTES = 1;
	public static final int HOUR = 2;
	public static final int DAY = 3;

	public static long diffDate(Date date1, Date date2, int field) {
		long diff = date1.getTime() - date2.getTime();
		switch (field) {
			case SECOND:
				return diff / 1000;
			case MINUTES:
				return diff / (1000 * 60);
			case HOUR:
				return diff / (1000 * 60 * 60);
			case DAY:
				return diff / (1000 * 60 * 60 * 24);
			default:
				return diff;
		}
	}

	/**
	 * 获取本月最后一天
	 * @param date  本月第一天
	 * @return String 本月最后一天
	 */
	@SuppressWarnings("static-access")
	public static String lastDayOrMonth(String date){
		String substring = "";
		if(date.length() == 7){
			substring = date;
		}else if(date.length() == 10){
			substring = date.substring(0, date.length()-3);
		}else{
			return null;
		}

		Calendar calendar=new GregorianCalendar();
		Date resDate = parseDateYMD(substring+"-01");
		calendar.setTime(resDate);
		calendar.roll(Calendar.DATE, -1);
		String format = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		format = format + " 23:59:59";
		return format;
	}

	/**
	 * 获得某天最大时间 xxxx-xx-xx 23:59:59
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		if(null == date){
			date = new Date();
		}
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(date);
		calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
		calendarEnd.set(Calendar.MINUTE, 59);
		calendarEnd.set(Calendar.SECOND, 59);

		//防止mysql自动加一秒,毫秒设为0
		calendarEnd.set(Calendar.MILLISECOND, 0);
		return calendarEnd.getTime();
	}

	/**
	 * 获得某天最小时间 xxxx-xx-xx 00:00:00
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
		if(null == date){
			date = new Date();
		}
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 指定月天数
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int formatDateHour(Date date) {
		try {
			String dateString = DATE_FORMAT_TIME.format(date);
			return Integer.valueOf(dateString.substring(11, 13));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


    /**
     * 参数为null 则返回当前时间
     * @return 格式 xxxx年xx月xx日
     * @param parDate 参数格式为 xxxx-xx-xx
     */
    public static String getNowDate(String parDate){
        String date = "";
        if(StringUtils.isNotBlank(parDate)){
            date = formatDateYMd(parseDateYMD(parDate));
        }else{
            date = formatDateYMd(new Date());
        }
        return new StringBuffer(date.substring(0,4)).append("年")
                .append(date.substring(5,7)).append("月")
                .append(date.substring(8,10)).append("日")
                .toString();
    }


	public static int getNowYear() {
		Date nowdate = getNowDate();
		int year = nowdate.getYear() + 1900;
		return year;
	}

	/**
	 * 获取当前时间
	 * 系统内部获取时间时统一使用这个方法
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * 获取当前LocalDateTime
	 * 系统内部获取时间时统一使用这个方法
	 */
	public static LocalDateTime getNowLocalDateTime() {
		return date2LocalDateTime(getNowDate());
	}

	/**
	 * 获取当前LocalDate
	 * 系统内部获取时间时统一使用这个方法
	 */
	public static LocalDate getNowLocalDate() {
		return date2LocalDate(getNowDate());
	}

	public static String formatDateYM(Date date) {
		try {
			if(date == null){
				return null;
			}
			return dateFormatYM.get().format(date);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * String 转 LocalDateTime
	 * @param str
	 * @return
	 */
	public static LocalDateTime str2LocalDateTime(String str)
	{
		if (StringUtils.isNotBlank(str))
		{
			if (str.length() > 19)
			{
				str = str.substring(0, 19);
			}
			return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		}
		return null;
	}

	/**
	 * LocalDateTime 转 String
	 * @param localDateTime
	 * @return
	 */
	public static String localDateTime2Str(LocalDateTime localDateTime) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime);
	}

	/**
	 * LocalDateTime 转 Date
	 * @param localDateTime
	 * @return
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Date 转 LocalDateTime
	 * @param date
	 * @return
	 */
	public static LocalDateTime date2LocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	/**
	 * Date 转 LocalDate
	 * @param date
	 * @return
	 */
	public static LocalDate date2LocalDate(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * 字符串日期格式之间的转换
	 * @param sourceFormat 源日期格式
	 * @param targetFormat 目标日期格式
	 * @param sourceStrDate 源日期字符串
	 * @return
	 */
	public static String strDateConvert(String sourceFormat, String targetFormat, String sourceStrDate) {
		try {
			Date date = new SimpleDateFormat(sourceFormat).parse(sourceStrDate);
			String resultDate = new SimpleDateFormat(targetFormat).format(date);
			return resultDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}


    public static String formatDateYM2(Date date) {
        try {
            if(date == null){
                return null;
            }
            return dateFormatY_M.get().format(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 传入一个时间 返回当前月第一天
	 * 返回格式 YYY-MM-DD
     * @return
     */
    public static String getFirstDate(Object oneMonthDay){
        if(oneMonthDay == null || oneMonthDay.equals("")){
            oneMonthDay = formatDateYM2(new Date()) +"-01";
        }else{
            if (oneMonthDay instanceof Date) {
                oneMonthDay = formatDateYM2((Date)oneMonthDay) +"-01";
            }else if(oneMonthDay instanceof String){
                if(((String) oneMonthDay).length() == 10){
                    oneMonthDay = ((String) oneMonthDay).substring(0,7) + "-01";
                }else if(((String) oneMonthDay).length() >= 7 && ((String) oneMonthDay).length() < 10){
                    oneMonthDay = ((String) oneMonthDay).substring(0,7) + "-01";
                }
            }else{
                return null;
            }
        }

        return oneMonthDay.toString();
    }

	/**
	 * 获取两个日期间隔的所有日期
	 * @param start 格式必须为'2018-01-25'
	 * @param end 格式必须为'2018-01-25'
	 * @return
	 */
	public static List<String> getBetweenDate(String start, String end){
		List<String> list = new ArrayList<>();
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);

		long distance = ChronoUnit.DAYS.between(startDate, endDate);
		if (distance < 1) {
			return list;
		}
		Stream.iterate(startDate, d -> {
			return d.plusDays(1);
		}).limit(distance + 1).forEach(f -> {
			list.add(f.toString());
		});
		return list;
	}

	/**
	 * 获取传入时间(默认现在)的当月第一天 0时0分0秒
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime getFristDayOfMonth(LocalDateTime localDateTime) {
		if (null != localDateTime){
			return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
		}
		return DateUtil.getNowLocalDateTime().with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
	}

	/**
	 * 获取传入时间0时0分0秒
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime get0Hour0Minute0Second(LocalDateTime localDateTime) {
		return localDateTime.withHour(0).withMinute(0).withSecond(0);
	}

	/**
	 * 获取传入时间(默认现在)的当月最后一天 23时59分59秒
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime getLastDayOfMonth(LocalDateTime localDateTime) {
		if (null != localDateTime){
			return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
		}
		return DateUtil.getNowLocalDateTime().with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);
	}

    @SuppressWarnings("finally")
    public static Date getDate(String dateStr) {

        Date time = null;

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(getNowDate());
        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatter2;
        String dateReplace;
        String strSuccess = "";
        try {
            for (String key : dateRegFormat2.keySet()) {
                if (Pattern.compile(key).matcher(dateStr).matches()) {
                    formatter2 = new SimpleDateFormat(dateRegFormat2.get(key));
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

	/**
	 * 获得指定天数后的时间
	 * @param now
	 * @param number 正数为加，负数为减
	 * @return yyyy-MM-dd
	 */
	public static String nextDate(Date now,int number){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, number);
		now = calendar.getTime();
		return formatDateYMd(now);
	}

    /**
     * 获得指定天数后的时间
     * @param now
     * @param number 正数为加，负数为减
     * @return date
     */
    public static Date nextDayDate(Date now,int number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_MONTH, number);
        return calendar.getTime();
    }

	/**
	 * 获取下n个月的日期
	 * @param date
	 * @param number
	 * @return
	 */
	public static Date nextMonthDate(Date date, int number){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, number);
		return calendar.getTime();
	}

	/**
	 * 获取两个日期间隔多少天
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Long getBetweenDay(LocalDateTime beginDate, LocalDateTime endDate){
		return Duration.between(beginDate, endDate).toDays();
	}

    public static void main(String[] args) {

        Date before7Day = DateUtil.localDateTime2Date(DateUtil.get0Hour0Minute0Second(DateUtil.getNowLocalDateTime().plusDays(-7)));
        System.out.println(formatDateTimeDispaly(before7Day));
    }
}
