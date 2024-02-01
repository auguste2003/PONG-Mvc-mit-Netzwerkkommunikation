import Pong.Controller.PongController;
import Pong.Model.PongModel;
import Pong.View.PongView;
import processing.core.PApplet;

public class client {
    public static void main(String[] args){
        final int port =8080;

        var model = new PongModel(900, 600);
        var controller = PongController.newClient("localhost",port,600,model);
        var view = new PongView();



        // Connect M, V and C
        controller.setModel(model);
        controller.setView(view);
        view.setController(controller);

        // Starts the processing application
        PApplet.runSketch(new String[]{"PongView"}, view);
    }
}
