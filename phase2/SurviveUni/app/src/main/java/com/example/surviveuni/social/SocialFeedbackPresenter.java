package com.example.surviveuni.social;

import com.example.surviveuni.data.GameState;
import com.example.surviveuni.gameCentre.GameManager;

class SocialFeedbackPresenter implements SocialFeedbackView{
    private GameState gameState = GameManager.getGameState();

//    private ImageView iv;
    //private Social social = new Social();
    private Socialfeedback socialfb;
    private String setImage;

    @Override
    public  String getSetImage(){
        return setImage;
    }

    @Override
    public void checkFeedback(String feedback) {
        //gameState.addObserver(social);
        //gameState.socialNotify();
//        iv = findViewById(R.id.imageView1);
        if (feedback.equals("Correct! Let's be friend!")) {
//            iv.setImageResource(R.drawable.wow);
            gameState.changeGPA(-5);
            gameState.changeSpirit(-5);
            gameState.changeHappiness(10);
            setImage = "wow";
//            return ("Happiness:+10\nGPA:-5\nSpirit:-5");
        } else if (feedback.equals("Sorry! Run out of playing times:( Maybe next time.")) {
//            iv.setImageResource(R.drawable.sorry);
            gameState.changeGPA(-5);
            gameState.changeSpirit(-5);
            gameState.changeHappiness(-5);
            setImage = "sorry";
//            return ("Happiness:-5\nGPA:-5\nSpirit:-5");
        } else {
//            iv.setImageResource(R.drawable.angry);
            gameState.changeGPA(-5);
            gameState.changeSpirit(-10);
            gameState.changeHappiness(-10);
            setImage = "angry";
//            return ("Happiness:-10\nGPA:-5\nSpirit:-10");
        }
    }
//    void passSocialFeedback(Socialfeedback sf) {
//        this.socialfb = sf;
//    }
}
