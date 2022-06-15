package Game;

import java.util.Scanner;

// In dieser Klasse werden die Einstellung (settings) für das Spiel angepasst.

public class Settings {
    public int numExits;
    public int numPlayers;
    public int numRemedies;
    public int numZombies;
    public int zombieSleep = 0;
    public int numItems;
    public int numObstacles;
    public boolean hasCustomSettings;
    public boolean hasPortals;
    public boolean hasSleepMode = false;

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
        this.hasPortals = hasPortals;
        if (zombieSleep > 0) {
            this.hasSleepMode = true;
        }
    }

    // Konstruktor + fragt den Benutzer zusätzlich nach den Einstellungen.
    public Settings() {
        Scanner sc = new Scanner(System.in);
        boolean isValid = true;
        int inputInt;
        this.hasCustomSettings = true;
        try {
            // Falls "[2] Custom mode" ausgewählt wurde, geht er die kommenden Game-Settings-Abfragen durch
            if (this.hasCustomSettings) {
                // Abfrage zu Heilmitteln
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many remedies does the player need? (1-5)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // Einstellung für Remedies
                    isValid = (inputInt > 0 && inputInt <= 5);
                    if (isValid) {
                        this.numRemedies = inputInt;
                    }
                } while (!isValid);

                // Abfrage zu Zombies
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many zombies do you want to escape? (1-5)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // Einstellung für Zombies
                    isValid = (inputInt > 0 && inputInt <= 5);
                    if (isValid) {
                        this.numZombies = inputInt;
                    }
                } while (!isValid);

                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many items should be on the board? (0-10)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // Einstellung für Items
                    isValid = (inputInt >= 0 && inputInt <= 10);
                    if (isValid) {
                        this.numItems = inputInt;
                    }
                } while (!isValid);

                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How many obstacles should be on the board? (0-25)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // Einstellung für Hindernisse
                    isValid = (inputInt >= 0 && inputInt <= 25);
                    if (isValid) {
                        this.numObstacles = inputInt;
                    }
                } while (!isValid);

                // Abfrage zu Portalen
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("Do you want to enable portals?");
                    System.out.println("[1] No portals");
                    System.out.println("[2] portals");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // Einstellung für Portale
                    isValid = (inputInt == 1 || inputInt == 2);
                    if (isValid && inputInt == 2) {
                        this.hasPortals = true;
                    }
                } while (!isValid);

                // Abfrage zum "Sleep-Modus"
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    // Einstellung für den Zombie-Sleep-Modus
                    System.out.println("Should the zombies sleep for certain turns? (0-99 Turns)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    isValid = (inputInt >= 0 && inputInt <= 99);
                    if (isValid) {
                        this.zombieSleep = inputInt;
                        this.hasSleepMode = true;
                    }
                } while (!isValid);
            }
        } catch (Exception e) {
            System.err.println("Wrong input, program ends here!");
            System.exit(2);
        }
    }
}
