package tk.twpooi.happycake;

import java.io.Serializable;

/**
 * Created by tw on 2017. 2. 4..
 */

public class CustomPoint implements Serializable {

    public int x;
    public int y;

    CustomPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}
