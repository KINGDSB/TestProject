package com.dsb.tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	private static final String PHONE_REG = "^1[0-9]{10}$";
	private static final String EMAIL_REG = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	private static final String QQ_REG = "^[0-9]{5,10}$";
	private static final String USERNAME = ".{1,16}";
	private static final String PASSWORD_RULE = "[0-9a-zA-Z`~!@#$%^&*()-_=+]{6,20}";

	/**
	 * 验证密码格式，6~20位数字和字母或!&组合，且非纯数字或纯字母
	 * 
	 * @param input
	 * @return
	 */
	public static boolean checkPassword(String input) {
		return input.matches(PASSWORD_RULE) && !input.matches("[0-9]*") && !input.matches("[a-zA-z]*");
	}

	/**
	 * 验证输入是否是手机号码
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isPhoneNum(String input) {
		return isMatch(input, PHONE_REG);
	}

	/**
	 * 验证输入是否是邮箱帐号
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isEmail(String input) {
		return isMatch(input, EMAIL_REG);
	}

	/**
	 * 验证输入是否是QQ号
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isQQ(String input) {
		return isMatch(input, QQ_REG);
	}

	/**
	 * 验证输入是否是正常的用户名
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isUserName(String input) {
		return isMatch(input, USERNAME);
	}

	/**
	 * 判断输入是否是数字
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isNumber(String input) {
		return isMatch(input, "[0-9]*");
	}
	
	/**
	 * 判断输入的内容是否满足正则表达式
	 * 
	 * @param input
	 * @param regex
	 * @return
	 */
	public static boolean isMatch(String input, String regex) {
		return input.matches(regex);
	}

	/**
	 * 截取字符串中指定格式内容
	 * 
	 * @param input
	 * @param regex
	 * @return
	 */
	public static String captureStr(String input, String regex) {
		Pattern patter = Pattern.compile(regex);
		Matcher matcher = patter.matcher(input);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/**
	 * 截取字符串中匹配正则表达式的内容
	 * 
	 * @param input
	 * @param regex
	 * @return
	 */
	public static String[] captureMatchStrs(String input, String regex) {
		Pattern patter = Pattern.compile(regex);
		Matcher matcher = patter.matcher(input);
		List<String> matchs = new ArrayList<String>();
		while (matcher.find()) {
			matchs.add(matcher.group(1));
		}
		return matchs.toArray(new String[0]);
	}

	public static List<String[]> captureAlllMatchStrs(String input, String regex) {
		List<String[]> result = new LinkedList<String[]>();
		Pattern patter = Pattern.compile(regex);
		Matcher matcher = patter.matcher(input);
		List<String> matchs = new ArrayList<String>();
		int i = 1;
		while (i < matcher.groupCount()) {
			matchs.add(matcher.group(i));
		}

		return result;
	}

}
