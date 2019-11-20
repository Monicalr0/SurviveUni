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

    private boolean checkReasonable(String username,String password){
        if (password.equals("")) {
            return false;
        } else if (username.equals("")) {
            return false;
        }
        else return (password.length() >= 7 && username.length() >= 7);

    }


    String getUsername(){
        return username;
    }

    boolean checkPassword(String pw){
        return this.password.equals(pw);
    }


}
