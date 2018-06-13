package com.dsb.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class TaskCycleExpressionUtils {
	
	/**
	 * 
	 * 匹配周期任务表达式
		表达式详情
		为 * 忽略
		每n年	第n月	每n月	第n天	第n周	每n周	星期数n	每n天
		每日
		*		*		*		*		*		*		*		n
		*		*		*		*		*		*		*		nw
		*		*		*		*		*		*		*		n
		每周
		*		*		*		*		*		n		n,x,y	*
		每月
		*		*		n		n		*		*		*		*
		*		*		n		*		n		*		n,x,y	*
		每年
		n		n		*		n		*		*		*		*
		n		n		*		*		n		*		n,x,y	*
	 * @param baseDate 基础日期
	 * @param expression
	 * @return
	 */
	public static boolean matchTaskCycleExpression(Date baseDate, String expression){
		boolean flag = false;
		
		if (null != baseDate && StringUtils.isNotBlank(expression)) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(baseDate);
			
			String[] items = expression.split(" ");
			Set<String> matchDateSet = new HashSet<String>();
			
			for (int i = 0; i < items.length; i++) {
				String item = items[i];
				if (!"*".equals(item)) {
					
					switch (i) {
						case 0:
							int year = Integer.parseInt(item);
							calendar.add(Calendar.YEAR, year);
							break;
						case 1:
							int dmonth = Integer.parseInt(item);
							calendar.set(Calendar.MONTH, dmonth - 1);
							break;
						case 2:
							int mmonth = Integer.parseInt(item);
							calendar.add(Calendar.MONTH, mmonth);
							break;
						case 3:
							int day_of_month = Integer.parseInt(item);
							calendar.set(Calendar.DAY_OF_MONTH, day_of_month);
							matchDateSet.add(DateUtils.date2Str(calendar.getTime(), DateUtils.FORMAT_YYYY_MM_DD));
							break;
						case 4:
							int week_of_month = Integer.parseInt(item);
							calendar.set(Calendar.WEEK_OF_MONTH, week_of_month);
							break;
						case 5:
							int mweek_of_month = Integer.parseInt(item);
							calendar.add(Calendar.WEEK_OF_MONTH, mweek_of_month);
							break;
						case 6:
							for (String sday_of_week : item.split(",")) {
								int day_of_week = Integer.parseInt(sday_of_week);
								Calendar tmp = Calendar.getInstance();
								tmp.setTime(calendar.getTime());
								tmp.set(Calendar.DAY_OF_WEEK, day_of_week + 1);

								matchDateSet.add(DateUtils.date2Str(tmp.getTime(), DateUtils.FORMAT_YYYY_MM_DD));
							}
							break;
						case 7:
							if (item.endsWith("w") || item.endsWith("W")) {
								int day_of_year = Integer.parseInt(item.substring(0, item.length()-1));
								while (day_of_year > 0) {
									calendar.add(Calendar.DAY_OF_YEAR, 1);
									if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
										day_of_year -- ;
									}
								}
							} else {
								int day_of_year = Integer.parseInt(item);
								calendar.add(Calendar.DAY_OF_YEAR, day_of_year);
							}
							matchDateSet.add(DateUtils.date2Str(calendar.getTime(), DateUtils.FORMAT_YYYY_MM_DD));
							break;
					}
				}
			}

			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			for (String string : matchDateSet) {
				System.out.println(string);
			}
			
			flag = matchDateSet.contains(DateUtils.getNowDateString(DateUtils.FORMAT_YYYY_MM_DD));
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
			
		}
		
		return flag;
	}
	
	public static void main(String[] args) {
		Calendar tmp = Calendar.getInstance();
		
//		tmp.add(Calendar.DAY_OF_YEAR, -7);
//		System.out.println(matchTaskCycleExpression(tmp.getTime(), "* * * * * * * 5w")); // 5个工作日

//		tmp.add(Calendar.DAY_OF_YEAR, -14);
//		System.out.println(matchTaskCycleExpression(tmp.getTime(), "* * * * * 2 3,5 *")); // 2周后的星期三和星期五

//		tmp.add(Calendar.MONTH, -2);
//		System.out.println(matchTaskCycleExpression(tmp.getTime(), "* * 2 21 * * * *")); // 2个月后的第21日

//		tmp.add(Calendar.MONTH, -2);
//		System.out.println(matchTaskCycleExpression(tmp.getTime(), "* * 2 21 * * * *")); // 2个月后的第21日
		
//		tmp.add(Calendar.MONTH, -2);
//		System.out.println(matchTaskCycleExpression(tmp.getTime(), "* * 2 * 4 * 5 *")); // 2个月后的第4周的星期五

//		tmp.add(Calendar.YEAR, -2);
//		tmp.add(Calendar.MONTH, -2);
//		tmp.add(Calendar.DAY_OF_YEAR, -10);
//		System.out.println(matchTaskCycleExpression(tmp.getTime(), "2 7 * 21 * * * *")); // 2年后的7月21日

		tmp.add(Calendar.YEAR, -2);
		tmp.add(Calendar.MONTH, -2);
		tmp.add(Calendar.DAY_OF_YEAR, -10);
		System.out.println(matchTaskCycleExpression(tmp.getTime(), "2 7 * * 4 * 5 *")); // 2年后的第4周的星期五
		
		System.out.println(DateUtils.date2Str(tmp.getTime(), DateUtils.FORMAT_YYYY_MM_DD));
		
		
	}
}
