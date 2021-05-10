package com.zjh.notice.controller;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.kit.ResultCodeEnum;
import com.zjh.notice.model.User;
import com.zjh.notice.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zongjunhao
 */
@RestController
@RequestMapping("index")
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @RequestMapping("login")
    public ResponseData login(String account, String password) {
        ResponseData response = new ResponseData();
        try {
            response = indexService.login(account, password);
        } catch (Exception e) {
            logger.error("登录异常", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        if (response.getCode().equals(ResultCodeEnum.LOGIN_SUCCESS.getCode())) {
            User user = (User) response.getData();
            logger.info("login success account:" + account + " userID:" + user.getUserId());
        }
        return response;
    }

    @RequestMapping("getUser")
    public ResponseData getUser(String userId){
        ResponseData response;
        response = indexService.getUser(userId);
        return response;
    }

    @RequestMapping("updateUserInfo")
    public ResponseData updateUserInfo(String field, String value, String userId){
        ResponseData response;
        response = indexService.updateUserInfo(field, value, userId);
        return response;
    }

    @RequestMapping("getUsersInCharge")
    public ResponseData getUsersInCharge(String userId){
        ResponseData response;
        response = indexService.getUsersInCharge(userId);
        return response;
    }

    @RequestMapping("createUnit")
    public ResponseData createUnit(String unitName, String unitDescribe, String unitHolderId, String noticeReleaserIds, String userIds){
        ResponseData response;
        response = indexService.createUnit(unitName, unitDescribe, unitHolderId, noticeReleaserIds.split(","), userIds.split(","));
        return response;
    }
}
