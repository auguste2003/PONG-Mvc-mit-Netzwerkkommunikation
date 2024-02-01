package Pong.Model;

import java.io.Serializable;

public class Player implements Serializable {
    private float size; // epaisseur de la barre

    public XYTupel position; // position de la barre

    Player(double x, double y, float size) {// initialisation de la position
        this.position = new XYTupel((float)x, (float)y);
        this.size = size;
    }

    public boolean hits(Ball ball) { // Schlag
        // Note: This only works in 90% of all cases. Unfortunately it does not work in edge cases (literally).
        //       Sometimes this can cause funny behaviors, because it uses a rectangular bounding box for the ball.
        return ball.collidesX(position.x) && // Reourne vrai si les differents objects se touchent vraiment
                position.y- size/2.0 <= ball.position.y + ball.getSize()/2.0 &&
                position.y + size/2.0 >= ball.position.y- ball.getSize()/2.0;
    }

    public float getSize() {
        return size; // rtoune la position
    }

public XYTupel getPosition(){
        return position;
}
}
