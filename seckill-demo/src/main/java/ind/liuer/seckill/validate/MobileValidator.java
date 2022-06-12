package ind.liuer.seckill.validate;

import ind.liuer.seckill.util.MobileCheckUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号码校验类
 *
 * @author Mingの
 * @date 2022/6/12 5:25
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext constraintValidatorContext) {
        return MobileCheckUtil.checkMobile(mobile);
    }
}
