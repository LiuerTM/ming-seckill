package ind.liuer.seckill.exception.biz;

/**
 * 用户密码错误异常类
 *
 * @author Mingの
 * @date 2022/6/12 6:10
 */
public class PasswordErrorException extends LoginFailureException {

    private static final long serialVersionUID = 6637214252921307734L;

    public PasswordErrorException() {
        super("密码错误");
    }
}
