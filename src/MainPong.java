/**
import Pong.Controller.ClientServerThread;
import Pong.Controller.Controller;
import processing.core.PApplet;
import Pong.Model.PongModel;
import Pong.Model.PongData;

import Pong.View.PongView;

public class MainPong {

    public static void main(String[] args) {
            var data =new PongData();
            var model = new PongModel(900, 600);
            var controller = new Controller();
            var view = new PongView(900,600);

            var c = ClientServerThread.newClient("localhost",8080,model,controller);
            var s = ClientServerThread.newServer(8080,model,controller);
            // Connect M, V and C
            controller.setModel(model);
            controller.setView(view);
            view.setController(controller);

            // Starts the processing application
            PApplet.runSketch(new String[]{"PongView"}, view);
        }


} */
