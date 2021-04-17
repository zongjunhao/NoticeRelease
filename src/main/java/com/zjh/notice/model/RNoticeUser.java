package com.zjh.notice.model;

/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class RNoticeUser {

  private long rNoticeUserId;
  private long rNoticeId;
  private long rUserId;
  private long rIsReaded;
  private java.sql.Timestamp rReadtime;
  private long rIsFinished;
  private java.sql.Timestamp rFinishtime;
  private java.sql.Timestamp rAddtime;
  private java.sql.Timestamp rUpdatetime;


  public long getRNoticeUserId() {
    return rNoticeUserId;
  }

  public void setRNoticeUserId(long rNoticeUserId) {
    this.rNoticeUserId = rNoticeUserId;
  }


  public long getRNoticeId() {
    return rNoticeId;
  }

  public void setRNoticeId(long rNoticeId) {
    this.rNoticeId = rNoticeId;
  }


  public long getRUserId() {
    return rUserId;
  }

  public void setRUserId(long rUserId) {
    this.rUserId = rUserId;
  }


  public long getRIsReaded() {
    return rIsReaded;
  }

  public void setRIsReaded(long rIsReaded) {
    this.rIsReaded = rIsReaded;
  }


  public java.sql.Timestamp getRReadtime() {
    return rReadtime;
  }

  public void setRReadtime(java.sql.Timestamp rReadtime) {
    this.rReadtime = rReadtime;
  }


  public long getRIsFinished() {
    return rIsFinished;
  }

  public void setRIsFinished(long rIsFinished) {
    this.rIsFinished = rIsFinished;
  }


  public java.sql.Timestamp getRFinishtime() {
    return rFinishtime;
  }

  public void setRFinishtime(java.sql.Timestamp rFinishtime) {
    this.rFinishtime = rFinishtime;
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


  @Override
  public String toString() {
    return "RNoticeUser{" +
            "rNoticeUserId=" + rNoticeUserId +
            ", rNoticeId=" + rNoticeId +
            ", rUserId=" + rUserId +
            ", rIsReaded=" + rIsReaded +
            ", rReadtime=" + rReadtime +
            ", rIsFinished=" + rIsFinished +
            ", rFinishtime=" + rFinishtime +
            ", rAddtime=" + rAddtime +
            ", rUpdatetime=" + rUpdatetime +
            '}';
  }
}
