package com.zjh.notice.model;


/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class User {

  private long userId;
  private String userAccount;
  private String userPassword;
  private String userName;
  private String userGender;
  private String userPhone;
  private String userEmail;
  private java.sql.Timestamp userAddtime;
  private java.sql.Timestamp userUpdatetime;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserAccount() {
    return userAccount;
  }

  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserGender() {
    return userGender;
  }

  public void setUserGender(String userGender) {
    this.userGender = userGender;
  }


  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public java.sql.Timestamp getUserAddtime() {
    return userAddtime;
  }

  public void setUserAddtime(java.sql.Timestamp userAddtime) {
    this.userAddtime = userAddtime;
  }


  public java.sql.Timestamp getUserUpdatetime() {
    return userUpdatetime;
  }

  public void setUserUpdatetime(java.sql.Timestamp userUpdatetime) {
    this.userUpdatetime = userUpdatetime;
  }

}
