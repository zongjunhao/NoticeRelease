package com.zjh.notice.mapper;

import com.zjh.notice.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author zongjunhao
 */
@Mapper
public interface UserMapper {
    /**
     * 查找用户
     * @param account   账号
     * @param password  密码
     * @return 用户信息
     */
    @Select("select * from user where user_account = #{account} and user_password = #{password}")
    User findByAccount(String account, String password);

    // @Insert("insert into user")
    // int addUser(User user);
}
