package com.example.hoang.bookmovietickets.Model;

/**
 * Created by hoang on 11/16/2015.
 */
public class UserModel {

    private String username;
    private String password;
    private String name;
    private String tel;
    private String cardID;
    private String email;
    private int id;

    public UserModel(){}

    public UserModel(String name, String pass){
        this.username = name;
        this.password = pass;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCardID() {
        return cardID;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
