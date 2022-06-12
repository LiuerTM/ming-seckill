package ind.liuer.seckill.aspect;

import ind.liuer.seckill.config.SystemConfigProperties;
import ind.liuer.seckill.domain.User;
import ind.liuer.seckill.exception.biz.UserNotLoginException;
import ind.liuer.seckill.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mingの
 * @date 2022/6/12 17:27
 */
@Aspect
@Order(1)
@Component
public class AuthorizedAspect {

    private final SystemConfigProperties systemConfig;

    private final UserService userService;

    @Autowired
    public AuthorizedAspect(SystemConfigProperties systemConfig, UserService userService) {
        this.systemConfig = systemConfig;
        this.userService = userService;
    }

    @Pointcut(value = "execution(* ind.liuer.seckill.controller.*.* (..))")
    public void express() {
    }

    @Before(value = "express()")
    public void before(JoinPoint joinPoint) {
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = typeName + "." + joinPoint.getSignature().getName();
        if (!systemConfig.getLoginIgnoreMethods().contains(methodName)) {
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (sra == null) {
                throw new UserNotLoginException();
            }
            HttpServletRequest request = sra.getRequest();
            HttpServletResponse response = sra.getResponse();
            String token = request.getHeader(systemConfig.getLoginCookieName());
            User user = userService.getUserByCookieName(token, request, response);
            if (user == null) {
                throw new UserNotLoginException();
            }
        }
    }
}
