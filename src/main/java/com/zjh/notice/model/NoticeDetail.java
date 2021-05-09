package com.zjh.notice.model;

import java.util.List;

/**
 * @author zongjunhao
 */
public class NoticeDetail {
    private Notice notice;
    private List<String> labels;
    private List<File> files;
    private String unitName;
    private RNoticeUser rNoticeUser;
    private long isFinished;

    public NoticeDetail(Notice notice, List<String> labels, List<File> files, String unitName, RNoticeUser rNoticeUser) {
        this.notice = notice;
        this.labels = labels;
        this.files = files;
        this.unitName = unitName;
        this.rNoticeUser = rNoticeUser;
        if (rNoticeUser == null) {
            this.isFinished = 0;
        } else {
            this.isFinished = rNoticeUser.getRIsFinished();
        }
    }

    public NoticeDetail() {
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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public RNoticeUser getrNoticeUser() {
        return rNoticeUser;
    }

    public void setrNoticeUser(RNoticeUser rNoticeUser) {
        this.rNoticeUser = rNoticeUser;
    }

    public long getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(long isFinished) {
        this.isFinished = isFinished;
    }

    @Override
    public String toString() {
        return "NoticeDetail{" +
                "notice=" + notice +
                ", labels=" + labels +
                ", files=" + files +
                ", unitName='" + unitName + '\'' +
                ", rNoticeUser=" + rNoticeUser +
                ", isFinished=" + isFinished +
                '}';
    }
}
