package Pong.Model;

import java.io.Serializable;

public class Ball implements Serializable {
    private float size; // Größe des Balles
    private int speedFactor = 150;  // un facteur important opour determiner l'acceleration de la balle

    private float angle = 0;// l'angle de la balle et qui sera important pour la rotation de la balle sur elle meme

    public XYTupel position; // un couple qui donne la position de la balle
    XYTupel acceleration;// couple qui donne l'aceleration de la balle

    public Ball(double x, double y, float size) { /// lorsqu'on appelle la balle de coordonnee x ,y
        // on a un position mais l'aceleration es 0
        this.position = new XYTupel((float)x, (float)y);
        this.acceleration = new XYTupel(0, 0);
        this.size = size;
    }

    public void updateBallPosition(double timeFactor) {// position a un temp donne
        position.x += (float) (acceleration.x * speedFactor * timeFactor);/*Donnee en fonction du
        temp . facteur vitesse et facteur aceleration
        */
        position.y += (float) (acceleration.y * speedFactor * timeFactor);
        angle += 2;// l'angle de rotation
    }

    public void randomizeAcceleration() {// impose que la balle se deplasse a une direction aleatoire
        acceleration.x = (Math.random() >= 0.5) ? 1 : -1; // -1 ou 1
        acceleration.y = (float) (2 * Math.random() - 1);//entre -1 1
    }

    public boolean collidesY(float wall) { // verifie la colisiions sur les bords de la balle
        return (wall >= position.y - size / 2.0) && (wall <= position.y + size / 2.0);
    }

    public boolean collidesX(float wall) {// pour l'axe des x
        return (wall >= position.x - size / 2.0) && (wall <= position.x + size / 2.0);
    }
    public XYTupel getAcceleration(){
        return acceleration;
    }
    public XYTupel getPosition(){
        return position;
    }

    public float getSize() {// epaisseur de la balle
        return size;
    }

    public float getRotationAngle() {// retourne l'angle de rotation de la balle
        return (float)Math.toRadians(angle);
    }
}
