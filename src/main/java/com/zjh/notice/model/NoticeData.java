package com.zjh.notice.model;


import java.util.List;

/**
 * 通知数据，包含通知的标签信息
 *
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class NoticeData {
    private Notice notice;
    private List<String> labels;
    private RNoticeUser rNoticeUser;
    private long isFinished;

    public NoticeData(Notice notice, List<String> labels, RNoticeUser rNoticeUser) {
        this.notice = notice;
        this.labels = labels;
        this.rNoticeUser = rNoticeUser;
        if (rNoticeUser == null) {
            this.isFinished = 0;
        } else {
            this.isFinished = rNoticeUser.getRIsFinished();
        }
    }

    public NoticeData() {
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public long getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(long isFinished) {
        this.isFinished = isFinished;
    }

    public RNoticeUser getrNoticeUser() {
        return rNoticeUser;
    }

    public void setrNoticeUser(RNoticeUser rNoticeUser) {
        this.rNoticeUser = rNoticeUser;
    }

    @Override
    public String toString() {
        return "NoticeData{" +
                "notice=" + notice +
                ", labels=" + labels +
                ", rNoticeUser=" + rNoticeUser +
                ", isFinished=" + isFinished +
                '}';
    }
}
