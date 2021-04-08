package com.zjh.notice.service;

import com.zjh.notice.kit.ResponseData;

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

}
