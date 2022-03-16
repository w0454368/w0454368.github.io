package data;

public class TileGrid {

    private static int level;
    public static Tile[][] map;

    public TileGrid(int[][] newMap, int level) {
        this.level = level;
        map = new Tile[20][15];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (newMap[j][i]) {
                    case 0 -> map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Block);
                    case 1 -> map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Wall);
                    case 2 -> map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Door);
                    case 3 -> map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.OpenDoor);
                    case 4 -> map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Crate);
                }
            }
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setTile(int xPos, int yPos, TileType type) {
        map[xPos][yPos] = new Tile(xPos * 64, yPos * 64, 64, 64, type);
    }

    public Tile getTile(int xPos, int yPos) {
        return map[xPos][yPos];
    }

    public void drawGrid() {
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                tile.drawTile();
            }
        }
    }

    public void refreshBlocks() {
        GameMain.blocks.clear();
        GameMain.crates.clear();
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                if (tile.getType() == TileType.Block) {
                    GameMain.blocks.add(tile);
                }
                if (tile.getType() == TileType.Crate) {
                    GameMain.crates.add(tile);
                }
            }
        }
    }

    public void refreshDoors() {
        GameMain.openDoors.clear();
        GameMain.closedDoors.clear();
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                if (tile.getType() == TileType.OpenDoor) {
                    GameMain.openDoors.add(tile);
                }
                if (tile.getType() == TileType.Door) {
                    GameMain.closedDoors.add(tile);
                }
            }
        }
    }
}
