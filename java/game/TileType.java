package data;

public enum TileType {

    Wall("wall3"),
    Block("block"),
    Door("door"),
    OpenDoor("openDoor"),
    Crate("crate");

    String textureName;


    TileType(String textureName) {
        this.textureName = textureName;
    }
}
