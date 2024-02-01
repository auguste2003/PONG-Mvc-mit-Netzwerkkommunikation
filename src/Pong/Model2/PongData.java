package Pong.Model2;

import java.io.Serializable;

public class PongData implements Serializable { // Led differentes donnes du jeu
    public GameState state;
    public Player player1;
    public Player player2;
    public Ball ball;

    public PongData(GameState state,Player player1,Player player2,Ball ball){
        this.state =state;
        this.player1=player1;
        this.player2 =player2;
        this.ball =ball;
    }
}