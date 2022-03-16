package data;

import engine.DamageFlicker;
import engine.InvulnTime;
import org.newdawn.slick.opengl.Texture;

import static engine.DrawImage.drawQuadTex;
import static engine.DrawImage.quickLoad;

public class Enemy extends Thread {

    private float x, y, width, height, minRange, maxRange;
    private Texture texture;
    private EnemyType type;
    private boolean moveLeft, isAlive;
    private float xMargin = 30f;
    private float yMargin = 15f;

    public Enemy(float x, float y, float width, float height, float minRange, float maxRange, boolean moveLeft, EnemyType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.type = type;
        this.texture = quickLoad(type.textureName);
        this.moveLeft = moveLeft;
        this.isAlive = true;
        this.start();
    }

    public Enemy(float x, float y, float width, float height, EnemyType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.texture = quickLoad(type.textureName);
        this.isAlive = true;
        this.start();
    }

    public boolean collision() {

        float playerX = GameMain.player.getX();
        float playerY = GameMain.player.getY();
        float playerWidth = GameMain.player.getWidth();
        float playerHeight = GameMain.player.getHeight();

        if (this.x < playerX + playerWidth - xMargin &&
                this.x + this.width > playerX  + xMargin &&
                this.y < playerY + playerHeight - yMargin &&
                this.y + this.height > playerY + yMargin) {
            return true;
        } else {
            return false;
        }
    }

    public void drawEnemy() {
        if (this.isAlive) {
            drawQuadTex(texture, x, y, width, height);
        }
    }

    public void killEnemy() {
        this.isAlive = false;
    }

    public void moveLeft() {
        this.x -= 1.5f;
    }

    public void moveRight() {
        this.x += 1.5f;
    }

    public void moveUp() { this.y -= 1.5f; }

    public void moveDown() { this.y += 1.5f; }

    @Override
    public void run() {
        while (this.isAlive) {
            if (this.type == EnemyType.Slime) {
                if (this.x > minRange && this.moveLeft) {
                    this.moveLeft();
                } else if (this.x <= minRange && this.moveLeft) {
                    this.moveLeft = false;
                }
                if (this.x < maxRange && !this.moveLeft) {
                    this.moveRight();
                } else if (this.x >= maxRange && !this.moveLeft) {
                    this.moveLeft = true;
                }
            }
            if (this.type == EnemyType.Bat) {
                if (this.y < maxRange && this.moveLeft) {
                    this.moveDown();
                } else if (this.y >= maxRange && this.moveLeft) {
                    this.moveLeft = false;
                }
                if (this.y > minRange && !this.moveLeft) {
                    this.moveUp();
                } else if (this.y <= minRange && !this.moveLeft) {
                    this.moveLeft = true;
                }
            }
            if (this.type != EnemyType.Spike && this.collision() && GameMain.player.isDamageable()) {
                GameMain.player.lowerHealth(1);
                InvulnTime invulnTime = new InvulnTime();
                DamageFlicker damageFlicker = new DamageFlicker();
                System.out.println("Damage taken!");
            }
            if (this.type == EnemyType.Spike && this.collision() && GameMain.player.isDamageable()) {
                GameMain.player.lowerHealth(3);
            }

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
