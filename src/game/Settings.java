/**
 * @name Settings
 * @package game
 * @file Settings.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description this class contains the settings of the game.
 */

package game;

import enums.Difficulties;
import java.util.Scanner;

public class Settings {

    private int numExits;
    private int numPlayers;
    private int numRemedies;
    private int numZombies;
    private int zombieSleep = 0;
    private int numItems;
    private int numObstacles;
    private final boolean hasCustomSettings;
    private boolean portals;

    // constructors for difficulty modes (easy, medium, hard and custom):
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


    // getter methods
    public int getNumExits() {
        return numExits;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getNumRemedies() {
        return numRemedies;
    }

    public int getNumZombies() {
        return numZombies;
    }

    public int getZombieSleep() {
        return zombieSleep;
    }

    public int getNumItems() {
        return numItems;
    }

    public int getNumObstacles() {
        return numObstacles;
    }

    public boolean hasPortals() {
        return portals;
    }
}
