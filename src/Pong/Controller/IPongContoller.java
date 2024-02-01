package Pong.Controller;


import Pong.Model.Player;
public interface  IPongContoller {
    void gameStart();

    void updateBallPosition();

    void getPositionUP(Player p);
    void getPositionDOWN(Player p);

    void nextFrame();

    void collision();
    ClientServerThread getThread();
    void  userInput(char c);

}