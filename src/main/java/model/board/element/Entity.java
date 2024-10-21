package model.board.element;

import model.board.Board;
import model.board.Direction;
import model.board.element.deposable.Bomb;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

import static model.board.Size.TILE_HEIGHT;
import static model.board.Size.TILE_WIDTH;

/**
 * A base class representing entities on the game board.
 */
public abstract class Entity {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected double velocity;
    protected Image image;
    protected boolean alive;
    protected boolean visible;
    protected boolean explodable;
    protected int id;
    protected static int entityCounter = 0;
    protected boolean removable;
    protected Direction dir;

    /**
     * Constructs an Entity with the specified parameters.
     *
     * @param x        the x-coordinate of the entity
     * @param y        the y-coordinate of the entity
     * @param width    the width of the entity
     * @param height   the height of the entity
     * @param velocity the velocity of the entity
     * @param image    the image representing the entity
     * @param alive    indicates if the entity is alive
     * @param visible  indicates if the entity is visible
     */
    public Entity(double x, double y, int width, int height, double velocity, Image image, boolean alive, boolean visible) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.image = image;
        this.alive = alive;
        this.visible = visible;
        this.id = ++entityCounter;
        this.removable = false;
        this.dir = null;
    }

    /**
     * Checks if this entity collides with another entity.
     *
     * @param other the other entity to check collision with
     * @return true if this entity collides with the other, false otherwise
     */
    public boolean collides(Entity other) {
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, height);
        Rectangle2D.Double otherRect = new Rectangle2D.Double(other.x, other.y, other.width, other.height);
        return rect.intersects(otherRect);
    }

    /**
     * Draws the entity on the given graphics context, if visible.
     *
     * @param g the graphics context to draw on
     */
    public void draw(Graphics g) {
        if(this.visible) {
            int drawX = (int) Math.round(x);
            int drawY = (int) Math.round(y);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(image, drawX, drawY + TILE_HEIGHT.getSize(), width, height, null);
            g2d.dispose();
        }
    }

    /**
     * Gets the x-coordinate of the entity.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the entity.
     *
     * @param x the x-coordinate to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the entity.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the entity.
     *
     * @param y the y-coordinate to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the width of the entity.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the entity.
     *
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the entity.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the entity.
     *
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the velocity of the entity.
     *
     * @return the velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the entity.
     *
     * @param velocity the velocity to set
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the image of the entity.
     *
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image of the entity.
     *
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Checks if the entity is alive.
     *
     * @return true if the entity is alive, false otherwise
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Sets the alive status of the entity.
     *
     * @param alive the alive status to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Checks if the entity is visible.
     *
     * @return true if the entity is visible, false otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the visibility of the entity.
     *
     * @param visible the visibility to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Checks if the entity is removable.
     *
     * @return true if the entity is removable, false otherwise
     */
    public boolean isRemovable() {
        return removable;
    }

    /**
     * Sets the removable status of the entity.
     *
     * @param removable the removable status to set
     */
    public void setRemovable(boolean removable) {
        this.removable = removable;
    }

    /**
     * Moves the entity in the specified direction.
     *
     * @param direction the direction to move the entity
     */
    public void moveTowardsDirection(Direction direction) {
        switch(direction) {
            case UP:
                this.y -= this.velocity;
                break;
            case DOWN:
                this.y += this.velocity;
                break;
            case LEFT:
                this.x -= this.velocity;
                break;
            case RIGHT:
                this.x += this.velocity;
                break;
        }
    }

    /**
     * Checks if the entity is explodable.
     *
     * @return true if the entity is explodable, false otherwise
     */
    public boolean isExplodable() {
        return explodable;
    }

    /**
     * Sets the explodable status of the entity.
     *
     * @param explodable the explodable status to set
     */
    public void setExplodable(boolean explodable) {
        this.explodable = explodable;
    }

    /**
     * Checks if this entity is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id == entity.id;
    }

    /**
     * Generates a hash code for this entity.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Gets the center coordinate of the entity.
     *
     * @return the center coordinate as a Point object
     */
    public Point getCenterCoordinate() {
        return new Point((int) (x+(width/2)), (int) (y+(height/2)));
    }

    /**
     * Gets the column index of the entity on the game board.
     *
     * @return the column index of the entity
     */
    public int getColumn() {
        return (int)getCenterCoordinate().getX()/TILE_WIDTH.getSize();
    }

    /**
     * Gets the row index of the entity on the game board.
     *
     * @return the row index of the entity
     */
    public int getRow() {
        return (int)getCenterCoordinate().getY()/TILE_HEIGHT.getSize();
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }
}
