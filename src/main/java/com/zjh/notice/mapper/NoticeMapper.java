package com.zjh.notice.mapper;

import com.zjh.notice.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zongjunhao
 */
@Mapper
public interface NoticeMapper {

    /**
     * 查看此用户所有通知
     *
     * @param userId 用户ID
     * @param level  重要级别
     * @return 通知列表
     */
    List<Notice> findNoticesByUser(String userId, int level);

    /**
     * 查看已完成通知
     *
     * @param userId 用户ID
     * @param level  重要级别
     * @return 通知列表
     */
    List<Notice> findFinishedNotices(String userId, int level);

    /**
     * 查看未完成通知
     *
     * @param userId 用户ID
     * @param level  重要级别
     * @return 通知列表
     */
    List<Notice> findUnfinishedNotices(String userId, int level);

    /**
     * 查看过期通知
     *
     * @param userId 用户ID
     * @param level  重要级别
     * @return 通知列表
     */
    List<Notice> findExpiredNotices(String userId, int level);

    /**
     * 按通知ID查找通知
     *
     * @param noticeId 通知ID
     * @return 通知信息
     */
    @Select("select * from notice where notice_id = #{noticeId} ")
    Notice getNotice(String noticeId);

    /**
     * 查看通知对应的标签
     *
     * @param noticeId 通知ID
     * @return 标签列表
     */
    @Select("select label_name from label where label_id in (select r_label_id from r_notice_label where r_notice_id = #{noticeId} )")
    List<String> findLabelsByNotice(long noticeId);

    /**
     * 查看通知对应文件
     *
     * @param noticeId 通知ID
     * @return 文件列表
     */
    @Select("select * from file where file_notice_id = #{noticeId} ")
    List<File> findFilesByNotice(long noticeId);

    /**
     * 查找通知用户对应记录
     *
     * @param noticeId 通知ID
     * @param userId   用户ID
     * @return 通知用户记录
     */
    @Select("select * from r_notice_user where r_notice_id = #{noticeId} and r_user_id = #{userId}")
    RNoticeUser findNoticeUser(long noticeId, String userId);

    /**
     * 向通知用户关系表中添加阅读记录
     *
     * @param noticeId 通知ID
     * @param userId   用户ID
     * @return 是否添加成功
     */
    @Insert("insert into r_notice_user(r_notice_id, r_user_id, r_is_readed, r_readtime) values (#{noticeId}, #{userId}, 1, now())")
    int addReadRecord(String noticeId, String userId);

    /**
     * 获取所有标签
     *
     * @return 标签列表
     */
    @Select("select * from label")
    List<Label> getLabels();

    /**
     * 获取单位名称
     *
     * @param unitId 通知ID
     * @return 单位名称
     */
    @Select("select unit_name from unit where unit_id = #{unitId} ")
    String getUnitName(long unitId);

    /**
     * 获取当前用户具有通知发布权限的单位
     *
     * @param userId 用户ID
     * @return 单位列表
     */
    @Select("select * from unit where unit_id in (select r_unit_id from r_user_unit where r_user_id = #{userId}  and r_role <> 3)")
    List<Unit> getUnits(String userId);
}
