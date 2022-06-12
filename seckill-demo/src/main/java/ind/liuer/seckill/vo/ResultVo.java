package ind.liuer.seckill.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 接口统一返回结果模型
 *
 * @param <T> Object类型
 * @author Mingの
 * @date 2022/6/12 4:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ResultVo<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 返回结果类型枚举参数构造器
     *
     * @param resultEnum 返回类型枚举
     */
    public ResultVo(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    /**
     * 请求结果（无数据）
     *
     * @param resultEnum 返回结果类型枚举
     * @return 请求结果
     */
    public static ResultVo<?> result(ResultEnum resultEnum) {
        return new ResultVo<>(resultEnum);
    }

    /**
     * 请求成功（默认消息、无数据）
     *
     * @return 成功结果
     */
    public static ResultVo<?> success() {
        return result(ResultEnum.SUCCESS);
    }

    /**
     * 请求成功（自定义消息、无数据）
     *
     * @return 成功结果
     */
    public static ResultVo<?> success(String message) {
        return new ResultVo<>(ResultEnum.SUCCESS).setMessage(message);
    }

    /**
     * 请求成功（默认消息、带数据）
     */
    public void success(T data) {
        this.success(ResultEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 请求成功（自定义消息、带数据）
     */
    public void success(String message, T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = message;
        this.data = data;
    }

    /**
     * 请求失败（默认消息、无数据）
     *
     * @return 失败结果
     */
    public static ResultVo<?> failure() {
        return result(ResultEnum.FAILURE);
    }

    /**
     * 请求失败（自定义消息、无数据）
     *
     * @return 失败结果
     */
    public static ResultVo<?> failure(String message) {
        return new ResultVo<>(ResultEnum.FAILURE).setMessage(message);
    }

    /**
     * 请求失败（默认消息、带数据）
     *
     * @param data 数据
     */
    public void failure(T data) {
        this.failure(ResultEnum.FAILURE.getMessage(), data);
    }

    /**
     * 请求失败（自定义消息、带数据）
     *
     * @param message 消息
     * @param data    数据
     */
    public void failure(String message, T data) {
        this.code = ResultEnum.FAILURE.getCode();
        this.message = message;
        this.data = data;
    }
}
