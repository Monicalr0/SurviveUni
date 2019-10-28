package com.example.surviveuni;

import java.io.Serializable;

class User implements Serializable {
    private String username;
    private String password;

    void User(String username, String password) {
        if(checkReasonable(username,password)){
            this.password = password;
            this.username = username;
        }
    }

    private boolean checkReasonable(String username,String password){
        if (password.equals("")) {
            throw new RuntimeException("Password Cannot Be Empty");
        } else if (username.equals("")) {
            throw new RuntimeException("Username Cannot Be Empty");
        } else if (password.length() <= 6 || username.length() <= 6) {
            throw new RuntimeException("The Length Of Username And Password must be greater than 6");
        }
        return true;

    }

    public String getUsername(){
        return username;
    }

    boolean checkPassword(String pw){
        return this.password.equals(pw);
    }
}
