package ind.liuer.seckill.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 手机号码校验注解
 *
 * @author Mingの
 * @date 2022/6/12 5:18
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobileValidator.class)
public @interface Mobile {

    boolean required() default true;

    String message() default "手机号不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
