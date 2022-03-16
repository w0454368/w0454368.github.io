package data;

import org.lwjgl.input.Keyboard;

public class Controls {

    private boolean rightPressed, leftPressed, jumpPressed;

    public Controls() {
        this.rightPressed = false;
        this.leftPressed = false;
        this.jumpPressed = false;
    }

    public boolean getRightPressed() {
        return this.rightPressed;
    }

    public boolean getLeftPressed() {
        return this.leftPressed;
    }

    public boolean getJumpPressed() {
        return this.jumpPressed;
    }

    public void setRightPressed(Boolean bool) {
        this.rightPressed = bool;
    }

    public void setLeftPressed(Boolean bool) {
        this.leftPressed = bool;
    }

    public void setJumpPressed(Boolean bool) {
        this.jumpPressed = bool;
    }

    public void checkInput() {

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            if (!this.rightPressed) {
                this.rightPressed = true;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            if (!this.leftPressed) {
                this.leftPressed = true;
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if (!this.jumpPressed) {
                this.jumpPressed = true;
            }
        }

        // God Mode cheat, for demo
        if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
            GameMain.player.setDamageable(!GameMain.player.isDamageable());
        }

        // Reset player
        if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
            GameMain.player.lowerHealth(3);
        }
    }

}