package com.example.surviveuni.gameCentre.ScoreBoardSystem;

import com.example.surviveuni.data.User;

public interface ScoreBoardView {

    void showAnonymous(int index);

    void showNickName(int index, User us);

    void showTotalScore(int index, User us);
}
