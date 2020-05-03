# SurviveUni

This is a course group project for CSC207 Fall 2019 at the University of 
Toronto. I helped develope part of the "social" part of the game and also some universal part over the three subgames. 
   
**General Information :**  

*Basic Setting*:  
SurviveUni is a game. Each time an user start the game, he/she will be 
 requested to sign up an account with a password, or sign in. Then the 
 user will get three main attributes named Happiness, GPA and Spirit, 
 each with value 50, also DayofSurvival will be set to 0 as a record for
 round. 
 
 *Game Detail*:  
 After the basic setting, the user can choose from three different
 games to play: Study, Sleep and Social, corresponding to the attributes
 GPA, Spirit and Happiness. Each time the user start a round of game, 
 the DayofSurvival will add 1. Playing each game will cost other two not
 corresponding attributes. 
 
 *Level*:
 The user can choose different levels to play in each game. Each level 
 corresponds to a different difficulty.
 
 *Win/Lose*:  
 If the user wins, he/she will get points added to the corresponding 
 attributes, else the corresponding attributes will also be deducted 
 with some points. 
 
 *Gameover*:  
 Once the user has one of the attributes deducted to 0, the game is over.
 All attributes will be reset to 50, the DayofSurvival will be reset to 0.
 
 **Game Introduction**:
 
 *Study*: 
 
 *Social*:  A socializing guess-the-number game. A number ranging from
 1 to 5 is randomly generated each time and user would guess the number.
 There are different remaining guesses according to the level that the 
 user chooses. If the user guesses the number correctly within the 
 remaining guesses, he/she wins. Else, the user loses. There is a hidden
 setting of the game, that is, if the user's Happiness is lower or equal
 to 10, something special would happen ;)
 
 *Sleep*:  A memory and fast-reaction game. A number of sheep wil jump
 around on your screen. However, some of these sheep are actually wolves
 with camouflage! They will secretly eat the sheep. Tapping on them will
 make them show themselves. After a certain amount of time, you will be
 asked how many sheep there are finally. Try to get the number right and
 have a good sleep.
