package com.example.hoang.bookmovietickets.Model;

/**
 * Created by hoang on 12/8/2015.
 */
public class SessionModel {

    private int islogin;
    private int id;

    public SessionModel(){}
    public SessionModel(int islogin, int id){
        this.id = id;
        this.islogin = islogin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIslogin() {
        return islogin;
    }

    public void setIslogin(int islogin) {
        this.islogin = islogin;
    }

    public int getId() {
        return id;
    }
}
