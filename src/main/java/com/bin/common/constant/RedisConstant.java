package com.bin.common.constant;

/**
 * @description redis常量
 *
 * @author Administrator
 * @date 2020/9/27 8:57 上午
 */
public class RedisConstant {
    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Token_";

    /**
     * 登录短信间隔前缀
     */
    public static final String SMS_LOGIN_INTERVALS_PREFIX = "SmsLoginIntervals_";

    /**
     * 登录短信验证前缀
     */
    public static final String SMS_LOGIN_VERIFICATION_PREFIX = "SmsLoginVerification_";

    /**
     * 注册短信间隔前缀
     */
    public static final String SMS_REGISTERED_INTERVALS_PREFIX = "SmsRegisteredIntervals_";

    /**
     * 注册短信验证前缀
     */
    public static final String SMS_REGISTERED_VERIFICATION_PREFIX = "SmsRegisteredVerification_";

    /**
     * 修改密码短信验证前缀
     */
    public static final String SMS_PASSWORD_CHANGE_VERIFICATION_PREFIX = "SmsPasswordChangeVerification_";

    /**
     * 修改密码短信验证间隔前缀
     */
    public static final String SMS_PASSWORD_CHANGE_INTERVALS_PREFIX = "SmsPasswordChangeIntervals_";

    /**
     * 短信验证次数，命名空间
     */
    public static final String SMS_VERIFICATION_COUNT = "SmsVerificationCount";

    /**
     * 首页商品id
     */
    public static final String HOME_DATA = "HomeData";

    /**
     * 童模订单列表
     */
    public static final String TONG_MO_ORDER = "TongMoOrder";

}
