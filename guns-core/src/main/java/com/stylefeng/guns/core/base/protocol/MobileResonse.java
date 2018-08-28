package com.stylefeng.guns.core.base.protocol;

/**
 * Created by fanliwei on 2015/11/15.
 */
public class MobileResonse {

    public static final String SUCCESS_CODE = "0";
    public static final String SUCCESS_MESSAGE = "操作成功";

    private String code;
    private String message;
    private Object data;

    public MobileResonse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public MobileResonse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static MobileResonse success() {
        return new MobileResonse(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static MobileResonse success(Object data) {
        return new MobileResonse(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static MobileResonse success(String message, Object data) {
        return new MobileResonse(SUCCESS_CODE, message, data);
    }

    public static MobileResonse error(String errorCode, String message, String data) {
        return new MobileResonse(errorCode, message, data);
    }

    /**
     * 
     * error:向客户端发送异常信息，并记录异常日志 <br/>
     * 
     * @param mobileError
     *            异常信息对象
     * @return 异常响应结果 <br/>
     */
    public static MobileResonse error(MobileError mobileError) {
        return new MobileResonse(mobileError.getErrorCode(), mobileError.getErrorMessage());
    }

    public static MobileResonse error(MobileError mobileError, String logInfo) {
        return new MobileResonse(mobileError.getErrorCode(), mobileError.getErrorMessage());
    }

    /**
     * 
     * error:向客户端发送异常信息，并记录自定义异常日志 <br/>
     * 
     * @param mobileError
     *            异常信息对象
     * @param logInfo
     *            自定义日志信息
     * @return 异常响应结果 <br/>
     */
    public static MobileResonse errorWithLog(MobileError mobileError, String logInfo) {
        return new MobileResonse(mobileError.getErrorCode(), mobileError.getErrorMessage());
    }

    /**
     * 
     * error:向客户端发送自定义异常提示信息，并记录自定义异常日志 <br/>
     * 
     * @param mobileError
     *            异常信息对象
     * @param clientInfo
     *            自定义客户端异常提示信息
     * @param logInfo
     *            自定义日志信息
     * @return 异常响应结果 <br/>
     */
    public static MobileResonse errorWithLog(MobileError mobileError, String clientInfo, String logInfo) {
        return new MobileResonse(mobileError.getErrorCode(), clientInfo);
    }
}
