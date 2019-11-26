package com.example.surviveuni.gameCentre;

import android.content.Context;

import com.example.surviveuni.data.User;

public class ScoreBoard {

    private int maxScore;
    private String[] onBoard;
    private User user;
    private User toBoard; // next user to put on ScoreBoard
    private UserManager userManager;


    public ScoreBoard(Context context){
        this.maxScore = -1;
        this.onBoard = new String[5];
        this.user = null;
        this.toBoard = null;
        this.userManager = new UserManager(context);
    }

    public User setRanking(int spaceused){
        maxScore = -1;
        for(String key : userManager.getUsers().keySet()) {
            System.out.println(key);
            if (!contains(onBoard, key)) {
                user = userManager.getUsers().get(key);
                System.out.println(user.getScore());
                if (user.getScore() > maxScore) {
                    maxScore = user.getScore();
                    toBoard = user;
                }
            }
        }

        if(maxScore != -1)
        {
            onBoard[spaceused] = toBoard.getUsername();
            return toBoard;
        }
        return null;

    }

    private boolean contains(String[] a, String b)
    {
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == null){return false;}
            else if(a[i].equals(b))
            {
                return true;
            }
        }
        return false;
    }
}

