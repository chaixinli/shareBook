package com.chai.share.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * Created by chaixinli on 2017/10/20.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 0;

    private String id;  //用户Id

    private String userName;    //用户名

    private String userPwd;     //用户密码

    private String email;   //用户邮箱

    private Date birthday;  //用户生日

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
