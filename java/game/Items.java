package data;

import org.newdawn.slick.opengl.Texture;

import static engine.DrawImage.drawQuadTex;
import static engine.DrawImage.quickLoad;

public class Items {

    private boolean showItem;
    private float x, y, width, height;
    private Texture texture;
    private ItemType type;

    public boolean getShowItem() {
        return showItem;
    }

    public void setShowItem(boolean showItem) {
        this.showItem = showItem;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public ItemType getType() { return type; }

    public void setType(ItemType type) {
        this.type = type;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Items(float x, float y, float width, float height, ItemType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.texture = quickLoad(type.textureName);
        this.showItem = true;
    }

    public Items(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawItem() {
        if (this.showItem) { drawQuadTex(texture,x,y,width,height); }
    }


}
