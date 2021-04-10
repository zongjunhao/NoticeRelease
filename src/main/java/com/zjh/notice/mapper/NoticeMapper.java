package com.zjh.notice.mapper;

import com.zjh.notice.model.File;
import com.zjh.notice.model.Label;
import com.zjh.notice.model.Notice;
import com.zjh.notice.model.RNoticeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zongjunhao
 */
@Mapper
public interface NoticeMapper {

    /**
     * 按用户查看通知
     *
     * @param userId     用户ID
     * @return 通知列表
     */
    List<Notice> findNoticeByUser(String userId);

    /**
     * 查看通知对应的标签
     *
     * @param noticeId 通知ID
     * @return 标签列表
     */
    @Select("select label_name from label where label_id in (select r_label_id from r_notice_label where r_notice_id = #{noticeId} )")
    List<String> findLabelsByNotice(String noticeId);

    /**
     * 查看通知对应文件
     *
     * @param noticeId 通知ID
     * @return 文件列表
     */
    @Select("select * from file where file_notice_id = #{noticeId} ")
    List<File> findFilesByNotice(String noticeId);

    /**
     * 查找通知用户对应记录
     *
     * @param noticeId 通知ID
     * @param userId   用户ID
     * @return 通知用户记录
     */
    @Select("select * from r_notice_user where r_notice_id = #{noticeId} and r_user_id = #{userId}")
    RNoticeUser findNoticeUser(String noticeId, String userId);
}
