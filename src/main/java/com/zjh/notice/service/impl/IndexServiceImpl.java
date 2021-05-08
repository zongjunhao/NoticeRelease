package com.zjh.notice.service.impl;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.kit.ResultCodeEnum;
import com.zjh.notice.mapper.UserMapper;
import com.zjh.notice.model.RUserUnit;
import com.zjh.notice.model.User;
import com.zjh.notice.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zongjunhao
 */
@Service
public class IndexServiceImpl implements IndexService {
    private final static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
    private final UserMapper userMapper;

    public IndexServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResponseData login(String account, String password) {
        ResponseData response = new ResponseData();
        try {
            User user = userMapper.findByAccount(account, password);
            if (user != null) {
                List<RUserUnit> userList = userMapper.findUserByRole(user.getUserId());
                if (userList.isEmpty()) {
                    // 登陆成功，无通知发布权限
                    response.setData(user);
                    response.setResult(ResultCodeEnum.LOGIN_SUCCESS_WITH_NO_GRANT);
                } else {
                    // 登录成功，具有通知发布权限
                    response.setData(user);
                    response.setResult(ResultCodeEnum.LOGIN_SUCCESS);
                }
            } else {
                response.setResult(ResultCodeEnum.LOGIN_ERROR);
            }
        } catch (Exception e) {
            logger.error("login error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }
}
