package com.example.surviveuni.social;

import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

class Social implements Observer {
    private SocialActivity sa;
    private boolean gameWon = false;
    private int correctAnswer = generate_expect();
    int remainingGuess;
    private int expect;
    private boolean unexpectedInput = false;
    private String feedBack;
    private boolean triggered = false;

    String checkAnswer(String answer){
        String feedback;
        if(triggered){
            feedback = feedBack;
            gameWon = true;
        }
        else {
            try {
                int number = Integer.parseInt(answer);

                if (number > 5 || number < 1) {
                    feedback = "You are not here to be friend with me!";
                    sa.setUnexpectedInput(true);
                    unexpectedInput = true;
                } else if (remainingGuess == 1) {
                    if (number == correctAnswer) {
                        feedback = "Correct! Let's be friend!";
                    } else {
                        feedback = "Sorry! Run out of playing times:( Maybe next time.";
                    }
                } else {
                    if (number == correctAnswer) {
                        gameWon = true;
                        feedback = "Correct! Let's be friend!";
                    } else if (number > correctAnswer) {
                        feedback = "It's too high, try another time.";
                    } else {
                        feedback = "It's too low, try another time.";
                    }
                }
            } catch (NumberFormatException e) {
                sa.setFailedMessage();
                sa.setUnexpectedInput(true);
                unexpectedInput = true;
                feedback = "You are not here to be friend with me!";
            }
        }
        checkGameOver(feedback,remainingGuess == 1, unexpectedInput);
        return feedback;
    }

    private int generate_expect() {
        Random r = new Random();
        expect = r.nextInt(5) + 1; // generate a random number ranging from 1 to 5
        return expect;
    }

    void passSocialActivity(SocialActivity sa){this.sa = sa;}

    private void checkGameOver(String feedback, boolean limitStatus, boolean unExpectInput){
        if(gameWon || limitStatus || unExpectInput){
            sa.checkGameOver(feedback);
        }
    }

    void setRemainingGuess(String level){
        switch (level) {
            case "HARD":
                remainingGuess = 1;
                break;
            case "NORMAL":
                remainingGuess = 2;
                break;
            case "EASY":
                remainingGuess = 3;
                break;
        }
    }

    int getRemainingGuess(){return remainingGuess;}

    @Override
    public void update(Observable observable, Object o) {

        feedBack = "Correct! Let's be friend!";
        triggered = true;
    }
}