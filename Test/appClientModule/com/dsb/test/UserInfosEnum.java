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
public enum UserInfosEnum {
	NAME			(1<<0, "姓名"),
	SEX				(1<<1, "性别"),
	MOBILE			(1<<2, "手机号码"),
	BIRTHDAY		(1<<3, "生日"),
	COMPANY			(1<<4, "公司"),
	POSITION		(1<<5, "职位"),
	ADDRESS			(1<<6, "地址");
	
	private int code;
	private String name;
	
	private UserInfosEnum(int code, String name) {
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
	public static List<UserInfosEnum> showList(int privilege){
		List<UserInfosEnum> list = null;
		
		if (0 >= privilege) {
			list = Arrays.asList(UserInfosEnum.values());
		} else {
			list = new ArrayList<UserInfosEnum>();
			for (UserInfosEnum tmp : UserInfosEnum.values()) {
				if ((privilege & tmp.getCode()) == tmp.getCode()) {// 包含当前权限
					list.add(tmp);
				}
			}
		}
		return list;
	}
	
	public static String showAllString(){
		StringBuffer sb = new StringBuffer();
		for (UserInfosEnum tmp : UserInfosEnum.values()) {
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
		
		// 陌生人
		int stranger = UserInfosEnum.NAME.getCode() 
					 | UserInfosEnum.SEX.getCode() 
					 | UserInfosEnum.MOBILE.getCode();
		
		// 同事
		int workmate = UserInfosEnum.NAME.getCode() 
					 | UserInfosEnum.SEX.getCode() 
					 | UserInfosEnum.MOBILE.getCode()
					 | UserInfosEnum.COMPANY.getCode()
					 | UserInfosEnum.POSITION.getCode();
		
		// 家人
		int family = UserInfosEnum.NAME.getCode() 
				 | UserInfosEnum.SEX.getCode() 
				 | UserInfosEnum.MOBILE.getCode()
				 | UserInfosEnum.COMPANY.getCode()
				 | UserInfosEnum.POSITION.getCode()
				 | UserInfosEnum.BIRTHDAY.getCode()
				 | UserInfosEnum.ADDRESS.getCode();
		
		// user1 陌生人
		int user1 = stranger;
		
		if ((user1 & UserInfosEnum.ADDRESS.getCode()) == UserInfosEnum.ADDRESS.getCode()) {
			System.out.println("user1知道你的家庭地址");
		}else {
			System.out.println("user1不知道你的家庭地址");
		}
		
		// user1 陌生人 -> 家人
		user1 |= family;

		if ((user1 & UserInfosEnum.ADDRESS.getCode()) == UserInfosEnum.ADDRESS.getCode()) {
			System.out.println("user1知道你的家庭地址");
		}else {
			System.out.println("user1不知道你的家庭地址");
		}
		
	}
	
}
