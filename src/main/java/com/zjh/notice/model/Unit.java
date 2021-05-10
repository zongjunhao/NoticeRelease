package com.zjh.notice.model;

/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class Unit {

  private long unitId;
  private String unitName;
  private String unitDescribe;
  private java.sql.Timestamp unitAddtime;
  private java.sql.Timestamp unitUpdatetime;

  public Unit(String unitName, String unitDescribe) {
    this.unitName = unitName;
    this.unitDescribe = unitDescribe;
  }

  public Unit() {
  }

  public long getUnitId() {
    return unitId;
  }

  public void setUnitId(long unitId) {
    this.unitId = unitId;
  }


  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }


  public String getUnitDescribe() {
    return unitDescribe;
  }

  public void setUnitDescribe(String unitDescribe) {
    this.unitDescribe = unitDescribe;
  }


  public java.sql.Timestamp getUnitAddtime() {
    return unitAddtime;
  }

  public void setUnitAddtime(java.sql.Timestamp unitAddtime) {
    this.unitAddtime = unitAddtime;
  }


  public java.sql.Timestamp getUnitUpdatetime() {
    return unitUpdatetime;
  }

  public void setUnitUpdatetime(java.sql.Timestamp unitUpdatetime) {
    this.unitUpdatetime = unitUpdatetime;
  }

}
