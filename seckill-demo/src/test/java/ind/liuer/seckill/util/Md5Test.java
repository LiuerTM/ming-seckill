package ind.liuer.seckill.util;

import org.junit.Test;

/**
 * @author Mingの
 * @date 2022/6/12 6:29
 */
public class Md5Test {

    @Test
    public void test00() {
        String frontSalt = Md5Util.generateRandom(Md5Util.SALT_LENGTH);
        System.out.println("frontSalt = " + frontSalt); // GPvZ6t
        String frontPassword = Md5Util.md5("123456", frontSalt, Boolean.TRUE);
        System.out.println("frontPassword = " + frontPassword); // ef764ccd96554623de8f63f98ce9f156
        String salt = Md5Util.generateRandom(Md5Util.SALT_LENGTH);
        System.out.println("salt = " + salt); // i30u77
        String password = Md5Util.md5(frontPassword, salt);
        System.out.println("password = " + password); // 2fa25894a6dc97076e25fc6ee1a51543
    }
}
