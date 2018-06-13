package com.dsb.common;

/**
 * api结果编号常量类
 * @author dengshibao
 *
 */
public class ApiResultCode {
	
	/**
	 * 成功Code
	 */
	public static final String RETURNCODE_SUCCESS = "0000";
	
	/**
	 * 成功msg
	 */
	public static final String RETURNMSG_SUCCESS = "操作成功!";
	
	/**
	 * 用户不存在code
	 */
	public static final String RETURNCODE_USER_NOT_EXIST = "0001";

	/**
	 * 用户不存在msg
	 */
	public static final String RETURNMSG_USER_NOT_EXIST = "用户不存在!";

	/**
	 * 密码错误code
	 */
	public static final String RETURNCODE_PASSWORD_ERROR = "0002";

	/**
	 * 密码错误msg
	 */
	public static final String RETURNMSG_PASSWORD_ERROR = "密码错误!";

	/**
	 * 输入数据为空code
	 */
	public static final String RETURNCODE_INPUT_ISEMPTY_ERROR = "0003";

	/**
	 * 输入数据为空msg
	 */
	public static final String RETURNMSG_INPUT_ISEMPTY_ERROR = "输入数据为空!";

	/**
	 * 业务异常code
	 */
	public static final String RETURNCODE_BUSINESS_ERROR = "0004";

	/**
	 * 查询结果为空code
	 */
	public static final String RETURNCODE_RESULT_ISEMPTY = "0005";

	/**
	 * 查询结果为空msg
	 */
	public static final String RETURNMSG_RESULT_ISEMPTY = "查询结果为空!";

	/**
	 * 乐欣接口异常code
	 */
	public static final String RETURNCODE_LEXIN_ERROR = "0006";

	/**
	 * 乐欣接口异常msg
	 */
	public static final String RETURNMSG_LEXIN_ERROR = "乐欣接口异常!";

	/**
	 * 设备类型异常code
	 */
	public static final String RETURNCODE_DEVICE_TYPE_ERROR = "0007";

	/**
	 * 设备类型异常msg
	 */
	public static final String RETURNMSG_DEVICE_TYPE_ERROR = "设备类型异常!";

	/**
	 * 设备类型异常code
	 */
	public static final String RETURNCODE_MT_ERROR = "0008";

	/**
	 * 设备类型异常msg
	 */
	public static final String RETURNMSG_MT_ERROR = "门店投资中心员工请切换测试环境!";

	/**
	 * token令牌错误
	 */
	public static final String RETURNCODE_TOKEN_FAIL= "9999";

	/**
	 * token令牌错误
	 */
	public static final String RETURNMSG_TOKEN_FAIL= "token令牌错误";

	/**
	 * 系统内部错误code
	 */
	public static final String RETURNCODE_SYSTEM_ERROR = "2000";

	/**
	 * 系统内部错误msg
	 */
	public static final String RETURNMSG_SYSTEM_ERROR = "系统异常!";

	
}
