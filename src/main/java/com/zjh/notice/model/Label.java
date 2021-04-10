package com.zjh.notice.model;

/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class Label {

  private long labelId;
  private String labelName;
  private java.sql.Timestamp labelAddtime;
  private java.sql.Timestamp labelUpdatetime;


  public long getLabelId() {
    return labelId;
  }

  public void setLabelId(long labelId) {
    this.labelId = labelId;
  }


  public String getLabelName() {
    return labelName;
  }

  public void setLabelName(String labelName) {
    this.labelName = labelName;
  }


  public java.sql.Timestamp getLabelAddtime() {
    return labelAddtime;
  }

  public void setLabelAddtime(java.sql.Timestamp labelAddtime) {
    this.labelAddtime = labelAddtime;
  }


  public java.sql.Timestamp getLabelUpdatetime() {
    return labelUpdatetime;
  }

  public void setLabelUpdatetime(java.sql.Timestamp labelUpdatetime) {
    this.labelUpdatetime = labelUpdatetime;
  }

}
