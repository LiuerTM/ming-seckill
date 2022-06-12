package ind.liuer.seckill.vo;

import lombok.Getter;

/**
 * 返回结果类型枚举
 *
 * @author Mingの
 * @date 2022/6/12 4:02
 */
@Getter
public enum ResultEnum {

    // ==================================================
    /**
     * 请求成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 请求失败
     */
    FAILURE(400, "操作失败"),

    /**
     * 参数不合法
     */
    ARG_FAILURE(400, "参数不合法"),

    /**
     * 登录成功
     */
    LOGIN_SUCCESS(200, "登录成功"),
    /**
     * 登录失败
     */
    LOGIN_FAILURE(400, "用户不存在或密码错误"),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(444, "用户未登录"),

    /**
     * 服务异常
     */
    SERVER_ERROR(500, "服务异常");


    // ==================================================

    private final int code;
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
