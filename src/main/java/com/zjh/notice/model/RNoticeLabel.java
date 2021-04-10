package com.zjh.notice.model;

/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class RNoticeLabel {

  private long rNoticeLabelId;
  private long rNoticeId;
  private long rLabelId;
  private java.sql.Timestamp rAddtime;
  private java.sql.Timestamp rUpdatetime;


  public long getRNoticeLabelId() {
    return rNoticeLabelId;
  }

  public void setRNoticeLabelId(long rNoticeLabelId) {
    this.rNoticeLabelId = rNoticeLabelId;
  }


  public long getRNoticeId() {
    return rNoticeId;
  }

  public void setRNoticeId(long rNoticeId) {
    this.rNoticeId = rNoticeId;
  }


  public long getRLabelId() {
    return rLabelId;
  }

  public void setRLabelId(long rLabelId) {
    this.rLabelId = rLabelId;
  }


  public java.sql.Timestamp getRAddtime() {
    return rAddtime;
  }

  public void setRAddtime(java.sql.Timestamp rAddtime) {
    this.rAddtime = rAddtime;
  }


  public java.sql.Timestamp getRUpdatetime() {
    return rUpdatetime;
  }

  public void setRUpdatetime(java.sql.Timestamp rUpdatetime) {
    this.rUpdatetime = rUpdatetime;
  }

}
