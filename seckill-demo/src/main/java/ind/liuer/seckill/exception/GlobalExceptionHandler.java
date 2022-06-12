package ind.liuer.seckill.exception;

import ind.liuer.seckill.config.SystemConfigProperties;
import ind.liuer.seckill.exception.biz.LoginFailureException;
import ind.liuer.seckill.exception.biz.UserNotLoginException;
import ind.liuer.seckill.vo.ResultEnum;
import ind.liuer.seckill.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * 统一异常处理
 *
 * @author Mingの
 * @date 2022/6/12 6:11
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final SystemConfigProperties systemConfig;

    @Autowired
    public GlobalExceptionHandler(SystemConfigProperties systemConfig) {
        this.systemConfig = systemConfig;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResultVo<?> handler(MethodArgumentNotValidException e) {
        log.info("参数校验异常：{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        Optional<ObjectError> objectErrorOpt = bindingResult.getAllErrors().stream().findFirst();
        if (objectErrorOpt.isPresent()) {
            ObjectError objectError = objectErrorOpt.get();
            return ResultVo.result(ResultEnum.ARG_FAILURE).setMessage(objectError.getDefaultMessage());
        }
        return ResultVo.result(ResultEnum.ARG_FAILURE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {LoginFailureException.class})
    public ResultVo<?> handler(LoginFailureException e) {
        log.info("登录失败：{}", e.getMessage());
        if (systemConfig.isLoginErrorDetails()) {
            return ResultVo.result(ResultEnum.LOGIN_FAILURE).setMessage(e.getMessage());
        }
        return ResultVo.result(ResultEnum.LOGIN_FAILURE);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = {UserNotLoginException.class})
    public ResultVo<?> handler(UserNotLoginException e) {
        log.info(e.getMessage());
        return ResultVo.result(ResultEnum.USER_NOT_LOGIN);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class})
    public ResultVo<?> handler(RuntimeException e) {
        log.error("服务器异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultVo.result(ResultEnum.SERVER_ERROR);
    }
}
