package com.example.surviveuni;

import java.util.Random;

public class Social {
    int expect;
    int input;
    String appearence;

    public void take_input(){}
    public void generate_expect(){
        Random r = new Random();
        expect = r.nextInt(10)+1; // generate a random number ranging from 1 to 10
    }
    public void update(){}
    public void compare(){}
    public void helper_compare(){}
    public void draw(){}

}
