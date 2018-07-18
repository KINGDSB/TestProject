package com.dsb.example.demo.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
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

	private static final ThreadLocal<DateFormat> dateFormatYMd = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
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

	public static Date stringToDate(String str) {
		if(StringUtils.isEmpty(str)){
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
		if(StringUtils.isEmpty(str)){
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
		if(date.length() != 10){
			return null;
		}
		String substring = date.substring(0, date.length()-3);
		Calendar calendar=new GregorianCalendar();
		Date resDate = parseDateYMD(substring+"-01");
		calendar.setTime(resDate);
		calendar.roll(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
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
        if(!StringUtils.isEmpty(parDate)){
            date = formatDateYMd(new Date());
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
}
