package com.zjh.notice.service;

import com.zjh.notice.kit.ResponseData;

/**
 * @author zongjunhao
 */
public interface NoticeService {
    /**
     * 按条件查看通知
     *
     * @param userId     用户ID
     * @param isFinished 是否完成
     * @param level      待办级别
     * @return 待办事项列表
     */
    ResponseData getNotices(String userId, int isFinished, int level);
}
