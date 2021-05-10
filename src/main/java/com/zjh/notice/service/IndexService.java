package com.zjh.notice.service;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.model.User;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author zongjunhao
 */
public interface IndexService {
    /**
     * 用户登录
     *
     * @param account  学工号
     * @param password 密码
     * @return 登录状态信息
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

    /**
     * 获取所掌管单位人员信息
     *
     * @param userId 用户ID
     * @return 人员信息
     */
    ResponseData getUsersInCharge(String userId);

    /**
     * 创建单位
     *
     * @param unitName          单位名称
     * @param unitDescribe      单位简介
     * @param unitHolderId      单位所有者
     * @param noticeReleaserIds 通知发布者
     * @param userIds           用户
     * @return 是否添加成功
     */
    ResponseData createUnit(String unitName, String unitDescribe, String unitHolderId, String[] noticeReleaserIds, String[] userIds);
}
