import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PImage;

/**
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // backgound image
  private static ArrayList<Badger> badgers; // array storing badger objects
  private static Random randGen; // Generator of random numbers
  private static final int badgersCountMax = 5;

  // arraylist storing Thing objects
  private static ArrayList<Thing> things;
  private static ArrayList<StarshipRobot> robots;


  /**
   * Driver method to run this graphic application
   *
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application
   */
  public static void setup() {
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new ArrayList<>(5);
    randGen = new Random();
    things = new ArrayList<>();
    robots = new ArrayList<>();
    // Set the processing instance for Thing
    Thing.setProcessing(Badger.getProcessing());

    // Create and add Thing objects to the things ArrayList
    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));

    StarshipRobot.setProcessing();
    robots.add(new StarshipRobot(things.get(2),things.get(0),3));
    robots.add(new StarshipRobot(things.get(3), things.get(1),3));



  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    for (Thing thing: things){
      thing.draw();
    }
    for (StarshipRobot robot : robots){
      robot.go();
      robot.draw();
    }
    for (int i = 0; i < badgers.size(); i++)
      if (badgers.get(i) != null) {
        badgers.get(i).draw();
      }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (int i = 0; i < badgers.size(); i++)
      if (badgers.get(i) != null && badgers.get(i).isMouseOver()) {
        badgers.get(i).startDragging();
        break;
      }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for (int i = 0; i < badgers.size(); i++)
      if (badgers.get(i) != null)
        badgers.get(i).stopDragging();
  }


  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
                // present in the field is not reached
        for (int i = 0; i < badgersCountMax; i++) {
            badgers.add(new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
            break;
        }
        break;
      case 'R': // delete the badger being pressed
        for (int i = 0; i < badgers.size(); i++) {
          if (badgers.get(i) != null && badgers.get(i).isMouseOver()) {
            badgers.remove(i);
            break;
          }
        }
        break;
    }
  }
}
