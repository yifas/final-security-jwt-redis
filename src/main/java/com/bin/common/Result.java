package com.bin.common;

import com.bin.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @description 返回结果统一处理类
 *
 * @author Administrator
 * @date 2020/9/22 11:41 上午
 */
@Slf4j
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE = "code";

    /** 返回内容 */
    public static final String MSG = "msg";

    /** 数据对象 */
    public static final String DATA = "data";

    /**
     * 初始化一个新创建的 Result 对象，使其表示一个空消息。
     */
    public Result() {

    }


    public Result(String msg) {
        super.put(CODE, ResultEnum.SUCCESS.getCode());
        super.put(MSG, msg);
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public Result(int code, String msg) {
        super.put(CODE, code);
        super.put(MSG, msg);
    }

    /**
     * 初始化一个新创建的 Result 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public Result(int code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if (data != null) {
            super.put(DATA, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Result success() {
        return Result.success("SUCCESS");
    }

    /**
     * 返回成功消息
     *
     * @param codeEnum 返回内容
     * @return 成功消息
     */
    public static Result success(ResultEnum codeEnum) {
        return new Result(codeEnum.getCode(), codeEnum.getMsg());
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Result success(Object data) {
        return Result.success("SUCCESS", data);
    }

    /**(String) param.get("goodsId")
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Result success(String msg) {
        return new Result(msg);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Result success(String msg, Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 失败
     */
    public static Result error() {
        int code = ResultEnum.FAILURE.getCode();
        log.error("code: {}, msg {}", code, "操作失败");
        return Result.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(String msg) {
        int code = ResultEnum.FAILURE.getCode();
        log.error("code: {}, msg {}", code, msg);
        return new Result(ResultEnum.FAILURE.getCode(), msg);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Result error(String msg, Object data) {
        int code = ResultEnum.FAILURE.getCode();
        log.error("code: {}, msg {}", code, msg);
        return new Result(code, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Result error(int code, String msg) {
        log.error("code: {}, msg {}", code, msg);
        return new Result(code, msg);
    }

    /**
     * 返回错误消息
     *
     * @param codeEnum 状态码
     * @return 警告消息
     */
    public static Result error(ResultEnum codeEnum) {
        log.error("code: {}, msg {}", codeEnum.getCode() ,codeEnum.getMsg());
        return new Result(codeEnum.getCode(), codeEnum.getMsg());
    }
}
