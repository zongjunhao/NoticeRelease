package com.zjh.notice.controller;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @RequestMapping("getUnits")
    public ResponseData getUnits(String userId){
        ResponseData response;
        response = noticeService.getUnits(userId);
        return response;
    }

    @RequestMapping("getLabels")
    public ResponseData getLabels(){
        ResponseData response;
        response = noticeService.getLabels();
        return response;
    }

    @RequestMapping("uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "/upload/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return "上传失败！";
    }
}
