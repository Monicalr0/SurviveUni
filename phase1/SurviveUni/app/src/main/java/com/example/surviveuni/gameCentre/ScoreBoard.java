package com.example.surviveuni.gameCentre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.surviveuni.R;
import com.example.surviveuni.data.User;

public class ScoreBoard extends AppCompatActivity {

    private int maxScore;
    private String[] onBoard = new String[5];
    private User user;
    private User toBoard; // next user to put on ScoreBoard

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        setRanking();
    }

    private void setRanking(){
        TextView name1 = findViewById(R.id.BoardName1);
        TextView name2 = findViewById(R.id.BoardName2);
        TextView name3 = findViewById(R.id.BoardName3);
        TextView name4 = findViewById(R.id.BoardName4);
        TextView name5 = findViewById(R.id.BoardName5);
        TextView score1 = findViewById(R.id.BoardScore1);
        TextView score2 = findViewById(R.id.BoardScore2);
        TextView score3 = findViewById(R.id.BoardScore3);
        TextView score4 = findViewById(R.id.BoardScore4);
        TextView score5 = findViewById(R.id.BoardScore5);
        TextView[] texts = new TextView[] {name1,name2,name3,name4,name5,score1,score2,score3,score4,score5};

        int spaceused = 0; // number of users already on board
        for(int i = 0; i <5; i++)
        {
            maxScore = -1;
            for(String key : UserManager.users.keySet())
            {
                if(!contains(onBoard,key))
                {
                    user = UserManager.users.get(key);
                    if(user.getScore() > maxScore)
                    {
                        maxScore = user.getScore();
                        toBoard = user;
                    }
                }
            }

            if(maxScore != -1)
            {
                texts[spaceused].setText(toBoard.getNickname());
                texts[spaceused+5].setText(toBoard.getScore());
                onBoard[spaceused] = toBoard.getUsername();
                spaceused += 1;
            }
        }
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
