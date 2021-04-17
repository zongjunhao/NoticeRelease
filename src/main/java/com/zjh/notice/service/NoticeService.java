package com.zjh.notice.service;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.model.Notice;
import com.zjh.notice.model.NoticeData;

import java.util.List;

/**
 * @author zongjunhao
 */
public interface NoticeService {
    /**
     * 按条件查找通知
     *
     * @param userId     用户ID
     * @param level      待办级别
     * @param isFinished 是否完成
     * @return 已完成通知列表
     */
    ResponseData getNoticesByConditions(String userId, int level, int isFinished);

    /**
     * 阅读通知
     *
     * @param userId   用户ID
     * @param noticeId 通知ID
     * @return 是否修改成功
     */
    ResponseData readNotice(String userId, String noticeId);

    /**
     * 完成通知
     *
     * @param userId   用户ID
     * @param noticeId 通知ID
     * @return 是否修改成功
     */
    ResponseData finishNotice(String userId, String noticeId);
}
