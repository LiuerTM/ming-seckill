package ind.liuer.seckill.vo;

import ind.liuer.seckill.validate.Mobile;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Mingの
 * @date 2022/6/12 4:43
 */
@Data
@NoArgsConstructor
public class LoginVo {

    @Mobile
    @Length(min = 11, max = 11, message = "手机号码不合法")
    private String mobile;

    @NotNull(message = "密码不能为空")
    private String password;
}
