package com.dsb.test;

public enum FenZu {
	FAMILY(1,"家庭"),
	FRIEND_LIST1(2,"朋友列表1"),
	FRIEND_LIST2(3,"朋友列表2"),
	FRIEND_LIST3(4,"朋友列表3"),
	FRIENDS(5,"朋友"),
	OTHER(6,"其他"),
	WATER(7,"水");
	
	private int code;
	private String msg;
	
	private FenZu(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static String showAll(){
		StringBuffer sb = new StringBuffer();
		for (FenZu tmp : FenZu.values()) {
			sb.append(tmp.code+"-"+tmp.msg);
		}
		return sb.toString();
	}
	
	public String show() {
		return this.code+"-"+this.msg;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
