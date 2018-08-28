package com.stylefeng.guns.core.base.protocol;

/**
 * Created by fanliwei on 2015/11/15.
 */
public enum MobileError {

    /**
     * 错误码一共8位，前4位表示错误类型，后4位为具体错误编码，依次递增
     */
    // 系统错误
    SYSTEM_ERROR("10000000", "系统错误"),

    VALID_ERROR("10000002", "校验失败"),

    PARAMS_ERROR("10000003", "参数错误"),

    UNLOGIN("10000005", "用户未登录"),

    TIME_OUT("10000006", "已过期"),

    PERMISSION_DENIDED("10000007", "没有权限"),

    // 小程序
    WX_UNAUTHORIZATION("10022001", "登录未授权"),

    WX_UN_CODE_LOGIN("10022002", "未code登录"),

    WX_UN_SIGN_MEMBER("10022003", "未绑定会员");

    private final String errorCode;
    private final String errorMessage;

    private MobileError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
