package com.zjh.notice.service.impl;

import com.zjh.notice.kit.Constant;
import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.kit.ResultCodeEnum;
import com.zjh.notice.kit.Utils;
import com.zjh.notice.mapper.UserMapper;
import com.zjh.notice.model.Notice;
import com.zjh.notice.model.RUserUnit;
import com.zjh.notice.model.Unit;
import com.zjh.notice.model.User;
import com.zjh.notice.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zongjunhao
 */
@Service
public class IndexServiceImpl implements IndexService {
    private final static Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
    private final PlatformTransactionManager transactionManager;
    private final UserMapper userMapper;

    public IndexServiceImpl(PlatformTransactionManager transactionManager, UserMapper userMapper) {
        this.transactionManager = transactionManager;
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

    @Override
    public ResponseData getUser(String userId) {
        ResponseData response = new ResponseData();
        try {
            User user = userMapper.getUser(userId);
            if (user == null) {
                response.setResult(ResultCodeEnum.DB_FIND_FAILURE);
            } else {
                response.setData(user);
                response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("get user error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData getRole(long userId) {
        ResponseData response = new ResponseData();
        try {
            List<RUserUnit> rUserUnit = userMapper.findChargeUnits(userId);
            if (rUserUnit.isEmpty()) {
                response.setResult(ResultCodeEnum.DB_FIND_FAILURE);
            } else {
                response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("get role error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData updateUserInfo(String field, String value, String userId) {
        ResponseData response = new ResponseData();
        try {
            int result = userMapper.updateUserInfo(field, value, userId);
            if (result == 0) {
                response.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
            } else {
                response.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("update user info error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData getUsersInCharge(String userId) {
        ResponseData response = new ResponseData();
        try {
            List<User> userList = userMapper.getUsersInCharge(userId);
            if (userList.isEmpty()) {
                response.setResult(ResultCodeEnum.DB_FIND_FAILURE);
            } else {
                response.setData(userList);
                response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("get user in charge error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData createUnit(String unitName, String unitDescribe, String unitHolderId, String[] noticeReleaserIds, String[] userIds) {
        // 创建事务
        TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        ResponseData response = new ResponseData();
        try {
            Unit unit = new Unit(unitName, unitDescribe);
            userMapper.createUnit(unit);
            long unitId = unit.getUnitId();
            userMapper.addUserUnit(unitHolderId, unitId, "1");
            for (String noticeReleaserId:noticeReleaserIds){
                userMapper.addUserUnit(noticeReleaserId, unitId, "2");
            }
            for (String userId: userIds){
                userMapper.addUserUnit(userId, unitId, "3");
            }
            response.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
        } catch (Exception e) {
            // 异常回滚
            transactionManager.rollback(txStatus);
            logger.error("create unit error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        // 提交事务
        transactionManager.commit(txStatus);
        return response;
    }
}
