package com.zjh.notice.model;

/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class RUserUnit {

  private long rUserUnitId;
  private long rUserId;
  private long rUnitId;
  private java.sql.Timestamp rAddtime;
  private java.sql.Timestamp rUpdatetime;


  public long getRUserUnitId() {
    return rUserUnitId;
  }

  public void setRUserUnitId(long rUserUnitId) {
    this.rUserUnitId = rUserUnitId;
  }


  public long getRUserId() {
    return rUserId;
  }

  public void setRUserId(long rUserId) {
    this.rUserId = rUserId;
  }


  public long getRUnitId() {
    return rUnitId;
  }

  public void setRUnitId(long rUnitId) {
    this.rUnitId = rUnitId;
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
    return "RUserUnit{" +
            "rUserUnitId=" + rUserUnitId +
            ", rUserId=" + rUserId +
            ", rUnitId=" + rUnitId +
            ", rAddtime=" + rAddtime +
            ", rUpdatetime=" + rUpdatetime +
            '}';
  }
}
