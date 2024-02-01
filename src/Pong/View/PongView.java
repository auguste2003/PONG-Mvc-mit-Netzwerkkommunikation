package Pong.View;


import Pong.Controller.IPongContoller;

import Pong.Model.Ball;
import Pong.Model.GameState;
import Pong.Model.Player;

import Pong.Model.PongData;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.KeyEvent;
public class PongView extends PApplet implements IPongView {
    private PongData data ;// initialise les donnees; // la donnee
    final int PADDLE_WIDTH = 5;
    private IPongContoller controller ;// controlleur
    final int BALL_SIZE = 25; // epaisseur de la balle
    final int PLAYER_SIZE = 50;// epaisseur du joueur



    private PImage startScreen; // image de debut
    private PImage gameOverScreen; // image a la fin
    private PImage player1Image; // image du premier joueur
    private PImage player2Image;// image du deuxieme joueur
    private PImage ballImage;// image de
    // / Geschwindigkeit mit der sich die Animation oder das Spiel läuft

    public PongView( ) {

    }

    public void setController(IPongContoller controller) {
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


        GameState state =  GameState.START; // etat de depart
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
    public float getHeight(){ // Gibt die Höhe von dem Fenster des Spielsfeldes zurück
        return height;
    }
    public void drawTitleScreen() { // Das Bield bei dem Biginn von dem Spiel zeichnen
        background(0);

        imageMode(CORNER);
        image(startScreen, 0, 0, width, height);
    }

    public void drawTitleGameState(Player p1, Player p2, Ball ball, int PADDLE_WIDTH) { // dessiner le jeu

        background(255);
        imageMode(CENTER);// paramètres d'une image sont interprétés comme image(image, x, y, width, height).


        image(player1Image,
                (float) (p1.position.x - p1.getSize() / 2.0 - PADDLE_WIDTH),// position en x
                p1.position.y, p1.getSize(), p1.getSize());
        fill(0);
        rect(p1.position.x - PADDLE_WIDTH, (float) (p1.position.y - p1.getSize() / 2.0),
                PADDLE_WIDTH, p1.getSize());
        // Draw player 2 and paddle

        image(player2Image,
                (float) (p2.position.x + p2.getSize() / 2.0 + PADDLE_WIDTH),
                p2.position.y, p2.getSize(), p2.getSize());
        rect(p2.position.x, (float) (p2.position.y - p2.getSize() / 2.0),
                PADDLE_WIDTH, p2.getSize());
        // Draw ball
        translate(ball.position.x, ball.position.y);
        rotate(ball.getRotationAngle());
        image(ballImage, 0, 0, ball.getSize(), ball.getSize());


    }

    public void drawTitleGO(){ // dessiner l'imaage a la fin du jeu

        imageMode(CORNER);
        image(gameOverScreen, 0, 0, width, height);

    }

    @Override
    public void keyPressed(KeyEvent event) {

        char keyc;
        if ((key ==CODED) &&(keyCode ==UP)){
            keyc ='u';
            System.out.println("up");
        }else if((key ==CODED) &&(keyCode ==DOWN)){
            keyc ='d';
            System.out.println("down");
        }else keyc = key;
        controller.userInput(keyc);

    }
}