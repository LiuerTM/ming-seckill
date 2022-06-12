package ind.liuer.seckill.util;

import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * MD5 工具类
 *
 * @author Mingの
 * @date 2022/6/12 3:23
 */
public class Md5Util {

    /**
     * 常用字符数组
     */
    public static final char[] COMMON_CHAR_ARRAY = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * 盐默认长度
     */
    public static final int SALT_LENGTH = 6;

    /**
     * MD5 加密
     *
     * @param source  原字符串
     * @param saltStr 盐
     * @param type    false 前面，true 后面
     * @return 加密后字符串
     */
    public static String md5(String source, String saltStr, boolean type) {
        Assert.hasLength(source, "加密字符串不能为空");
        Assert.hasLength(saltStr, "加密需要的盐不能为空");
        StringBuilder builder = new StringBuilder();
        // 原字符串在后面
        if (type) {
            builder.append(saltStr).append(source);
        } // 原字符串在前面
        else {
            builder.append(source).append(saltStr);
        }
        return DigestUtils.md5DigestAsHex(builder.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * MD5 加密
     *
     * @param source  原字符串
     * @param saltStr 盐
     * @return 加密后字符串
     */
    public static String md5(String source, String saltStr) {
        Assert.hasLength(source, "加密字符串不能为空");
        Assert.hasLength(saltStr, "加密需要的盐不能为空");
        StringBuilder builder = new StringBuilder();
        char[] saltArr = saltStr.toCharArray();
        // 原字符串嵌入盐串中间
        for (int i = 0; i < saltArr.length; i++) {
            if (i == saltArr.length / 2) {
                builder.append(source);
            }
            builder.append(saltArr[i]);
        }
        return DigestUtils.md5DigestAsHex(builder.toString().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 随机生成常用字符组成的字符串
     *
     * @param length 长度
     * @return 字符串
     */
    public static String generateRandom(int length) {
        Assert.state((length > 5 && length < 10), "随机生成的长度范围：5~10");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(COMMON_CHAR_ARRAY.length);
            builder.append(COMMON_CHAR_ARRAY[index]);
        }
        return builder.toString();
    }
}
