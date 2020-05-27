package com.muhameddhouibi.designthinking.Entity;

public class User {

    String UserId ;
    String UserName ;
    String UserEmail ;
    String Url ;
    Boolean Connection ;

    public User() {
    }

    public User(String userId, String userName, String userEmail, Boolean connection,String url) {
        UserId = userId;
        UserName = userName;
        UserEmail = userEmail;
        Connection = connection;
        Url = url ;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }



    public Boolean getConnection() {
        return Connection;
    }

    public void setConnection(Boolean connection) {
        Connection = connection;
    }
}
