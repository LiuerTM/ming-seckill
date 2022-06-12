package ind.liuer.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Mingの
 * @date 2022/6/12 2:11
 */
@SpringBootApplication
@MapperScan({"ind.liuer.seckill.dao"})
public class SeckillDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillDemoApplication.class, args);
    }
}
