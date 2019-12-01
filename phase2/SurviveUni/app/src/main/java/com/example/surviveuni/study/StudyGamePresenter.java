package com.example.surviveuni.study;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.data.User;
import com.example.surviveuni.gameCentre.UserManager;

public class StudyGamePresenter{
    /**
     * levels
     */
    private final String[] LEVELS = {"EASY", "NORMAL", "HARD"};

    private GameState gameState;

    private User user;

    private UserManager userManager;

    private StudyGameActivity sga = new StudyGameActivity();

    private StudyGameView studyGameView;

    public static boolean changed = false;

    StudyGamePresenter(GameState gameState, StudyGameView studyGameView){
        this.gameState = gameState;
        this.userManager = UserManager.getInstance(sga);
        this.studyGameView = studyGameView;
    }

    int convertTime(long time, String level) {
        int sec = (int) ((time % 3600000 % 60000) / 1000);
        int deadline;

        if (level.equals(LEVELS[0]))
            deadline = 6;
        else if (level.equals(LEVELS[1]))
            deadline = 5;
        else
            deadline = 4;
        if (sec == deadline) {

            studyGameView.setUpResult(false);
        }

        return sec - 3;
    }

    boolean checkExit() {
        if (gameState.checkGameover() == 1) {
            return true;
        } else {
            gameState.updateDay();
            return false;
        }
    }

    void saveScore() {
        userManager.getUsers().get(user.getUsername()).updateScore(gameState.getGPA() + gameState.getHappiness() + gameState.getSpirit());
        UserManager.getInstance(sga).SaveToFile(); // Save to file so no need to save again when sign out
        studyGameView.setScoreSaveMessage();
        changed = true;
    }

    public void setUpResult(boolean isSuccess) {
        gameState.changeHappiness(-5);
        gameState.changeSpirit(-5);
        if(isSuccess){gameState.changeGPA(5);}
        else{gameState.changeGPA(-5);}

    }

    void passActivity(StudyGameActivity sga){this.sga = sga;}

    void passUser(User user){this.user = user;}

    void passUserManager(UserManager userManager){this.userManager = userManager;}
}
