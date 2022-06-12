package ind.liuer.seckill.exception.biz;

/**
 * 用户登录失败异常类
 *
 * @author Mingの
 * @date 2022/6/12 6:10
 */
public class LoginFailureException extends RuntimeException {

    private static final long serialVersionUID = 2075989127391561497L;

    public LoginFailureException() {
        super("用户不存在或密码错误");
    }

    protected LoginFailureException(String message) {
        super(message);
    }
}
