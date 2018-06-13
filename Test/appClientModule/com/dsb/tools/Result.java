package com.dsb.tools;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务接口返回结果信息 Created by Sky.Zeng on 2015/4/30.
 */
public class Result<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9181957894772368700L;
    private T result;
    private Map<String, String> appendInfos = new HashMap<String, String>();
    private ErrorData errorData;

    public Result() {
        result = null;
        errorData = null;
    }

    public Result(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ErrorData getErrorData() {
        return errorData;
    }

    public void setErrorData(ErrorData errorData) {
        this.errorData = errorData;
    }

    public Map<String, String> getAppendInfos() {
        return appendInfos;
    }

    public void setAppendInfos(Map<String, String> appendInfos) {
        this.appendInfos = appendInfos;
    }

    public void appendInfo(String name, String val) {
        this.appendInfos.put(name, val);
    }

    public static ErrorData createErrorData(String errorCode, String errorMsg) {
        ErrorData errorData = new ErrorData();
        errorData.setErrorCode(errorCode);
        errorData.setErrorMessage(errorMsg);
        return errorData;
    }

    public static class ErrorData implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = -4335217511134696228L;
        private String errorCode;
        private String errorMessage;

        public ErrorData() {
            this.errorCode = null;
            this.errorMessage = null;
        }

        public ErrorData(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
