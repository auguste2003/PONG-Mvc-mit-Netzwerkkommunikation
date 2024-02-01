package Pong.View2;

import Pong.Model.Ball;
import Pong.Model.Player;

public interface IPongView {
    void drawTitleScreen();

    void drawTitleGameState(Player player1, Player player2, Ball ball, int PADDLE_WIDTH);

    void drawTitleGO();

    float  getFramRate();
    float getHeight();

}