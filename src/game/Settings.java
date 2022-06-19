/**
 * @name Settings
 * @package game
 * @file Settings.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description this class contains the settings for a game.
 */

package game;

import enums.Difficulties;
import java.util.Scanner;

public class Settings {

    private int numExits;                       // amount of exits.
    private int numPlayers;                     // amount of players.
    private int numRemedies;                    // amount of remedies.
    private int numZombies;                     // amount of zombies.
    private int zombieSleep = 0;                // rounds that the zombies sleep until first move.
    private int numItems;                       // amount of items.
    private int numObstacles;                   // amount of obstacles.
    private final boolean hasCustomSettings;    // true if game has custom settings.
    private boolean portals;                    // true if game enables portals.

    /**
     * Constructs a Settings element depending on input.
     * @param numExits          amount of exits.
     * @param numPlayers        amount of players.
     * @param numRemedies       amount of remedies.
     * @param numZombies        amount of zombies.
     * @param zombieSleep       rounds that the zombies will sleep.
     * @param numItems          amount of items.
     * @param numObstacles      amount of obstacles.
     * @param hasCustomSettings true, if game has custom settings.
     * @param hasPortals        true, if game has portals.
     */
    public Settings(int numExits, int numPlayers, int numRemedies, int numZombies, int zombieSleep, int numItems, int numObstacles,
                    boolean hasCustomSettings, boolean hasPortals) {
        this.numExits = numExits;
        this.numPlayers = numPlayers;
        this.numRemedies = numRemedies;
        this.numZombies = numZombies;
        this.zombieSleep = zombieSleep;
        this.numItems = numItems;
        this.numObstacles = numObstacles;
        this.hasCustomSettings = hasCustomSettings;
        this.portals = hasPortals;
    }

    /**
     * Constructs a Settings element depending on the chosen difficulty.
     * @param difficulty    chosen difficulty (easy, medium or hard).
     */
    public Settings(Difficulties difficulty) {
        switch (difficulty) {
            case EASY -> {
                this.numExits = 1;
                this.numPlayers = 1;
                this.numRemedies = 1;
                this.numZombies = 1;
                this.zombieSleep = 0;
                this.numItems = 1;
                this.numObstacles = 5;
                this.hasCustomSettings = false;
                this.portals = true;
            }
            case MEDIUM -> {
                this.numExits = 1;
                this.numPlayers = 1;
                this.numRemedies = 2;
                this.numZombies = 2;
                this.zombieSleep = 5;
                this.numItems = 2;
                this.numObstacles = 10;
                this.hasCustomSettings = false;
                this.portals = true;
            }
            case HARD -> {
                this.numExits = 1;
                this.numPlayers = 1;
                this.numRemedies = 3;
                this.numZombies = 3;
                this.zombieSleep = 10;
                this.numItems = 3;
                this.numObstacles = 15;
                this.hasCustomSettings = false;
                this.portals = true;
            }
            default -> {
                this.numExits = 1;
                this.numPlayers = 1;
                this.numRemedies = 1;
                this.numZombies = 1;
                this.zombieSleep = 0;
                this.numItems = 0;
                this.numObstacles = 0;
                this.hasCustomSettings = false;
                this.portals = false;
            }
        }
    }

    // constructor for custom settings (currently only working in the terminal):
    public Settings() {
        Scanner sc = new Scanner(System.in);
        boolean isValid = true;
        int inputInt;
        this.hasCustomSettings = true;
        try {
            // if "[2] Custom mode" is chosen, the programm will go through the following queries.
            if (this.hasCustomSettings) {
                // queries for remedies
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many remedies does the player need? (1-5)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // settings for remedies
                    isValid = (inputInt > 0 && inputInt <= 5);
                    if (isValid) {
                        this.numRemedies = inputInt;
                    }
                } while (!isValid);

                // queries for zombies
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many zombies do you want to escape? (1-5)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // settings for zombies
                    isValid = (inputInt > 0 && inputInt <= 5);
                    if (isValid) {
                        this.numZombies = inputInt;
                    }
                } while (!isValid);

                // queries for items
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many items should be on the board? (0-10)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // settings for items
                    isValid = (inputInt >= 0 && inputInt <= 10);
                    if (isValid) {
                        this.numItems = inputInt;
                    }
                } while (!isValid);

                // queries for obstacles
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many obstacles should be on the board? (0-25)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // settings for obstacles
                    isValid = (inputInt >= 0 && inputInt <= 25);
                    if (isValid) {
                        this.numObstacles = inputInt;
                    }
                } while (!isValid);

                // queries for portals
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("Do you want to enable portals?");
                    System.out.println("[1] No portals");
                    System.out.println("[2] portals");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // settings for portals
                    isValid = (inputInt == 1 || inputInt == 2);
                    if (isValid && inputInt == 2) {
                        this.portals = true;
                    }
                } while (!isValid);

                // queries for sleep mode
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    // settings for sleep mode
                    System.out.println("Should the zombies sleep for certain turns? (0-99 Turns)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    isValid = (inputInt >= 0 && inputInt <= 99);
                    if (isValid) {
                        this.zombieSleep = inputInt;
                    }
                } while (!isValid);
            }
        } catch (Exception e) {
            System.err.println("Wrong input, program ends here with exit code 2!");
            System.exit(2);
        }
    }

    /**
     * Method to return amount of exits.
     * @return amount of exits.
     */
    public int getNumExits() {
        return numExits;
    }

    /**
     * Method to return amount of players.
     * @return amount of players.
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Method to return amount of remedies.
     * @return amount of remedies.
     */
    public int getNumRemedies() {
        return numRemedies;
    }

    /**
     * Method to return amount of zombies.
     * @return amount of zombies.
     */
    public int getNumZombies() {
        return numZombies;
    }

    /**
     * Method to return rounds of sleep for the zombies.
     * @return rounds of sleep for the zombies.
     */
    public int getZombieSleep() {
        return zombieSleep;
    }

    /**
     * Method to return amount of items.
     * @return amount of items.
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * Method to return amount of obstacles.
     * @return amount of obstacles.
     */
    public int getNumObstacles() {
        return numObstacles;
    }

    /**
     * Method to return if the game will have portals.
     * @return true, if game contains portals.
     */
    public boolean hasPortals() {
        return portals;
    }
}
