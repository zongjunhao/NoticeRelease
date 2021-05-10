package com.zjh.notice.mapper;

import com.zjh.notice.model.RUserUnit;
import com.zjh.notice.model.Unit;
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

    /**
     * 获取所掌管单位人员信息
     *
     * @param userId 用户ID
     * @return 人员信息
     */
    @Select("select * from user where user_id in (select r_user_id from r_user_unit where r_unit_id in(select r_unit_id from r_user_unit where r_role = 1 and r_user_id = #{userId} ))")
    List<User> getUsersInCharge(String userId);

    /**
     * 创建单位
     *
     * @param unit 单位信息
     * @return 是否创建成功
     */
    @Options(useGeneratedKeys = true, keyProperty = "unitId", keyColumn = "unitId")
    @Insert("insert into unit(unit_name, unit_describe) values (#{unitName}, #{unitDescribe})")
    int createUnit(Unit unit);

    /**
     * 添加单位用户信息
     * @param unitId 单位ID
     * @param userId 用户ID
     * @param role 用户角色
     * @return 是否添加成功
     */
    @Insert("insert into r_user_unit(r_user_id, r_unit_id, r_role) values (#{userId}, #{unitId}, #{role})")
    int addUserUnit(String userId, long unitId, String role);
}
