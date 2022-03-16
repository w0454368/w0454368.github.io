package engine;

import data.GameMain;

import java.util.Timer;
import java.util.TimerTask;

public class JumpLimiter extends Thread {

    public JumpLimiter() {
        this.start();
    }

    public void run() {
        if (GameMain.player.isJumpAllowed()) {
            GameMain.player.setJumpAllowed(false);

            for (int i = 0; i < (int) GameMain.player.getJumpHeight(); i++) {
                GameMain.player.setPosition(GameMain.player.getX(), (GameMain.player.getY()) - 1);
                if (GameMain.player.detectBlockCollision()) {break;}
            }

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    GameMain.player.setJumpAllowed(true);
                }
            };
            timer.schedule(task, 500);
        }    // else {
//            System.out.println("Jump not allowed!");
//        }
    }


}
