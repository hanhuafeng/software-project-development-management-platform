package com.example.software.project.development.management.platform.response;

/**
 * 返回结果编码
 *
 * @author hanhuafeng
 * @date 2019/1/3 11:24
 */
public enum ResultCode {

    //操作成功
    SUCCESS(true, 10000, "操作成功！"),

    //操作成功 英文
    SUCCESS_ENGLISH(true,10000, "success"),

    //操作失败
    FAIL(false, 11111, "操作失败！"),

    // 操作失败
    FAIL_ENGLISH(false, 11111, "fail"),

    //参数异常
    INVALIDPARAM(false, 10003, "参数异常！"),

    //未登录信息
    UNAUTHENTICATED(false, 10001, "此操作需要登陆系统！"),

    //权限不足
    UNAUTHORISE(false, 10002, "权限不足，无权操作！"),

    //系统繁忙
    SERVER_ERROR(false, 99999, "抱歉，系统繁忙，请稍后重试！"),

    //验证异常
    VERIFY_ERROR(false,10004),

    //pki登录错误
    CERTIFICATE_NOT_EXIST(false,20602,"证书不存在！请插入数字证书，关闭浏览器，再重新打开浏览器，然后再以PKI方式登录系统！"),

    CERTIFICATE_IS_OVER (false,20603," 用户已经存在!");

    /**
     * 是否请求成功
     */
    boolean success;

    /**
     * 返回编码
     */
    int code;

    /**
     * 返回信息
     */
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    ResultCode(boolean success, int code) {
        this.success = success;
        this.code = code;
    }
}
