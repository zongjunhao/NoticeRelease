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
    private List<File> files;
    private long isFinished;

    public NoticeData(Notice notice, List<String> labels, List<File> files, RNoticeUser rNoticeUser) {
        this.notice = notice;
        this.labels = labels;
        this.files = files;
        if (rNoticeUser == null) {
            this.isFinished = 0;
        } else {
            this.isFinished = rNoticeUser.getRIsFinished();
        }
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

    public long getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(long isFinished) {
        this.isFinished = isFinished;
    }

    @Override
    public String toString() {
        return "NoticeData{" +
                "notice=" + notice.toString() +
                ", labels=" + labels +
                ", files=" + files.toString() +
                ", isFinished=" + isFinished +
                '}';
    }
}
