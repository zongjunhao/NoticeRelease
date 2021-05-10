package com.zjh.notice.service;

import com.zjh.notice.kit.ResponseData;
import org.apache.ibatis.annotations.Insert;

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

    /**
     * 获取当前用户具有通知发布权限的单位
     *
     * @param userId 用户ID
     * @return 单位列表
     */
    ResponseData getUnits(String userId);

    /**
     * 获取所有标签
     *
     * @return 标签列表
     */
    ResponseData getLabels();

    /**
     * 添加通知
     *
     * @param unitId  单位ID
     * @param title   通知标题
     * @param content 通知内容
     * @param level   重要等级
     * @param endTime 截止时间
     * @param labels  标签ID
     * @param fileIds 附件ID
     * @return 是否添加成功
     */
    ResponseData addNotice(long unitId, String title, String content, long level, String endTime, String[] labels, String[] fileIds);

    /**
     * 添加附件
     *
     * @param fileName 附件名
     * @param filePath 附件路径
     * @return 是否添加成功
     */
    ResponseData addFile(String fileName, String filePath);

    /**
     * 添加标签
     * @param labelName 标签名
     * @return 是否添加成功
     */
    ResponseData addLabel(String labelName);
}
