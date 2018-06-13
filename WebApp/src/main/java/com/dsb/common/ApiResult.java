package com.dsb.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * api接口结果 Created by Dengshibao on 2017/5/19.
 */
public class ApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3320605656787295767L;

	private Map<String, Object> resultMap;

	/**
	 * 返回码
	 */
	private static final String RESULTMAP_KEY_RETURNCODE = "retCode";

	/**
	 * 返回消息
	 */
	private static final String RESULTMAP_KEY_RETMSG = "retMsg";

	/**
	 * 返回字典数据
	 */
	private static final String RESULTMAP_KEY_RETDATA = "retData";

	/**
	 * 返回列表数据
	 */
	private static final String RESULTMAP_KEY_RETLISTDATA = "retListData";

	// 设定默认JSON配置
	static {
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	}

	public ApiResult() {
		resultMap = new HashMap<String, Object>();
	}

	public ApiResult(String retCode, String retMsg, Map retData, List retListData) {
		resultMap = new HashMap<String, Object>();
		resultMap.put(RESULTMAP_KEY_RETURNCODE, retCode);
		resultMap.put(RESULTMAP_KEY_RETMSG, retMsg);
		resultMap.put(RESULTMAP_KEY_RETDATA, retData);
		resultMap.put(RESULTMAP_KEY_RETLISTDATA, retListData);
	}

	/**
	 * 设置公共出参消息
	 *
	 * @param retCode
	 * @param retMsg
	 */
	public void setReturnCode(String retCode, String retMsg) {
		resultMap.put(RESULTMAP_KEY_RETURNCODE, retCode);
		resultMap.put(RESULTMAP_KEY_RETMSG, retMsg);
	}

	/**
	 * 设置成功的返回消息
	 */
	public void setSuccess() {
		resultMap.put(RESULTMAP_KEY_RETURNCODE, ApiResultCode.RETURNCODE_SUCCESS);
		resultMap.put(RESULTMAP_KEY_RETMSG, ApiResultCode.RETURNMSG_SUCCESS);
	}

	/**
	 * 设置输入数据为空的返回消息
	 */
	public void setInputIsempty() {
		resultMap.put(RESULTMAP_KEY_RETURNCODE, ApiResultCode.RETURNCODE_INPUT_ISEMPTY_ERROR);
		resultMap.put(RESULTMAP_KEY_RETMSG, ApiResultCode.RETURNMSG_INPUT_ISEMPTY_ERROR);
	}

	/**
	 * 设置查询结果为空的返回消息
	 */
	public void setResultIsempty() {
		resultMap.put(RESULTMAP_KEY_RETURNCODE, ApiResultCode.RETURNCODE_RESULT_ISEMPTY);
		resultMap.put(RESULTMAP_KEY_RETMSG, ApiResultCode.RETURNMSG_RESULT_ISEMPTY);
	}

	/**
	 * 设置业务异常的返回消息
	 *
	 * @param msg
	 */
	public void setBusinessError(String msg) {
		resultMap.put(RESULTMAP_KEY_RETURNCODE, ApiResultCode.RETURNCODE_BUSINESS_ERROR);
		resultMap.put(RESULTMAP_KEY_RETMSG, msg);
	}

	/**
	 * 获取Json格式的成功返回信息
	 *
	 * @return
	 */
	public String getSuccseeJsonResult() {
		this.setSuccess();
		return getJsonResult();
	}

	/**
	 * 获取带数据的Json格式的成功返回信息
	 *
	 * @param retData
	 * @param retListData
	 * @return
	 */
	public String getSuccseeJsonResult(Map retData, List retListData) {
		this.setSuccess();
		resultMap.put(RESULTMAP_KEY_RETDATA, retData);
		resultMap.put(RESULTMAP_KEY_RETLISTDATA, retListData);
		return getJsonResult();
	}

	/**
	 * 获取Json格式的输入为空返回信息
	 *
	 * @return
	 */
	public String getInputIsEmptyJsonResult() {
		this.setInputIsempty();
		return getJsonResult();
	}

	/**
	 * 获取Json格式的查询结果为空返回信息
	 *
	 * @return
	 */
	public String getResultIsemptyJsonResult() {
		this.setResultIsempty();
		return getJsonResult();
	}

	/**
	 * 获取Json格式的返回信息
	 *
	 * @return
	 */
	public String getJsonResult(String retCode, String retMsg) {
		resultMap.put(RESULTMAP_KEY_RETURNCODE, retCode);
		resultMap.put(RESULTMAP_KEY_RETMSG, retMsg);
		return getJsonResult();
	}

	/**
	 * 设置retData返回信息
	 *
	 * @param returnInfo
	 */
	public void setReturnInfo(Object returnInfo) {
		resultMap.put(RESULTMAP_KEY_RETDATA, returnInfo);
	}

	/**
	 * 添加retData返回信息
	 *
	 * @param infoKey
	 *            返回值key
	 * @param infoObject
	 *            返回值对象
	 */
	public void addReturnInfo(String infoKey, Object infoObject) {

		Map map = (Map) resultMap.get(RESULTMAP_KEY_RETDATA);
		if (map == null) {
			map = new HashMap();
		}
		map.put(infoKey, infoObject);
		resultMap.put(RESULTMAP_KEY_RETDATA, map);
	}

	/**
	 * 设置retListData返回信息
	 *
	 * @param retListData
	 */
	public <T extends Collection<?>> void setReturnListInfo(T retListData) {
		resultMap.put(RESULTMAP_KEY_RETLISTDATA, retListData);
	}

	/**
	 * 获取Json格式的返回信息
	 *
	 * @return
	 */
	public String getJsonResult(String retCode, String retMsg, Map retData, List retListData) {
		resultMap.put(RESULTMAP_KEY_RETURNCODE, retCode);
		resultMap.put(RESULTMAP_KEY_RETMSG, retMsg);
		resultMap.put(RESULTMAP_KEY_RETDATA, retData);
		resultMap.put(RESULTMAP_KEY_RETLISTDATA, retListData);
		return getJsonResult();
	}

	/**
	 * 获取Json格式的返回信息
	 *
	 * @return
	 */
	public String getJsonResult() {
		try {
			// return JSON.toJSONString(resultMap,
			// SerializerFeature.WriteDateUseDateFormat);
			return JSON.toJSONString(resultMap, SerializerFeature.WriteNullListAsEmpty,
					SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
					SerializerFeature.WriteNullBooleanAsFalse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JSONObject json = new JSONObject();
			json.put(RESULTMAP_KEY_RETURNCODE, ApiResultCode.RETURNCODE_SYSTEM_ERROR);
			json.put(RESULTMAP_KEY_RETMSG, ApiResultCode.RETURNMSG_SYSTEM_ERROR);
			return JSON.toJSONString(json, SerializerFeature.WriteNullListAsEmpty,
					SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
					SerializerFeature.WriteNullBooleanAsFalse);
		}
	}

	/**
	 * 获取Json格式的返回信息
	 *
	 * @return
	 */
	public boolean isSuccess() {
		return ApiResultCode.RETURNCODE_SUCCESS.equals(resultMap.get(RESULTMAP_KEY_RETURNCODE));
	}

	/**
	 * 添加返回消息 用来和新jar包格式统一
	 *
	 * @param infoKey
	 *            返回值key
	 * @param infoObject
	 *            返回值对象
	 */
	@Deprecated
	public void addReturnMsg(String infoKey, Object infoObject) {
		resultMap.put(infoKey, infoObject);
	}
}
