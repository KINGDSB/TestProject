package com.dsb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户信息枚举
 * 
 * @author admin
 *
 */
public enum UserInfoEnum {
	NAME			(1<<0, "姓名"),
	SEX				(1<<1, "性别"),
	MOBILE1			(1<<2, "手机号码1"),
	MOBILE2			(1<<3, "手机号码2"),
	MOBILE3			(1<<4, "手机号码3"),
	BIRTHDAY		(1<<5, "生日"),
	LUNARBIRTHDAY	(1<<6, "农历生日"),
	COMPANY			(1<<7, "公司"),
	POSITION		(1<<8, "职位"),
	EMAIL1			(1<<9, "邮箱1"),
	EMAIL2			(1<<10,"邮箱2"),
	ADDRESS1		(1<<11,"地址1"),
	ADDRESS2		(1<<12,"地址2"),
	ADDRESS3		(1<<13,"地址3"),
	EDUCATION		(1<<14,"学历");
	
	private int code;
	private String name;
	
	private UserInfoEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	

	/**
	 * 合并权限
	 */
	public static int mergePrivilege(int oldPrivilege, int newPrivilege){
		return oldPrivilege | newPrivilege;
	}
	
	/**
	 * 根据权限获取用户信息项列表
	 * 
	 * @param Privilege <=0:获取所有  >0:获取对应的项
	 * @return
	 */
	public static List<UserInfoEnum> showList(int privilege){
		List<UserInfoEnum> list = null;
		
		if (0 >= privilege) {
			list = Arrays.asList(UserInfoEnum.values());
		} else {
			list = new ArrayList<UserInfoEnum>();
			for (UserInfoEnum tmp : UserInfoEnum.values()) {
				if ((privilege & tmp.getCode()) == tmp.getCode()) {// 包含当前权限
					list.add(tmp);
				}
			}
		}
		return list;
	}
	
	public static String showAllString(){
		StringBuffer sb = new StringBuffer();
		for (UserInfoEnum tmp : UserInfoEnum.values()) {
			sb.append(tmp.code+"-"+tmp.name+"\n");
		}
		return sb.toString();
	}
	
	public String show() {
		return this.code+"-"+this.name;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return name;
	}
	public void setMsg(String msg) {
		this.name = msg;
	}
	
	public static void main(String[] args) {
		System.out.println(showAllString());
//		System.out.println(11 | 7);
		
		int num = NAME.getCode() + SEX.getCode()+MOBILE1.getCode()+MOBILE2.getCode();

		System.out.println(4&4);
		System.out.println(num & 16);
		System.out.println(16 & num);
		System.out.println(num | 16);
		System.out.println(num);
		
//		System.out.println(JsonUtil.getJsonFromBean(showList(11)));
		
	}
	
}
