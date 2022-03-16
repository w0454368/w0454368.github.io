package data;

public enum ItemType {

    Speed("speed"),
    Jump("jump"),
    Key("key");

    String textureName;


    ItemType(String textureName) {
        this.textureName = textureName;
    }
}
