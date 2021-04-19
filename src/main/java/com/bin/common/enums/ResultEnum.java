package com.bin.common.enums;

import lombok.Getter;

/**
 * @description 返回数据信息
 *
 * @author Administrator
 * @date 2020/9/22 11:32 上午
 */
@Getter
public enum ResultEnum {
    /**
     * 返回码和返回信息
     */
    SUCCESS(200, "成功"),
    FAILURE(201, "失败"),
    INSUFFICIENT_BALANCE(202, "余额不足"),
    ENCRYPTED_FILE(203, "解析失败，该文件是加密文件"),
    PARAM_ERROR(400, "参数错误"),
    USER_NO_PERMISSION(401, "您没有权限访问"),
    LOGIN_OVERDUE(402, "登录已失效"),
    USER_NO_ACCESS(403, "未登录"),
    NOT_FOUND(404, "资源找不到"),
    NOT_REQUEST(406, "不支持的请求方式"),
    BIND_ERROR(407, "绑定错误"),
    RESOURCE_IS_DELETED(410, "资源被删除"),
    FILE_SIZE_LIMIT(413, "文件太大了, 请压缩后再使用"),
    TIME_OUT(504, "超时"),
    SYSTEM_ERROR(500, "系统异常，请稍后再试");

    private final int code;

    private final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
