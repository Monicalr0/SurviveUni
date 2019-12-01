package com.example.surviveuni.gameCentre.ScoreBoardSystem;

import android.content.Context;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.UserManager;

class ScoreBoardPresenter {
    private int maxScore;
    private String[] onBoard;
    private User user; // user variable for loop
    private User toBoard; // next user to put on ScoreBoard
    private UserManager userManager;
    private ScoreBoardView scoreBoardView;
    private int spaceUsed = 0;


    ScoreBoardPresenter(Context context, ScoreBoardView scoreBoardView) {
        this.maxScore = -1;
        this.onBoard = new String[5];
        this.toBoard = null;
        this.userManager = UserManager.getInstance(context);
        this.scoreBoardView = scoreBoardView;
    }

    void setRanking() {
        for (int i = 0; i < 5; i++) {
            toBoard = null;
            maxScore = -1;
            for (String key : userManager.getUsers().keySet()) {
                if (!contains(onBoard, key)) {
                    user = userManager.getUsers().get(key);
                    if (user.getScore() > maxScore) {
                        System.out.println(user.getScore());
                        maxScore = user.getScore();
                        toBoard = user;
                    }
                }
            }

            if (maxScore != -1) {
                onBoard[spaceUsed] = toBoard.getUsername();
            }
            if (toBoard != null) {
                if (String.valueOf(user.getNickname()).equals("")) {
                    scoreBoardView.showAnonymous(spaceUsed);
                } else {
                    scoreBoardView.showNickName(spaceUsed, toBoard);
                }
                scoreBoardView.showTotalScore(spaceUsed + 5, toBoard);
                System.out.println(user.getScore());
            }
            spaceUsed++;

        }
    }

    private boolean contains(String[] a, String b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null) {
                return false;
            } else if (a[i].equals(b)) {
                return true;
            }
        }
        return false;
    }
}
