import java.awt.*;

public class Bricks {
    private Color color;
    private int x, y;
    private int width, height;
    private boolean isVisible;

    public Bricks(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isVisible = true;
        this.color = color;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getColor() {
        return this.color;
    }

    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

}
