package com.zjh.notice.controller;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.kit.ResultCodeEnum;
import com.zjh.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public ResponseData getNotices(String userId, int level, int isFinished) {
        ResponseData response;
        response = noticeService.getNoticesByConditions(userId, level, isFinished);
        return response;
    }

    @RequestMapping("readNotice")
    public ResponseData readNotice(String userId, String noticeId) {
        ResponseData response;
        response = noticeService.readNotice(userId, noticeId);
        return response;
    }

    @RequestMapping("finishNotice")
    public ResponseData finishNotice(String userId, String noticeId) {
        ResponseData response;
        response = noticeService.finishNotice(userId, noticeId);
        return response;
    }

    @RequestMapping("getUnits")
    public ResponseData getUnits(String userId) {
        ResponseData response;
        response = noticeService.getUnits(userId);
        return response;
    }

    @RequestMapping("getLabels")
    public ResponseData getLabels() {
        ResponseData response;
        response = noticeService.getLabels();
        return response;
    }

    @RequestMapping("addNotice")
    public ResponseData addNotice(long unitId, String title, String content, long level, String endtime, String labels, String fileIds) {
        ResponseData response;
        response = noticeService.addNotice(unitId, title, content, level, endtime, labels.split(","), fileIds.split(","));
        return response;
    }

    @RequestMapping("uploadFile")
    public ResponseData handleFormUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        ResponseData response = new ResponseData();
        String originalFileName = file.getOriginalFilename();
        String dirPath = "E:\\upload\\";
        logger.info(dirPath);
        File filePath = new File(dirPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        // 修改文件名，防止文件名重复
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = sdf.format(new Date()) + "_" + originalFileName;
        logger.info(newFileName);

        try {
            file.transferTo(new File(dirPath + newFileName));
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(ResultCodeEnum.FILE_UPLOAD_FAILURE);
            return response;
        }
        response = noticeService.addFile(originalFileName, "/upload/" + newFileName);
        return response;
    }

    @RequestMapping("addLabel")
    public ResponseData addLabel(String labelName){
        ResponseData response;
        response = noticeService.addLabel(labelName);
        return response;
    }
}
