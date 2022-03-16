package data;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;

import engine.JumpLimiter;
import engine.SpeedLogic;
import org.newdawn.slick.opengl.Texture;

import static engine.DrawImage.drawQuadTex;
import static engine.DrawImage.quickLoad;

public class Player {

    private float x, y, width, height, speed, jumpHeight, startX, startY;
    private float xMargin = 7f;
    private Texture texture;
    private Controls controller;
    private int health;
    private boolean jumpAllowed;
    private boolean damageable;
    private boolean facingRight;
    private boolean damaged;


    public Player(float x, float y, float width, float height, Controls controller) {
        this.x = x;
        this.y = y;
        this.startX = 70;
        this.startY = 800;
        this.width = width;
        this.height = height;
        this.health = 3;
        this.texture = quickLoad("player");
        this.controller = controller;
        this.speed = 5.5f;
        this.jumpHeight = 135f;
        this.jumpAllowed = true;
        this.damageable = true;
        this.facingRight = true;
        this.damaged = false;
    }

    public Player(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void lowerHealth(int x) {
        this.health -= x;
    }

    public void drawPlayer() {
        if (this.health > 0) {
            drawQuadTex(texture, x, y, width, height);
        } else {
            this.setPosition(startX, startY);
            for (Items boost : GameMain.speedBoosts) {
                boost.setShowItem(true);
            }
            for (Items boost : GameMain.jumpBoosts) {
                boost.setShowItem(true);
            }
            this.health = 3;
        }
    }

    public void checkSprite() {
        if (this.damaged) {
            if (this.facingRight) {
                this.texture = quickLoad("damagedPlayer");
            } else if (!this.facingRight) {
                this.texture = quickLoad("damagedPlayerMirror");
            }
        } else if (!this.damaged) {
            if (this.facingRight) {
                this.texture = quickLoad("player");
            } else if (!this.facingRight) {
                this.texture = quickLoad("playerMirror");
            }
        }
    }


    public boolean detectBlockCollision() {
        boolean colliding = false;
        for (Tile block : GameMain.blocks) {
            if (this.x + xMargin < block.getX() + block.getWidth() && this.x + this.width - xMargin > block.getX() &&
                    this.y < block.getY() + block.getHeight() && this.y + this.height > block.getY()) {
                if (this.x + this.width < block.getX() + (block.getWidth() / 2)) {
                    this.x = block.getX() - this.width - xMargin;
                } else if (this.x > block.getX() + (block.getWidth() / 2)) {
                    this.x = block.getX() + block.getWidth() + xMargin;
                }
                colliding = true;
            }
        }
        return colliding;
    }

    public void applyGravity() {
        boolean onGround = false;
        for (Tile tile : GameMain.blocks) {
            if (this.x + this.width - xMargin >= tile.getX() && this.x + xMargin <= tile.getX() + tile.getWidth() && this.y >= (tile.getY() - tile.getHeight() - 6)
                    && this.y <= (tile.getY() - tile.getHeight() + 6)) {
                this.y = (tile.getY() - tile.getHeight());
                onGround = true;
            }
        }
        for (Tile tile : GameMain.crates) {
            if (this.x + this.width - xMargin >= tile.getX() && this.x + xMargin <= tile.getX() + tile.getWidth() && this.y >= (tile.getY() - tile.getHeight() - 6)
                    && this.y <= (tile.getY() - tile.getHeight() + 6)) {
                this.y = (tile.getY() - tile.getHeight());
                onGround = true;
            }
        }
        if (!onGround) {
            this.y += 6f;
        }
    }

    public void move() {
        if (controller.getRightPressed()) {
            if (!this.facingRight) {
                this.facingRight = true;
            }
            this.x += this.speed;
            controller.setRightPressed(false);
        }

        if (controller.getLeftPressed()) {
            if (this.facingRight) {
                this.facingRight = false;
            }
            this.x -= this.speed;
            controller.setLeftPressed(false);
        }

        if (controller.getJumpPressed()) {
            JumpLimiter limiter = new JumpLimiter();
            controller.setJumpPressed(false);
        }
    }

    public void collisionKey(Items key) {

        float keyX = key.getX();
        float keyY = key.getY();
        float keyWidth = key.getWidth();
        float keyHeight = key.getHeight();

        if (this.x < keyX + keyWidth &&
                this.x + this.width > keyX &&
                this.y < keyY + keyHeight &&
                this.y + this.height > keyY
                && key.getShowItem() == true) {
            int doorX = (int) (GameMain.closedDoors.get(0).getX() / 64);
            int doorY = (int) (GameMain.closedDoors.get(0).getY() / 64);
            System.out.println("Obtained key!");
            key.setShowItem(false);
            GameMain.grid.setTile(doorX, doorY, TileType.OpenDoor);
            GameMain.grid.refreshDoors();
        }
    }

    public boolean collisionSpeed(Items speedObj) {

        float speedX = speedObj.getX();
        float speedY = speedObj.getY();
        float speedWidth = speedObj.getWidth();
        float speedHeight = speedObj.getHeight();


        if (this.x < speedX + speedWidth &&
                this.x + this.width > speedX &&
                this.y < speedY + speedHeight &&
                this.y + this.height > speedY &&
                speedObj.getShowItem()) {
            speedObj.setShowItem(false);
            return true;
        } else {
            return false;
        }
    }

    public boolean collisionJump(Items jumpObj) {

        float jumpX = jumpObj.getX();
        float jumpY = jumpObj.getY();
        float jumpWidth = jumpObj.getWidth();
        float jumpHeight = jumpObj.getHeight();


        if (this.x < jumpX + jumpWidth &&
                this.x + this.width > jumpX &&
                this.y < jumpY + jumpHeight &&
                this.y + this.height > jumpY &&
                jumpObj.getShowItem()) {
            jumpObj.setShowItem(false);
            return true;
        } else {
            return false;
        }
    }

    public boolean collisionDoor(Tile door) {

        float doorX = door.getX();
        float doorY = door.getY();
        float doorWidth = door.getWidth();
        float doorHeight = door.getHeight();

        if (this.x < doorX + doorWidth &&
                this.x + this.width > doorX &&
                this.y < doorY + doorHeight &&
                this.y + this.height > doorY && door.getType() == TileType.OpenDoor) {
            return true;
        } else {
            return false;
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public boolean isDamageable() {
        return this.damageable;
    }

    public boolean isJumpAllowed() {
        return jumpAllowed;
    }

    public void setJumpAllowed(Boolean bool) {
        this.jumpAllowed = bool;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setStartX(float x) {
        this.startX = x;
    }

    public void setStartY(float y) {
        this.startY = y;
    }

    public void setDamageable(Boolean bool) {
        this.damageable = bool;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getJumpHeight() {
        return this.jumpHeight;
    }

    public void setJumpHeight(float jump) {
        this.jumpHeight = jump;
    }

    public boolean isFacingRight() { return this.facingRight; }

    public boolean isDamaged() { return this.damaged; }

    public void setDamaged(Boolean bool) {
        this.damaged = bool;
    }
}

