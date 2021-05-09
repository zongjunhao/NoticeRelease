package com.zjh.notice.model;

/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class File {

    private long fileId;
    private long fileNoticeId;
    private String fileName;
    private String filePath;
    private java.sql.Timestamp fileAddtime;
    private java.sql.Timestamp fileUpdatetime;

    public File(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public File() {
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }


    public long getFileNoticeId() {
        return fileNoticeId;
    }

    public void setFileNoticeId(long fileNoticeId) {
        this.fileNoticeId = fileNoticeId;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public java.sql.Timestamp getFileAddtime() {
        return fileAddtime;
    }

    public void setFileAddtime(java.sql.Timestamp fileAddtime) {
        this.fileAddtime = fileAddtime;
    }


    public java.sql.Timestamp getFileUpdatetime() {
        return fileUpdatetime;
    }

    public void setFileUpdatetime(java.sql.Timestamp fileUpdatetime) {
        this.fileUpdatetime = fileUpdatetime;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", fileNoticeId=" + fileNoticeId +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileAddtime=" + fileAddtime +
                ", fileUpdatetime=" + fileUpdatetime +
                '}';
    }
}
