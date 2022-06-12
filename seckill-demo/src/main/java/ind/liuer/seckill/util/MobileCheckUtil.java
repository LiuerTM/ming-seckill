package ind.liuer.seckill.util;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * 手机号码校验工具
 *
 * @author Mingの
 * @date 2022/6/12 5:07
 */
public class MobileCheckUtil {

    /**
     * 中国电信号码格式验证
     * 手机段：133,149,153,173,177,180,181,189,199,1349,1410,1700,1701,1702
     */
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|49|53|7[37]|8[019]|99)\\d{8}$)|(?:^(?:\\+86)?1349\\d{7}$)|(?:^(?:\\+86)?1410\\d{7}$)|(?:^(?:\\+86)?170[0-2]\\d{7}$)";

    /**
     * 中国联通号码格式验证
     * 手机段：130,131,132,145,146,155,156,166,171,175,176,185,186,1704,1707,1708,1709
     */
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[56]|5[56]|66|7[156]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[47-9]\\d{7}$)";

    /**
     * 中国移动号码格式验证
     * 手机段：134,135,136,137,138,139,147,148,150,151,152,157,158,159,178,182,183,184,187,188,195,198,1440,1703,1705,1706
     */
    private static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[78]|5[0-27-9]|78|8[2-478]|98|95)\\d{8}$)|(?:^(?:\\+86)?1440\\d{7}$)|(?:^(?:\\+86)?170[356]\\d{7}$)";

    /**
     * 中国大陆手机号码校验
     *
     * @param mobile 手机号码
     * @return 校验结果 ture | false
     */
    public static boolean checkMobile(String mobile) {
        if (StringUtils.hasText(mobile)) {
            return checkChinaMobile(mobile) || checkChinaUnicom(mobile) || checkChinaTelecom(mobile);
        }
        return false;
    }

    /**
     * 中国移动手机号码校验
     *
     * @param mobile 手机号码
     * @return 校验结果 ture | false
     */
    public static boolean checkChinaMobile(String mobile) {
        if (StringUtils.hasText(mobile)) {
            Pattern regexp = Pattern.compile(CHINA_MOBILE_PATTERN);
            return regexp.matcher(mobile).matches();
        }
        return false;
    }

    /**
     * 中国联通手机号码校验
     *
     * @param mobile 手机号码
     * @return 校验结果 ture | false
     */
    public static boolean checkChinaUnicom(String mobile) {
        if (StringUtils.hasText(mobile)) {
            Pattern regexp = Pattern.compile(CHINA_UNICOM_PATTERN);
            return regexp.matcher(mobile).matches();
        }
        return false;
    }

    /**
     * 中国电信手机号码校验
     *
     * @param mobile 手机号码
     * @return 校验结果 ture | false
     */
    public static boolean checkChinaTelecom(String mobile) {
        if (StringUtils.hasText(mobile)) {
            Pattern regexp = Pattern.compile(CHINA_TELECOM_PATTERN);
            return regexp.matcher(mobile).matches();
        }
        return false;
    }

    /**
     * 隐藏手机号中间四位
     *
     * @param mobile 手机号码
     * @return 脱敏后手机号码
     */
    public static String hideMiddleMobile(String mobile) {
        if (StringUtils.hasText(mobile)) {
            mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return mobile;
    }
}
