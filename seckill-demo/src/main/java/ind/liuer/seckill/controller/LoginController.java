package ind.liuer.seckill.controller;

import ind.liuer.seckill.domain.User;
import ind.liuer.seckill.service.UserService;
import ind.liuer.seckill.vo.LoginVo;
import ind.liuer.seckill.vo.ResultEnum;
import ind.liuer.seckill.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录相关接口
 *
 * @author Mingの
 * @date 2022/6/12 5:35
 */
@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录接口
     *
     * @param loginVo 用户登录信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResultVo<?> login(@RequestBody @Valid LoginVo loginVo,
                             HttpServletRequest request, HttpServletResponse response) {
        User user = userService.login(loginVo, request, response);
        return new ResultVo<User>(ResultEnum.LOGIN_SUCCESS).setData(user);
    }
}
