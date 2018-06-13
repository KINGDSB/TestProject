package com.dsb.tools;

/**
 * 用于验证输入参数是否合法
 * 
 */
public class ValidateUtil {
	private static final String MSG_SPLITER="\r\n";

	/**
	 * 错误信息
	 */
	private StringBuilder exceptionMsg = new StringBuilder();

	/**
	 * 增加验证，如果条件满足则保存错误信息
	 * 
	 * @param condition
	 *            错误验证条件
	 * @param msg
	 *            错误条件满足需要保存的错误信息
	 */
	public void addValidate(Boolean condition, String msg) {
		if (condition) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证是否错误
	 * 
	 * @throws BusinessException
	 */
	public void validate() throws Exception {
		if (exceptionMsg.length() > 0) {
			String exceptionMsgStr = exceptionMsg.toString();
			exceptionMsg = new StringBuilder();
			throw new Exception(exceptionMsgStr);
		}
	}

	/**
	 * 验证输入内容不为空
	 * 
	 * @param obj
	 * @param msg
	 */
	public void validateNull(Object obj, String msg) {
		if (null == obj) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证字符串是否是空白串
	 * 
	 * @param content
	 * @param msg
	 */
	public void validateBlank(String content, String msg) {
		if (null == content || "".equals(content)) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证输入内容是否为长整型
	 * 
	 * @param input
	 * @param msg
	 */
	public void validateLong(String input, String msg) {
		try {
			Long.parseLong(input);
		} catch (NumberFormatException e) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证输入内容是否是
	 * 
	 * @param input
	 * @param msg
	 */
	public void validateInteger(String input, String msg) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证输入的内容是否是布尔类型
	 * 
	 * @param input
	 * @param msg
	 */
	public void validateBoolean(String input, String msg) {
		try {
			Boolean.parseBoolean(input);
		} catch (NumberFormatException e) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证是否是正确的手机号码
	 * 
	 * @param input
	 * @param msg
	 */
	public void validatePhoneNum(String input, String msg) {
		if (!RegUtil.isPhoneNum(input)) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证输入内容是否为正确的电子邮箱
	 * 
	 * @param input
	 * @param msg
	 */
	public void validateEmail(String input, String msg) {
		if (!RegUtil.isEmail(input)) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}

	/**
	 * 验证输入的密码是否合法
	 * 
	 * @param input
	 * @param msg
	 */
	public void validatePassword(String input, String msg) {
		if (!RegUtil.checkPassword(input)) {
			exceptionMsg.append(msg).append(MSG_SPLITER);
		}
	}
}
