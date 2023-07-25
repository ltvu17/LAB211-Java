package model;

import controller.status;

public class Users {
    protected String userName;
    private String passWord;
    protected String role;
    protected status status;
    public Users(){
        
    }
    public Users(String userName, String passWord, String role, status status) {
        this.userName = userName;
        this.passWord = passWord;
        this.role = role;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public status getStatus() {
        return status;
    }

    public void setStatus(status status) {
        this.status = status;
    }  
    
}
