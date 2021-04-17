package com.zjh.notice.controller;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongjunhao
 */
@RestController
@RequestMapping("notice")
public class NoticeController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping("getNotices")
    public ResponseData getNotices(String userId, int isFinished, int level) {
        ResponseData response;
        response = noticeService.getNoticesByConditions(userId, isFinished, level);
        return response;
    }

    @RequestMapping("readNotice")
    public ResponseData readNotice(String userId, String noticeId){
        ResponseData response;
        response = noticeService.readNotice(userId, noticeId);
        return response;
    }
}
