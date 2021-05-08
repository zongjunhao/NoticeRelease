package com.zjh.notice.mapper;

import com.zjh.notice.model.RUserUnit;
import com.zjh.notice.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 查询用户是否具有发布权限
     * @param userId 用户ID
     * @return 具有通知发布权限的记录
     */
    @Select("select * from r_user_unit where r_user_id = #{userId} and r_role <> 3")
    List<RUserUnit> findUserByRole(long userId);
}
