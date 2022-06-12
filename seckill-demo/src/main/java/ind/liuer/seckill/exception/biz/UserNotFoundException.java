package ind.liuer.seckill.exception.biz;

/**
 * 用户不存在异常类
 *
 * @author Mingの
 * @date 2022/6/12 6:10
 */
public class UserNotFoundException extends LoginFailureException {

    private static final long serialVersionUID = -4417802699281007208L;

    public UserNotFoundException() {
        super("用户不存在");
    }
}
