package ind.liuer.seckill.util;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author Mingの
 * @date 2022/6/12 15:37
 */
public class UuidUtil {

    /**
     * 随机生成UUID（不带'-'）
     *
     * @return 随机字符串
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
