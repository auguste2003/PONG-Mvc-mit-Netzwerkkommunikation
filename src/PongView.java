/*import Pong.Controller.IPongContoller;
import Pong.Controller.Controller;
import Pong.Model.GameState;
import Pong.Model.PongData;
import Pong.View.IPongView;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
public class PongView extends PApplet implements IPongView {



    private PongData data =new PongData();// initialise les donnees; // la donnee
    final int PADDLE_WIDTH = 5;
    private IPongContoller controller =new Controller(data);// controlleur
    final int BALL_SIZE = 25; // epaisseur de la balle
    final int PLAYER_SIZE = 50;// epaisseur du joueur



    private PImage startScreen; // image de debut
    private PImage gameOverScreen; // image a la fin
    private PImage player1Image; // image du premier joueur
    private PImage player2Image;// image du deuxieme joueur
    private PImage ballImage;// image de
    // / Geschwindigkeit mit der sich die Animation oder das Spiel läuft

    public PongView( PongData data) {
this.data=data;
    }

    public void setController(PongController controller) {
        this.controller = controller;
    }

    public IPongContoller getController() {
        return controller;
    }

    public void settings() {
        size(900, 600); // Use aspect ratio 3:2
        pixelDensity(1);
    }

    @Override
    public void setup(){


        data.state =  GameState.START; // etat de depart
        startScreen = loadImage("images/StartScreen.jpg");
        gameOverScreen = loadImage("images/GameOverScreen.jpg");
        player1Image = loadImage("images/025.png");
        player2Image = loadImage("images/133.png");
        ballImage = loadImage("images/100.png");
    }
    public void draw(){

        controller.nextFrame();
    }
public float  getFramRate(){ // Gibt die FrameRate zurück
        return frameRate;
}
public float getHeight(){
        return height;
}
    public void drawTitleScreen() {
        background(0);

        imageMode(CORNER);
        image(startScreen, 0, 0, width, height);
    }

    public void drawTitleGameState() {

        background(255);
        imageMode(CENTER);// paramètres d'une image sont interprétés comme image(image, x, y, width, height).

        var p1 = data.player1;
        image(player1Image,
                (float) (p1.position.x - p1.getSize() / 2.0 - PADDLE_WIDTH),// position en x
                p1.position.y, p1.getSize(), p1.getSize());
        fill(0);
        rect(p1.position.x - PADDLE_WIDTH, (float) (p1.position.y - p1.getSize() / 2.0),
                PADDLE_WIDTH, p1.getSize());
        // Draw player 2 and paddle
        var p2 = data.player2;
        image(player2Image,
                (float) (p2.position.x + p2.getSize() / 2.0 + PADDLE_WIDTH),
                p2.position.y, p2.getSize(), p2.getSize());
        rect(p2.position.x, (float) (p2.position.y - p2.getSize() / 2.0),
                PADDLE_WIDTH, p2.getSize());
        // Draw ball
        translate(data.ball.position.x, data.ball.position.y);
        rotate(data.ball.getRotationAngle());
        image(ballImage, 0, 0, data.ball.getSize(), data.ball.getSize());
        controller.updateBallPosition();
        controller.collision();
    }

    public void drawTitleGO(){

                imageMode(CORNER);
                image(gameOverScreen, 0, 0, width, height);

        }




    @Override
    public void keyPressed(KeyEvent event) {
        switch (data.state) {
            case START, GAME_OVER -> {
               // data.state = GameState.START;
                if(event.getKey() == ' ') {// lange Taste
                    data.state = GameState.PLAYING;



                  controller.gameStart(); // appelle la methode gameStart() qui va appeller la methode startGame() dans le model

                }
            }
            case PLAYING -> {
                // Player 1: w = up and s = down
                var p1 = data.player1;
                if(event.getKey() == 'w')// up
                    controller.getPositionUP(p1);
                if(event.getKey() == 's')
                    controller.getPositionDOWN(p1);// down

                // Player 2: Up-key = up and Down-key = down
                var p2 = data.player2;
                if(event.getKeyCode() == UP)
                    controller.getPositionUP(p2);
                if(event.getKeyCode() == DOWN)
                    controller.getPositionDOWN(p2);// down
            }
            default -> throw new IllegalStateException("Unexpected value: " + data.state);
        }
    }
    }

 */

