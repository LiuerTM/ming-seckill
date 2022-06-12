package ind.liuer.seckill.service.impl;

import ind.liuer.seckill.config.SystemConfigProperties;
import ind.liuer.seckill.dao.UserMapper;
import ind.liuer.seckill.domain.User;
import ind.liuer.seckill.exception.biz.PasswordErrorException;
import ind.liuer.seckill.exception.biz.UserNotFoundException;
import ind.liuer.seckill.service.UserService;
import ind.liuer.seckill.util.CookieUtil;
import ind.liuer.seckill.util.Md5Util;
import ind.liuer.seckill.util.UuidUtil;
import ind.liuer.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author Mingの
 * @date 2022/6/12 5:38
 */
@Service
public class UserServiceImpl implements UserService {

    public static final String TOKEN_PREFIX = "user:";

    private final UserMapper userMapper;

    private final SystemConfigProperties systemConfig;

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, SystemConfigProperties systemConfig,
                           RedisTemplate<String, Object> redisTemplate) {
        this.userMapper = userMapper;
        this.systemConfig = systemConfig;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public User login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper.selectUserById(loginVo.getMobile());
        if (user == null) {
            throw new UserNotFoundException();
        }
        String password = Md5Util.md5(loginVo.getPassword(), user.getSalt());
        if (!user.getPassword().equals(password)) {
            throw new PasswordErrorException();
        }
        user.setPassword(null).setSalt(null);
        String token = UuidUtil.uuid();
        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, user,
                systemConfig.getLoginCookieMaxAge(), TimeUnit.SECONDS);
        CookieUtil.setCookie(request, response,
                systemConfig.getLoginCookieName(), token, systemConfig.getLoginCookieMaxAge(), true);
        return user;
    }

    @Override
    public User getUserByCookieName(String token, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        if (user != null) {
            redisTemplate.opsForValue().set(TOKEN_PREFIX + token, user,
                    systemConfig.getLoginCookieMaxAge(), TimeUnit.SECONDS);
            CookieUtil.setCookie(request, response,
                    systemConfig.getLoginCookieName(), token, systemConfig.getLoginCookieMaxAge(), true);
        }
        return user;
    }
}
