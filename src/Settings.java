import java.util.Scanner;

// In dieser Klasse werden die Einstellung (settings) für das Spiel angepasst.

public class Settings {
    public int numExits;
    public int numPlayers;
    public int numRemedies;
    public int numZombies;
    public int zombieSleep = 0;
    public int numItems;
    public boolean hasCustomSettings;
    public boolean hasPortals;
    public boolean hasSleepMode;

    public Settings(int numExits, int numPlayers, int numRemedies, int numZombies, int zombieSleep, int numItems,
                    boolean hasCustomSettings, boolean hasPortals, boolean hasSleepMode) {
        this.numExits = numExits;
        this.numPlayers = numPlayers;
        this.numRemedies = numRemedies;
        this.numZombies = numZombies;
        this.zombieSleep = zombieSleep;
        this.numItems = numItems;
        this.hasCustomSettings = hasCustomSettings;
        this.hasPortals = hasPortals;
        this.hasSleepMode = hasSleepMode;
    }

    // Konstruktor + fragt den Benutzer zusätzlich nach den Einstellungen.
    public Settings() {
        Scanner sc = new Scanner(System.in);
        boolean isValid = true;
        int inputInt;
        this.hasCustomSettings = true;
        try {
            do {
                // bei einer falschen Eingabe wird folgende Meldung ausgegeben:
                if (!isValid) {
                    System.out.println("Wrong input! Go again!");
                }
                System.out.println("How do you wanna play?");
                System.out.println("[1] easy-peasy-lemons-squeezy mode");
                System.out.println("[2] Custom mode");
                inputInt = sc.nextInt();
                sc.nextLine();
                isValid = (inputInt == 1 || inputInt == 2);
                //  Falls der User "1" in die Konsole eingibt, werden folgende Einstellungen für den easy Mode angepasst.
                if (isValid && inputInt == 1) {
                    this.numRemedies = 1;
                    this.numZombies = 1;
                    this.zombieSleep = 0;
                    this.hasPortals = false;
                    this.hasCustomSettings = false;
                }
            } while (!isValid); // while-Schleife bis zur Eingabe eines richtigen Wertes

            // Falls "[2] Custom mode" ausgewählt wurde, geht er die kommenden Settings-Abfragen durch
            if (this.hasCustomSettings) {
                // Abfrage zu Spawns
                do {
                    if (!isValid) {
                        System.out.println("Wrong input! Go again!");
                    }
                    System.out.println("How do you wanna play?");
                    System.out.println("[1] Fixed spawns");
                    System.out.println("[2] Random spawns");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    // Einstellung für Spawns.
                    isValid = (inputInt == 1 || inputInt == 2);
                    if (isValid && inputInt == 2) {
                        // this.hasRandomSpawns = true;
                    }
                } while (!isValid);

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
                    System.out.println("Should the zombies sleep for certain turns? (0-5 Turns)");
                    inputInt = sc.nextInt();
                    sc.nextLine();
                    isValid = (inputInt >= 0 && inputInt <= 5);
                    if (isValid && inputInt > 0) {
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
