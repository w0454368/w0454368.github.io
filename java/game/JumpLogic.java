package engine;

import data.GameMain;

import java.util.Timer;
import java.util.TimerTask;

public class JumpLogic extends Thread {

    public JumpLogic() {
        this.start();
    }

    public void run() {
        GameMain.player.setJumpHeight(250f);
        System.out.println("jump height up!!");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GameMain.player.setJumpHeight(150f);
            }
        };
        timer.schedule(task, 4000);
    }
}