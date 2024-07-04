import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;

public class StarshipRobot {
    private static PApplet processing;
    private int speed = 3;
    private float x;
    private float y;
    private Thing source; // it represents the source of the StarshipRobot in its current journey.
    private Thing destination; // It represents the destination of the StarshipRobot in its current journey.
    private PImage image;



    public StarshipRobot(Thing source, Thing destination, int speed) {
        this.source = source;
        this.destination = destination;
        this.speed = speed;
        this.image = processing.loadImage("images" + File.separator + "starshipRobot.png");
        this.x = source.getX();
        this.y = source.getY();
    }

    public PImage image() {
// returns a reference to the PImage of the current StarshipRobot object
        return image;
    }
    public float getX() {
// returns the x-position of the current StarshipRobot object
        return this.x;
    }
    public float getY() {
// returns the y-position of the current StarshipRobot object
        return this.y;
    }
    public void setX(float x) {
// sets the x-position of the current StarshipRobot object
        this.x = x;
    }
    public void setY(float y) {
// sets the y-position of the current StarshipRobot object
        this.y = y;
    }
    public static void setProcessing() {
// sets the processing PApplet static field to the processing
// of the Badger class.
        StarshipRobot.processing = Badger.getProcessing();
    }
    public void draw() {
// draws this StarshipRobot to the display window at its current
// (x,y) position by calling processing.image() method
        processing.image(this.image,this.x,this.y);
    }

    private void moveTowardsDestination(){
        float dx = destination.getX() - x;
        float dy = destination.getY() - y;
        float d = (float) Math.sqrt(dx*dx + dy*dy);
        float newX = x + speed * dx / d;
        float newY = y + speed * dy / d;
        x = newX;
        y = newY;
    }
    /**
     * Checks whether the StarshipRobot overlaps with a Thing.
     *
     * @param thing the Thing object to check for overlap
     * @return true if this StarshipRobot overlaps with the given Thing, false otherwise
     */
    public boolean isOver(Thing thing) {
        return Math.abs(this.x - thing.getX()) < this.image.width / 2 &&
                Math.abs(this.y - thing.getY()) < this.image.height / 2;
    }

    /**
     * Gets the StarshipRobot to move back-and-forth between its source and destination.
     */
    public void go() {
        if (isOver(destination)) {
            // Swap source and destination if the robot reached its destination
            Thing temp = source;
            source = destination;
            destination = temp;
        }
        moveTowardsDestination();
    }
}
