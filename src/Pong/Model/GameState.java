package Pong.Model;

import java.io.Serializable;

public enum GameState implements Serializable {// les differents etats du jeu
    START,
    PLAYING,
    GAME_OVER
};