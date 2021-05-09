package com.zjh.notice.service;

import com.zjh.notice.kit.ResponseData;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

/**
 * @author zongjunhao
 */
public interface IndexService {
    /**
     * 用户登录
     * @param account   学工号
     * @param password  密码
     * @return  登录状态信息
     */
    ResponseData login(String account, String password);

    /**
     * 根据ID查询用户
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    ResponseData getUser(String userId);

    /**
     * 根据字段更新用户信息
     *
     * @param field  字段
     * @param value  值
     * @param userId 用户ID
     * @return 是否更新成功
     */
    ResponseData updateUserInfo(String field, String value, String userId);
}
