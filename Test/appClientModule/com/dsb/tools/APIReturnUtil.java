package com.dsb.tools;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口返回工具
 * Created by Dengshibao on 2017/5/19.
 */
public class APIReturnUtil implements Serializable {

    private String      retCode;                // 返回码
    private String      retMsg;                 // 返回消息
    private Map         retInfo;                // 返回字典数据
    private List        retListInfo;            // 返回列表数据

    public APIReturnUtil() {}

    public APIReturnUtil(String retCode, String retMsg, Map retInfo, List retListInfo) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.retInfo = retInfo;
        this.retListInfo = retListInfo;
    }

    /**
     * 设置公共出参消息
     *
     * @param retCode
     * @param retMsg
     */
    public void setReturnCode(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    /**
     * 设置成功的返回消息
     */
    public void setSuccess() {
//        this.retCode = APPReturnConstants.RETURNCODE_SUCCESS;
//        this.retMsg = APPReturnConstants.RETURNMSG_SUCCESS;
    }

    /**
     * 设置输入数据为空的返回消息
     */
    public void setInputIsempty() {
//        this.retCode = APPReturnConstants.RETURNCODE_INPUT_ISEMPTY_ERROR;
//        this.retMsg = APPReturnConstants.RETURNMSG_INPUT_ISEMPTY_ERROR;
    }

    /**
     * 设置查询结果为空的返回消息
     */
    public void setResultIsempty() {
//        this.retCode = APPReturnConstants.RETURNCODE_RESULT_ISEMPTY;
//        this.retMsg = APPReturnConstants.RETURNMSG_RESULT_ISEMPTY;
    }

    /**
     * 获取Json格式的成功返回信息
     *
     * @return
     */
    public String getSuccseeJsonReturnInfo(){
        this.setSuccess();
        return getJsonReturnInfo();
    }

    /**
     * 获取Json格式的成功返回信息
     *
     * @param retInfo
     * @param retListInfo
     * @return
     */
    public String getSuccseeJsonReturnInfo(Map retInfo, List retListInfo){
        this.setSuccess();
        this.retInfo = retInfo;
        this.retListInfo = retListInfo;
        return getJsonReturnInfo();
    }

    /**
     * 获取Json格式的输入为空返回信息
     *
     * @return
     */
    public String getInputIsEmptyJsonReturnInfo(){
        this.setInputIsempty();
        return getJsonReturnInfo();
    }

    /**
     * 获取Json格式的查询结果为空返回信息
     *
     * @return
     */
    public String getResultIsemptyJsonReturnInfo(){
        this.setResultIsempty();
        return getJsonReturnInfo();
    }

    /**
     * 获取Json格式的返回信息
     *
     * @return
     */
    public String getJsonReturnInfo(String retCode, String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
        return getJsonReturnInfo();
    }

    /**
     * 添加map返回信息
     *
     * @param infoKey 返回值key
     * @param infoObject 返回值对象
     */
    public void addReturnInfo(String infoKey, Object infoObject) {

        if (retInfo == null) {
            retInfo = new HashMap();
        }
        retInfo.put(infoKey, infoObject);
    }

    /**
     * 获取Json格式的返回信息
     *
     * @return
     */
    public String getJsonReturnInfo(String retCode, String retMsg, Map retInfo, List retListInfo) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.retInfo = retInfo;
        this.retListInfo = retListInfo;
        return getJsonReturnInfo();
    }

    /**
     * 获取Json格式的返回信息
     *
     * @return
     */
    public String getJsonReturnInfo(){
        return JsonUtil.getJsonFromBean(this);
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Map getRetInfo() {
        return retInfo;
    }

    public void setRetInfo(Map retInfo) {
        this.retInfo = retInfo;
    }

    public List getRetListInfo() {
        return retListInfo;
    }

    public void setRetListInfo(List retListInfo) {
        this.retListInfo = retListInfo;
    }
    
}
