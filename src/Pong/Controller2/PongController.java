package Pong.Controller2;

import Pong.Model.GameState;
import Pong.Model.Player;
import Pong.Model.PongData;
import Pong.Model.PongModel;

import Pong.View.IPongView;
public class PongController implements IPongContoller {
    private PongModel model  ; // le model

    private PongData data; // la donnee
    //  data.state =  GameState.START; // etat de depart
    private IPongView view ; // la vue
private PongController controller;
public ClientServerThread thread ;


    private static Pong.Controller2.ClientServerThread sv ;
public int height;

    public PongController( int height) {
     //  this.data.state= GameState.START;
       this.height =height;
        this.thread =new ClientServerThread(model,controller);
    }
    public static  PongController newServer(int port , int size , PongModel model){
        var ps = new PongController(size);
        ps.thread =ClientServerThread.newServer(port,model,ps);
        ps.thread.isServer =true;


        return  ps;
    }
    public static  PongController newClient(String ip,int port , int size , PongModel model){
        var pc = new PongController(size);
        pc.thread =ClientServerThread.newClient(ip,port,model,pc);

        pc.thread.isServer =false;
        return  pc;
    }
    public void collision(){
        model.handleCollisions();
    }
    public PongModel getModel() {
        return model;
    }

    public void setModel(PongModel model) {
        this.model = model;
    }

    public void setView( IPongView view)  {
        this.view =view;
    }// changer de view

    public void gameStart(){ // lancer le jeu en creeant des joueurs
        model.startNewGame();
    }
    public  void updateBallPosition() { // actualiser la position de la balle      // private

        model.UPDBallPosition( view.getFramRate());
    }

    public void getPositionUP(Player p) {

        model.showPositionUP( p);

    }
    public void getPositionDOWN(Player p) {

        model.showPositionDOWN( p);


    }

    public ClientServerThread getThread() {
        return thread;
    }

    public void nextFrame() { // evaluer les diferents etats de jeu
        switch (model.data.state){
            case START -> {
                view.drawTitleScreen();// Dessiner l'Etat premier du code
                updateBallPosition();
                collision();
                thread.send(model.data);
            }
            case PLAYING -> {
                view.drawTitleGameState(model.data.player1,model.data.player2,model.data.ball, 5);// Dessiner le deroulement du jeu

            if (thread.getIsServer()){
                thread.send(model.data);
                System.out.println("Data gesendet");
                }

            }
            case GAME_OVER -> {
                view.drawTitleGO();// l'image finale du joueur
                thread.send(model.data);
            }
            default -> throw new IllegalStateException("Unexpected value: " + data.state);
        }

    }
    public void userInput(char c){
        switch (model.data.state) {
            case START, GAME_OVER -> {
                // data.state = GameState.START;
                if(c == ' ' && thread.getIsServer() && thread.isConnected()) {// lange Taste
                    model.data.state = GameState.PLAYING;
                    System.out.println("Starte ");
                    model.startNewGame();  // appelle la methode gameStart() qui va appeller la methode startGame() dans le model
                } else if (c== ' ' && !thread.getIsServer() && thread.isConnected()) {
                    thread.send(c);
                  }
            }
            case PLAYING -> {
                // Player 1: w = up and s = down

                if (thread.getIsServer() && ( c=='w' || c== 's')) {
                    if (c == 'w') {
                        model.showPositionUP(model.data.player1);
                    } else if (c == 's') {
                        model.showPositionDOWN(model.data.player1);
                    }
                }

                // Player 2: Up-key = up and Down-key = down
                if (thread.isServer && (c== 'u' || c == 'd')){
                    if (c == 'u') {
                        model.showPositionUP(model.data.player2);
                    }else if (c == 'd'){
                        model.showPositionDOWN(model.data.player2);// down
                    }
                }

                if (!thread.getIsServer()) {
                    thread.send(c);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + data.state);
        }
    }




}