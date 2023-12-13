import java.awt.*;

public class Bricks {

    // Initialize variables: brick's position, size, color, and visibility.

    private Color color;
    private int x, y;
    private int width, height;
    private boolean isVisible;

    /**
     * Bricks constructor
     * @param int x
     * @param int y
     * @param int width
     * @param int height
     * @param Color color
     * @return none
     * Creates a brick object with the specified x, y, width, height, and color
     */

    public Bricks(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isVisible = true;
        this.color = color;
    }

    // 7 getters and 1 setter for the variables.

    /**
     * isVisible method
     * @param none
     * @return boolean
     * Returns the visibility of the brick
     */

    public boolean isVisible() {
        return isVisible;
    }

    /**
     * setVisibility method
     * @param boolean isVisible
     * @return none
     * Sets the visibility of the brick
     */

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * getX method
     * @param none
     * @return int
     * Returns the x position of the brick
     */

    public int getX() {
        return this.x;
    }

    /**
     * getY method
     * @param none
     * @return int
     * Returns the y position of the brick
     */

    public int getY() {
        return this.y;
    }

    /**
     * getWidth method
     * @param none
     * @return int
     * Returns the width of the brick
     */

    public int getWidth() {
        return this.width;
    }

    /**
     * getHeight method
     * @param none
     * @return int
     * Returns the height of the brick
     */

    public int getHeight() {
        return this.height;
    }

    /**
     * getColor method
     * @param none
     * @return Color
     * Returns the color of the brick
     */

    public Color getColor() {
        return this.color;
    }

    /**
     * getRect method
     * @param none
     * @return Rectangle
     * Returns the rectangle of the brick
     */

    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

}
