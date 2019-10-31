package com.example.surviveuni;

import java.io.Serializable;

class User implements Serializable {
    private String username;
    private String password;

    User(String username, String password) {
        if(checkReasonable(username,password)){
            this.password = password;
            this.username = username;
        }
    }

    static boolean checkReasonable(String username,String password){
        if (password.equals("")) {
            return false;
        } else if (username.equals("")) {
            return false;
        } else if (password.length() <= 6 || username.length() <= 6) {
            return false;
        }
        return true;

    }

    public String getUsername(){
        return username;
    }

    boolean checkPassowrd(String pw){
        return this.password.equals(pw);
    }
}
