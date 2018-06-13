package com.dsb.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * 
 * Json 工具类
 * @author admin
 *
 */
public class JsonUtil {
	
	/**
	 * 从JsonArray获取List
	 * 
	 * @param pString
	 * @return List
	 * @throws Exception
	 */
	public static List getListFromJsArray(String pString) throws Exception {
		List reList = new ArrayList();
		if (null != pString && !"".equals(pString)) {
			if (!(JSONUtils.mayBeJSON(pString))) {
				throw new Exception("不是JSON格式数据");
			}
			JSONArray jArray = JSONArray.fromString(pString);
			for (int i = 0; i < jArray.length(); ++i) {
				if (JSONUtils.isObject(jArray.get(i))) {
					reList.add(getMapFromJsObject(((JSONObject) jArray.get(i)).toString()));
				} else {
					reList.add(jArray.get(i));
				}

			}
		}

		return reList;
	}

	/**
	 * 从Json对象里获取Map
	 * 
	 * @param pString
	 * @return Map
	 * @throws Exception
	 */
	public static Map getMapFromJsObject(String pString) throws Exception {
		Map reMap = new HashMap();
		JSONObject jObject;
		Iterator i;
		if (null != pString && !"".equals(pString)) {
			if (!(JSONUtils.mayBeJSON(pString))) {
				throw new Exception("不是JSON格式数据");
			}
			jObject = JSONObject.fromString(pString);
			if ((!(jObject.isNullObject())) && (!(jObject.isEmpty()))) {
				for (i = jObject.keys(); i.hasNext();) {
					String key = (String) i.next();
					Object obj = jObject.get(key);
					if(obj.equals(null)){
						reMap.put(key, "");
					}else if (JSONUtils.isArray(jObject.get(key))) {
						reMap.put(key, JSONArray.toList((JSONArray) jObject.get(key), HashMap.class));
					} else if (JSONUtils.isObject(jObject.get(key))) {
						reMap.put(key, getMapFromJsObject(((JSONObject) jObject.get(key)).toString()));
					} else {
						reMap.put(key, jObject.get(key));
					}
				}

			}
		}

		return reMap;
	}

	/**
	 * 把数组转换成JsonArray格式的字符串
	 * 
	 * @param pArray
	 * @return String
	 * @throws Exception
	 */
	public static String getJsonFromList(Object[] pArray) throws Exception {
		JSONArray jArray = JSONArray.fromArray(pArray);
		return jArray.toString();
	}

	/**
	 * 把Map转换成JsonObject格式字符串
	 * @param pMap
	 * @return
	 * @throws Exception
	 */
	public static String getJsonFromMap(Map pMap) throws Exception {
		JSONObject jObject = JSONObject.fromMap(pMap);
		return jObject.toString();
	}

	/**
	 * 获取bean对象的json格式
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonFromBean(Object obj) {
		JSONObject jsonObj = JSONObject.fromBean(obj);
		return jsonObj.toString();
	}
	
	/**
	 * 对象转Json
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String getJsonFromObject(Object obj) throws Exception {
		JSONObject jsObj = JSONObject.fromObject(obj);
		return jsObj.toString();
	}
	
	/**
	 * 把Json对象转换成Java对象
	 * 
	 * @param jsongObj
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object getBeanFromJson(String jsongObj, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		Map map = getMapFromJsObject(jsongObj);
		String js = getJsonFromMap(map);
		JSONObject jsObj = JSONObject.fromObject(js);
		obj = (Object)JSONObject.toBean(jsObj, clazz);
		return obj;
	}

	/**
	 * 给json对象增加属性
	 * 
	 * @param jsonObj
	 * @param appendAttr
	 * @return String
	 */
	public static String addAttribute(String jsonObj, Map<String, String> appendAttr) {
		JSONObject jsonObject = JSONObject.fromString(jsonObj);
		Iterator<String> keyIter = appendAttr.keySet().iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			String val = appendAttr.get(key);
			jsonObject.accumulate(key, val);
		}
		return jsonObject.toString();
	}
	
	/**
	 * 获取Json对象中的某个属性
	 * 
	 * @param jsonObj
	 * @param attrName
	 * @return String
	 */
	public static String getJsonObjStrAttr(String jsonObj, String attrName) {
		JSONObject tmp = null;
		try {
			tmp = JSONObject.fromBean(jsonObj);
		} catch (Exception e) {
			return null;
		}
		if (!tmp.has(attrName)) {
			return null;
		}
		return tmp.getString(attrName);
	}

}