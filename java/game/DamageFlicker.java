package engine;

import data.GameMain;

import java.util.Timer;
import java.util.TimerTask;

public class DamageFlicker extends Thread {

    public DamageFlicker() {
        this.start();
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        while (!GameMain.player.isDamageable()) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    if (GameMain.player.isDamaged()) {
                        GameMain.player.setDamaged(false);
                    } else {
                        GameMain.player.setDamaged(true);
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 50);
        }
        timer.cancel();
        try {
            Thread.sleep(50);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        GameMain.player.setDamaged(false);
    }
}
