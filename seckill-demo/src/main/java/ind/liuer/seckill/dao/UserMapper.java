package ind.liuer.seckill.dao;

import ind.liuer.seckill.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Mingの
 * @date 2022/6/12 5:37
 */
@Mapper
public interface UserMapper {

    /**
     * 根据主键（手机号）查询用户密码和盐
     *
     * @param id 主键（手机号码）
     * @return 用户信息
     */
    User selectUserById(String id);
}
