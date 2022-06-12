package ind.liuer.seckill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统自定义属性
 *
 * @author Mingの
 * @date 2022/6/12 8:10
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system.config")
public class SystemConfigProperties {

    /**
     * 登录失败是否展示详细错误，默认false
     */
    private boolean loginErrorDetails = false;

    /**
     * 登录cookie名称，默认UserToken
     */
    private String loginCookieName = "UserToken";

    /**
     * 登录cookie过期时间，默认1800s
     */
    private int loginCookieMaxAge = 1800;

    /**
     * 忽略登录可调用方法
     */
    private List<String> loginIgnoreMethods = new ArrayList<>();
}
