import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models a Thing object in the Dancing Badgers program.
 */
public class Thing {

    private static PApplet processing; // PApplet object that represents the display window

    private PImage image; // image associated with this Thing
    private float x; // x-position of this Thing
    private float y; // y-position of this Thing



    /**
     * Creates a new Thing located at the given position of the screen, and loads its image.
     *
     * @param x             x-position of this Thing
     * @param y             y-position of this Thing
     * @param imageFilename filename of the image to be loaded for this object
     */
    public Thing(float x, float y, String imageFilename) {
        this.x = x;
        this.y = y;
        this.image = processing.loadImage("images" + File.separator + imageFilename);
    }

    /**
     * Draws this Thing to the display window at its current (x, y) position.
     */
    public void draw() {
        processing.image(this.image, this.x, this.y);
    }

    /**
     * Sets the PApplet object display window where this Thing will be drawn.
     *
     * @param processing PApplet object that represents the display window
     */
    public static void setProcessing(PApplet processing) {
        Thing.processing = processing;
    }

    // Getter methods for the instance fields if needed
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public PImage getImage() {
        return image;
    }
}

