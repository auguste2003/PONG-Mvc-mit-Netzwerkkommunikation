package Pong.Model2;

import java.io.Serializable;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PongModel implements Serializable {
    final int PADDLE_WIDTH = 5;
    final int BALL_SIZE = 25; // epaisseur de la balle
    final int PLAYER_SIZE = 50;// epaisseur du joueur
    int width,height;
   public  PongData data ;// initialise les donnees

    public PongModel(int width,int height){

        this.height =height;
        this.width=width;
        data =new PongData(GameState.START,new Player(PLAYER_SIZE+PADDLE_WIDTH, (height-PLAYER_SIZE)/2.0 - PADDLE_WIDTH, PLAYER_SIZE),
        new Player(width-PLAYER_SIZE-PADDLE_WIDTH, (height-PLAYER_SIZE)/2.0 + PADDLE_WIDTH, PLAYER_SIZE),
         new Ball(width/2.0, height/2.0, BALL_SIZE)
        );
        //   startNewGame();
    }


    public void startNewGame() { // lancer un nouveau jeu
        data.player1 = new Player(PLAYER_SIZE+PADDLE_WIDTH, (height-PLAYER_SIZE)/2.0 - PADDLE_WIDTH, PLAYER_SIZE);
        data.player2 = new Player(width-PLAYER_SIZE-PADDLE_WIDTH, (height-PLAYER_SIZE)/2.0 + PADDLE_WIDTH, PLAYER_SIZE);
        data.ball = new Ball(width/2.0, height/2.0, BALL_SIZE);
        data.ball.randomizeAcceleration();
    }
    public  void UPDBallPosition(float rate ) { // actualiser la position de la balle      // private
        data.ball.updateBallPosition(1.0 /rate );
    }
    public void showPositionUP(Player p) {// Position von dem Player , wenn man nach unten drückt

        p.position.y = max((float) (p.getSize() / 2.0), p.position.y - 10); // Der Spiler kann sich quasi nach Oben bewegen

    }
    public void showPositionDOWN(Player p) { // Position von dem Player , wenn man nach oben drückt

        p.position.y = min((float) (height-p.getSize()/2.0), p.position.y + 10);// down
    }
    public  void handleCollisions() { // public
        // Reflect on y-axis on top and bottom wall
        if (data.ball.collidesY(0) || data.ball.collidesY(height)) {
            data.ball.acceleration.y *= -1;// si la balle frappe sur les bordures hautes et basses , la direction change
        }

        // Reflect on x-axis when paddle hits the ball
        if (data.player1.hits(data.ball) || data.player2.hits(data.ball)) {
            data.ball.acceleration.x *= -1;// Sie la barre de l'un des joueurs touche la balle
        }

        // Game over on hitting left or right wall
        if (data.ball.collidesX(0) || data.ball.collidesX(width)) {
            data.state = GameState.GAME_OVER;// si la coordonee x est superieure a width ou inferieure a 0 alors le jeu fini
        }

    }
    @Override
    public String toString() {
        return "Position X Von dem Spieler  1 : "+  data.player1.position.x+"\n"+
                "Position y von dem Spieler  1 : "+  data.player1.position.y+"\n"+
                "Position X von dem Spieler  2 : "+ data.player1.position.x+"\n"+
                "Position y von dem Spieler  2 : "+ data.player1.position.y +"\n"+
                "Die X Position von dem Ball ist "+data.ball.position.x +"\n"+
                "Die Y Position von dem Vall ist "+data.ball.position.y
                ;

    }


}