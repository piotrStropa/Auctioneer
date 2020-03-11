package com.alledrogo.models.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "userID")
    private int userID;
    @Column(name = "login")
    private String login;
    @Column(name = "passwordMD5")
    private String password;
    @Column(name = "userType")
    private String userType;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    public int getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    @Column(name = "warningCount")
    private int warningCount;

    public User(){

    }

    public User(String login, String password, String name, String userType, String surname, int warningCount){
        setLogin(login);
        setPassword(password);
        setName(name);
        setSurname(surname);
        setUserType(userType);
        setWarningCount(warningCount);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
