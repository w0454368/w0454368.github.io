package data;

public enum EnemyType {

    Slime("slime"),
    Bat("bat"),
    Spike("spike");

    String textureName;

    EnemyType(String textureName) { this.textureName = textureName; }
}
