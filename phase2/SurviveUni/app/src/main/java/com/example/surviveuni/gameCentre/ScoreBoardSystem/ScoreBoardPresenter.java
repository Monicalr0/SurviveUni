package com.example.surviveuni.gameCentre.ScoreBoardSystem;

import android.content.Context;

import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.FeedbackActivity;
import com.example.surviveuni.gameCentre.UserManager;
import com.example.surviveuni.social.Socialfeedback;
import com.example.surviveuni.study.StudyGamePresenter;

class ScoreBoardPresenter {
    private int maxScore;
    public static User[] onBoard = new User[5];
    private User user; // user variable for loop
    private User toBoard; // next user to put on ScoreBoard
    private UserManager userManager;
    private ScoreBoardView scoreBoardView;
    private int spaceUsed;


    ScoreBoardPresenter(Context context, ScoreBoardView scoreBoardView) {
        this.maxScore = -1;
        this.toBoard = null;
        this.userManager = UserManager.getInstance(context);
        this.scoreBoardView = scoreBoardView;
    }

    void showRanking(){
        if(StudyGamePresenter.changed || Socialfeedback.changed || FeedbackActivity.changed){
            setRanking();
        }
        for(int i = 0 ; i < 5; i++){
            if(ScoreBoardPresenter.onBoard[i] != null){
                if (String.valueOf(ScoreBoardPresenter.onBoard[i].getNickname()).equals("")) {
                    scoreBoardView.showAnonymous(i);
                } else {
                    scoreBoardView.showNickName(i, ScoreBoardPresenter.onBoard[i]);
                }
                scoreBoardView.showTotalScore(i + 5, ScoreBoardPresenter.onBoard[i]);
            }
        }
        StudyGamePresenter.changed = false;
        Socialfeedback.changed = false;
        FeedbackActivity.changed = false;
    }
    void setRanking() {
        for(int i = 0; i < 5 ; i++)
        {
            onBoard[i] = null;
        }
        spaceUsed = 0;
        for (int i = 0; i < 5; i++) {
            toBoard = null;
            maxScore = -1;
            for (String key : userManager.getUsers().keySet()) {
                if (!contains(ScoreBoardPresenter.onBoard, key)) {
                    user = userManager.getUsers().get(key);
                    if (user.getScore() > maxScore) {
                        System.out.println(user.getScore());
                        maxScore = user.getScore();
                        toBoard = user;
                    }
                }
            }

            if (maxScore != -1) {
                ScoreBoardPresenter.onBoard[spaceUsed] = toBoard;
            }
            spaceUsed++;

        }
    }

    private boolean contains(User[] a, String b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == null) {
                return false;
            } else if (a[i].getUsername().equals(b)) {
                return true;
            }
        }
        return false;
    }
}
