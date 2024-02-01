package Pong.Model;

import java.io.Serializable;

public class XYTupel implements Serializable {
    public float x = 0;
    public float y = 0;

    XYTupel() {}

    XYTupel(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY(){
        return y;
    }

    public void setY(float y){
        this.y =y;
    }
    public void  setX(float x){
        this.x =x;
    }
    public float getX(){
        return y;
    }
}
