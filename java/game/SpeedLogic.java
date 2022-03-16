package engine;

import data.GameMain;

import java.util.Timer;
import java.util.TimerTask;

public class SpeedLogic extends Thread {

    public SpeedLogic() {
        this.start();
    }

    public void run() {
                GameMain.player.setSpeed(11f);
                System.out.println("speed up!!");
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        GameMain.player.setSpeed(5.5f);
                    }
                };
                timer.schedule(task, 4000);
    }


}