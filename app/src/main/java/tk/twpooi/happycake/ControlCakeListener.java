package tk.twpooi.happycake;

/**
 * Created by tw on 2017. 2. 4..
 */

public interface ControlCakeListener {

    boolean reduceStraw();
    boolean increaseStraw();
    boolean reduceGrap();
    boolean increaseGrap();
    boolean reduceBlue();
    boolean increaseBlue();
    int getStrawSize();
    int getGrapSize();
    int getBlueSize();

}
