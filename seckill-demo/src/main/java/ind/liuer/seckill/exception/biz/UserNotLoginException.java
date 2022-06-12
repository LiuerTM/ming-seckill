package ind.liuer.seckill.exception.biz;

/**
 * 用户未登录异常类
 *
 * @author Mingの
 * @date 2022/6/12 18:22
 */
public class UserNotLoginException extends RuntimeException {

    private static final long serialVersionUID = 6579849781168807751L;

    public UserNotLoginException() {
        super("用户未登录");
    }
}
