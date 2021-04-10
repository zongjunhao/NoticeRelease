package com.zjh.notice.model;


/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class Notice {

  private long noticeId;
  private String noticeTitle;
  private String noticeContent;
  private long noticeUnitId;
  private long noticeLevel;
  private java.sql.Timestamp noticeEndtime;
  private java.sql.Timestamp noticeAddtime;
  private java.sql.Timestamp noticeUpdatetime;


  public long getNoticeId() {
    return noticeId;
  }

  public void setNoticeId(long noticeId) {
    this.noticeId = noticeId;
  }


  public String getNoticeTitle() {
    return noticeTitle;
  }

  public void setNoticeTitle(String noticeTitle) {
    this.noticeTitle = noticeTitle;
  }


  public String getNoticeContent() {
    return noticeContent;
  }

  public void setNoticeContent(String noticeContent) {
    this.noticeContent = noticeContent;
  }


  public long getNoticeUnitId() {
    return noticeUnitId;
  }

  public void setNoticeUnitId(long noticeUnitId) {
    this.noticeUnitId = noticeUnitId;
  }


  public long getNoticeLevel() {
    return noticeLevel;
  }

  public void setNoticeLevel(long noticeLevel) {
    this.noticeLevel = noticeLevel;
  }


  public java.sql.Timestamp getNoticeEndtime() {
    return noticeEndtime;
  }

  public void setNoticeEndtime(java.sql.Timestamp noticeEndtime) {
    this.noticeEndtime = noticeEndtime;
  }


  public java.sql.Timestamp getNoticeAddtime() {
    return noticeAddtime;
  }

  public void setNoticeAddtime(java.sql.Timestamp noticeAddtime) {
    this.noticeAddtime = noticeAddtime;
  }


  public java.sql.Timestamp getNoticeUpdatetime() {
    return noticeUpdatetime;
  }

  public void setNoticeUpdatetime(java.sql.Timestamp noticeUpdatetime) {
    this.noticeUpdatetime = noticeUpdatetime;
  }

  @Override
  public String toString() {
    return "Notice{" +
            "noticeId=" + noticeId +
            ", noticeTitle='" + noticeTitle + '\'' +
            ", noticeContent='" + noticeContent + '\'' +
            ", noticeUnitId=" + noticeUnitId +
            ", noticeLevel=" + noticeLevel +
            ", noticeEndtime=" + noticeEndtime +
            ", noticeAddtime=" + noticeAddtime +
            ", noticeUpdatetime=" + noticeUpdatetime +
            '}';
  }
}
