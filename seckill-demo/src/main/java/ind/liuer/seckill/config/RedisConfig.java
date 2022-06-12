package ind.liuer.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 *
 * @author Mingの
 * @date 2022/6/12 17:09
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // string类型key序列化
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // string类型value序列化
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        // hash类型key序列化
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // hash类型value序列化
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        // 注入连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
