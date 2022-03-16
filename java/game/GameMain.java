package data;

import engine.DrawImage;
import engine.JumpLogic;
import engine.SpeedLogic;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;

public class GameMain {

    public static Player player;
    public static ArrayList<Tile> blocks = new ArrayList<>();
    public static ArrayList<Tile> crates = new ArrayList<>();
    public static ArrayList<Tile> openDoors = new ArrayList<>();
    public static ArrayList<Tile> closedDoors = new ArrayList<>();
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Items> jumpBoosts = new ArrayList<>();
    public static ArrayList<Items> speedBoosts = new ArrayList<>();
    public static ArrayList<Items> keys = new ArrayList<>();
    public static TileGrid grid;
    public static boolean gameWin = false;

    public GameMain() {

        DrawImage.beginSession();

        int[][] level1 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 2, 0},
                {0, 1, 1, 4, 4, 1, 4, 4, 1, 1, 0, 1, 1, 1, 1, 4, 1, 4, 4, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 0},
                {0, 4, 4, 1, 1, 1, 1, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 0},
                {0, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 4, 0},
                {0, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] level2 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 2, 0},
                {0, 1, 4, 4, 4, 4, 1, 1, 1, 1, 1, 4, 4, 0, 4, 1, 1, 4, 4, 0},
                {0, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 4, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 4, 1, 0},
                {0, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] level3 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 4, 4, 4, 1, 4, 4, 0, 1, 1, 4, 4, 0, 1, 4, 4, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 4, 1, 1, 0, 1, 1, 1, 1, 4, 4, 0},
                {0, 1, 4, 4, 4, 4, 4, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 4, 1, 1, 4, 0, 1, 1, 4, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 1, 4, 1, 1, 0, 4, 1, 1, 1, 1, 1, 0},
                {0, 4, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0},
                {0, 4, 4, 4, 4, 4, 1, 0, 1, 1, 4, 1, 0, 1, 1, 4, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 2, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] level4 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 0},
                {0, 1, 1, 4, 4, 1, 4, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 0},
                {0, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 0},
                {0, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        int[][] level5 = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 1, 1, 1, 1, 4, 0},
                {0, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0},
                {0, 4, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 1, 4, 4, 4, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        grid = new TileGrid(level1, 1);

        grid.refreshBlocks();

        Controls controller = new Controls();
        grid.refreshDoors();
        player = new Player(150, 800, 64, 64, controller);
        speedBoosts.add(new Items(1152, 315, 64, 64, ItemType.Speed));
        jumpBoosts.add(new Items(395, 510, 64, 64, ItemType.Jump));
        keys.add(new Items(215, 130, 64, 64, ItemType.Key));
        enemies.add(new Enemy(64, 510, 64, 64, 64, 704, false, EnemyType.Slime));
        enemies.add(new Enemy(1024, 70, 64, 64, 69, 460, true, EnemyType.Bat));

        while (!Display.isCloseRequested() && !gameWin) {

            grid.drawGrid();
            for (Items boost : speedBoosts) {
                boost.drawItem();
                if (player.collisionSpeed(boost)) {
                    SpeedLogic speedUp = new SpeedLogic();
                }
            }
            for (Items boost : jumpBoosts) {
                boost.drawItem();
                if (player.collisionJump(boost)) {
                    JumpLogic jumpUp = new JumpLogic();
                }
            }
            for (Items key : keys) {
                key.drawItem();
                player.collisionKey(key);
            }
            for (Enemy enemy : enemies) {
                enemy.drawEnemy();
            }
            player.checkSprite();
            player.drawPlayer();
            controller.checkInput();
            player.move();
            player.applyGravity();
            player.detectBlockCollision();

            if (!openDoors.isEmpty()) {
                if (player.collisionDoor(openDoors.get(0))) {
                    switch (grid.getLevel()) {
                        case 1:
                            grid = new TileGrid(level2, 2);
                            grid.refreshBlocks();
                            keys.clear();
                            keys.add(new Items(770, 131, 64, 64, ItemType.Key));
                            jumpBoosts.clear();
                            jumpBoosts.add(new Items(515, 706, 64, 64, ItemType.Jump));
                            speedBoosts.clear();
                            speedBoosts.add(new Items(130, 125, 64, 64, ItemType.Speed));
                            for (Enemy enemy : enemies) {
                                enemy.killEnemy();
                            }
                            enemies.clear();
                            enemies.add(new Enemy(384, 100, 64, 64, 64, 384, true, EnemyType.Bat));
                            enemies.add(new Enemy(1024, 200, 64, 64, 64, 512, false, EnemyType.Bat));
                            for (int i = 2; i < 18; i++) {
                                enemies.add(new Enemy((i*64),832, 64, 64, EnemyType.Spike));
                            }
                            player.setStartX(70);
                            player.setStartY(440);
                            player.setPosition(player.getStartX(), player.getStartY());
                            grid.refreshDoors();
                            System.out.println("going to level 2!");
                            break;
                        case 2:
                            grid = new TileGrid(level3, 3);
                            grid.refreshBlocks();
                            keys.clear();
                            keys.add(new Items(704,390,64,64, ItemType.Key));
                            jumpBoosts.clear();
                            jumpBoosts.add(new Items(704,770,64,64, ItemType.Jump));
                            speedBoosts.clear();
                            speedBoosts.add(new Items(64, 576, 64, 64, ItemType.Speed));
                            for (Enemy enemy : enemies) {
                                enemy.killEnemy();
                            }
                            enemies.clear();
                            enemies.add(new Enemy(130, 320, 64, 64, 128, 384, false, EnemyType.Slime));
                            enemies.add(new Enemy(150, 640, 64, 64, 128, 320, true, EnemyType.Slime));
                            enemies.add(new Enemy(170, 832, 64, 64, 64, 640, false, EnemyType.Slime));
                            enemies.add(new Enemy(1028, 80, 64, 64, 64, 780, false, EnemyType.Bat));
                            enemies.add(new Enemy(896, 450, 64, 64, 256, 780, true, EnemyType.Bat));
                            for (int i = 13; i < 17; i++) {
                                enemies.add(new Enemy((i*64),832, 64, 64, EnemyType.Spike));
                            }
                            grid.refreshDoors();
                            player.setStartX(70);
                            player.setStartY(70);
                            player.setPosition(player.getStartX(), player.getStartY());
                            System.out.println("going to level 3!");
                            break;
                        case 3:
                            grid = new TileGrid(level4, 4);
                            grid.refreshBlocks();
                            keys.clear();
                            keys.add(new Items(64, 256, 64, 64, ItemType.Key));
                            jumpBoosts.clear();
                            jumpBoosts.add(new Items(576, 704, 64, 64, ItemType.Jump));
                            speedBoosts.clear();
                            speedBoosts.add(new Items(1152, 320, 64, 64, ItemType.Speed));
                            for (Enemy enemy : enemies) {
                                enemy.killEnemy();
                            }
                            enemies.clear();
                            enemies.add(new Enemy(256, 350, 64, 64, 256, 780, true, EnemyType.Bat));
                            enemies.add(new Enemy(448, 450, 64, 64, 64, 780, false, EnemyType.Bat));
                            enemies.add(new Enemy(640, 550, 64, 64, 256, 780, true, EnemyType.Bat));
                            enemies.add(new Enemy(768, 100, 64, 64, 64, 448, false, EnemyType.Bat));
                            enemies.add(new Enemy(1024, 650, 64, 64, 64, 780, true, EnemyType.Bat));
                            for (int i = 1; i < 19; i++) {
                                enemies.add(new Enemy((i*64),832, 64, 64, EnemyType.Spike));
                            }
                            grid.refreshDoors();
                            player.setStartX(150);
                            player.setStartY(600);
                            player.setPosition(player.getStartX(), player.getStartY());
                            System.out.println("going to level 4!");
                            break;
                        case 4:
                            grid = new TileGrid(level5, 5);
                            grid.refreshBlocks();
                            keys.clear();
                            keys.add(new Items(320, 448, 64, 64, ItemType.Key));
                            jumpBoosts.clear();
                            speedBoosts.clear();
                            speedBoosts.add(new Items(384, 128, 64, 64, ItemType.Speed));
                            speedBoosts.add(new Items(1152, 384, 64, 64, ItemType.Speed));
                            for (Enemy enemy : enemies) {
                                enemy.killEnemy();
                            }
                            enemies.clear();
                            enemies.add(new Enemy(130, 192, 64, 64, 128, 320, false, EnemyType.Slime));
                            enemies.add(new Enemy(500, 192, 64, 64, 448, 640, true, EnemyType.Slime));
                            enemies.add(new Enemy(800, 192, 64, 64, 768, 1028, false, EnemyType.Slime));
                            enemies.add(new Enemy(200, 448, 64, 64, 192, 448, true, EnemyType.Slime));
                            enemies.add(new Enemy(600, 448, 64, 64, 576, 768, false, EnemyType.Slime));
                            enemies.add(new Enemy(1000, 448, 64, 64, 896, 1088, true, EnemyType.Slime));
                            enemies.add(new Enemy(192, 650, 64, 64, 576, 780, true, EnemyType.Bat));
                            enemies.add(new Enemy(320, 650, 64, 64, 576, 780, false, EnemyType.Bat));
                            enemies.add(new Enemy(448, 650, 64, 64, 576, 780, true, EnemyType.Bat));
                            enemies.add(new Enemy(576, 650, 64, 64, 576, 780, false, EnemyType.Bat));
                            enemies.add(new Enemy(704, 650, 64, 64, 576, 780, true, EnemyType.Bat));
                            enemies.add(new Enemy(832, 650, 64, 64, 576, 780, false, EnemyType.Bat));
                            enemies.add(new Enemy(960, 650, 64, 64, 576, 780, true, EnemyType.Bat));
                            for (int i = 1; i < 19; i++) {
                                enemies.add(new Enemy((i*64),832, 64, 64, EnemyType.Spike));
                            }
                            grid.refreshDoors();
                            player.setStartX(64);
                            player.setStartY(128);
                            player.setPosition(player.getStartX(), player.getStartY());
                            System.out.println("going to level 5!");
                            break;
                        case 5:
                            gameWin = true;
                            System.out.println("YOU WIN!!!");
                    }
                }
            }
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }


    public static void main(String[] args) {
        new GameMain();
    }
}