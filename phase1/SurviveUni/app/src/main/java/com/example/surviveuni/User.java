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

    boolean checkReasonable(String username,String password){
        if (password.equals("")) {
            return false;
        } else if (username.equals("")) {
            return false;
        } else if (password.length() <= 6 || username.length() <= 6) {
            return false;
        }
        return true;

    }

    String getPassword() {return password;}

    public String getUsername(){
        return username;
    }

    boolean checkPassword(String pw){
        return this.password.equals(pw);
    }
}
