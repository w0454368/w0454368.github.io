package engine;

import data.GameMain;

import java.util.Timer;
import java.util.TimerTask;

public class InvulnTime extends Thread {

    public InvulnTime() {
        this.start();
    }

    public void run() {
        if (GameMain.player.isDamageable()) {
            GameMain.player.setDamageable(false);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    GameMain.player.setDamageable(true);
                }
            };
            timer.schedule(task, 1000);
        }
    }
}