package ind.liuer.seckill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author Mingの
 * @date 2022/6/12 2:14
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping({"", "/"})
    public String test() {
        return "Welcome to seckill-demo test";
    }
}
