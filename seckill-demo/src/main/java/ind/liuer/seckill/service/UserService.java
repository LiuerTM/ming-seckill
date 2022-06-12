package ind.liuer.seckill.service;

import ind.liuer.seckill.domain.User;
import ind.liuer.seckill.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mingの
 * @date 2022/6/12 5:38
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param loginVo  登录信息
     * @param request  request
     * @param response response
     * @return 用户信息
     */
    User login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据cookie值获取用户信息
     *
     * @param token    cookie值
     * @param request  request
     * @param response response
     * @return 用户信息
     */
    User getUserByCookieName(String token, HttpServletRequest request, HttpServletResponse response);
}
