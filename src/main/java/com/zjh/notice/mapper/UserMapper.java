package com.zjh.notice.mapper;

import com.zjh.notice.model.RUserUnit;
import com.zjh.notice.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author zongjunhao
 */
@Mapper
public interface UserMapper {
    /**
     * 查找用户
     *
     * @param account  账号
     * @param password 密码
     * @return 用户信息
     */
    @Select("select * from user where user_account = #{account} and user_password = #{password}")
    User findByAccount(String account, String password);

    /**
     * 查询用户是否具有发布权限
     *
     * @param userId 用户ID
     * @return 具有通知发布权限的记录
     */
    @Select("select * from r_user_unit where r_user_id = #{userId} and r_role <> 3")
    List<RUserUnit> findUserByRole(long userId);

    /**
     * 根据ID查询用户
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Select("select * from user where user_id = #{userId} ")
    User getUser(String userId);

    /**
     * 根据字段更新用户信息
     *
     * @param field  字段
     * @param value  值
     * @param userId 用户ID
     * @return 是否更新成功
     */
    @Options(statementType = StatementType.STATEMENT)
    @Update("update user set ${field} = '${value}' where user_id = ${userId}")
    int updateUserInfo(String field, String value, String userId);
}
