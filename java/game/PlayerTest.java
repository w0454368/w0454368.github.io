package data;

import static engine.DrawImage.drawQuadTex;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

//    public PlayerTest(float x, float y, float width, float height, Controls controller) {
//        super(x, y, width, height, controller);
//    }


    Player player = new Player(64, 800, 64, 64);
    Items speed = new Items(64, 800, 64, 64);



    //Test collision
    @org.junit.jupiter.api.Test
    void collisionSpeed() {

        Boolean expResult = true;

        boolean collide = player.collisionSpeed(speed);
        assertEquals(expResult, collide);

    }

    //Test collision on Y axis
    @org.junit.jupiter.api.Test
    void collisionSpeed2() {

        Boolean expResult = true;

        player.setPosition(100, 100);
        speed.setPosition(100, 163);

        boolean collide = player.collisionSpeed(speed);
        assertEquals(expResult, collide);

    }

    //Test collision on Y axis
    @org.junit.jupiter.api.Test
    void collisionSpeed3() {

        Boolean expResult = false;

        player.setPosition(100, 100);
        speed.setPosition(100, 165);

        boolean collide = player.collisionSpeed(speed);
        assertEquals(expResult, collide);

    }

    //Test collision on X axis
    @org.junit.jupiter.api.Test
    void collisionSpeed4() {

        Boolean expResult = true;

        player.setPosition(100, 100);
        speed.setPosition(163, 100);

        boolean collide = player.collisionSpeed(speed);
        assertEquals(expResult, collide);

    }

    //Test collision on X axis
    @org.junit.jupiter.api.Test
    void collisionSpeed5() {

        Boolean expResult = false;

        player.setPosition(100, 100);
        speed.setPosition(165, 100);

        boolean collide = player.collisionSpeed(speed);
        assertEquals(expResult, collide);

    }



}