package com.example.surviveuni.study;

import androidx.appcompat.app.AppCompatActivity;


import com.example.surviveuni.data.User;
import com.example.surviveuni.data.GameState;
import com.example.surviveuni.gameCentre.UserManager;


public class StudyGame extends AppCompatActivity {

    /**
     * levels
     */
    private final String[] LEVELS = {"EASY", "NORMAL", "HARD"};

    private GameState gameState;

    private User user;

    private UserManager userManager;

    private StudyGameActivity sga = new StudyGameActivity();

    StudyGame(GameState gameState){
        this.gameState = gameState;
        this.userManager = UserManager.getInstance(this);
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

        System.out.println("LEVEL: " + level);
        System.out.println("deadline: " + deadline);

        System.out.println("sec: " + sec);
        if (sec == deadline) {

            sga.setUpResult(false);
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
        UserManager.getInstance(this).SaveToFile(); // Save to file so no need to save again when sign out
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