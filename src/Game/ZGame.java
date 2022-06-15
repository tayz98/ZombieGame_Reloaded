package Game;

import enums.Direction;
import game_elements.*;
import playfield.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ZGame {

    private Settings settings;                                      // Einstellungen
    private Board board;                                            // Spielfeld
    private List<Survivor> survivors = new ArrayList<>();           // Liste mit Survivors
    private List<Zombie> zombies = new ArrayList<>();               // Liste mit Zombies
    private List<Remedy> remedies = new ArrayList<>();              // Liste mit Heilmitteln
    private List<Exit> exits = new ArrayList<>();                   // Liste mit Ausgängen
    private List<Portal> portals = new ArrayList<>();               // Liste mit Portalen
    private List<Item> items = new ArrayList<>();                   // Liste mit Items
    private List<Obstacle> obstacles = new ArrayList<>();           // Liste mit Hindernissen
    private List<GameElement> fixedObjects = new ArrayList<>();     // Liste mit allen Objekten, die sich nicht bewegen
    private List<GameElement> allGameElements = new ArrayList<>();  // Liste mit allen Game Elementen

    public ZGame(Board board) {
        this.board = board;
    }

    public void adjustGame() {
        Scanner sc = new Scanner(System.in);
        board.printWelcomeMessage();
        System.out.println("Press [1] to continue");
        String input = sc.nextLine();
        if (!Objects.equals(input, "1")) {
            System.exit(42);
        }
        board.printAdjustSettings();
        input = sc.nextLine();
        switch(input) {
            case "1" -> {
                this.settings = new Settings(1, 1, 1, 1, 0, 1, 5, false, true);
            }
            case "2" -> {
                this.settings = new Settings(1, 1, 2, 2, 5, 2, 10, false, true);
            }
            case "3" -> {
                this.settings = new Settings(1, 1, 3, 3, 10, 3, 15, false, true);
            }
            case "4" -> {
                this.settings = new Settings();
            }
            default -> {
                System.exit(42);
            }
        }
    }

    public void setupGame() {
        do {
            this.allGameElements.clear();
            this.zombies.clear();
            this.survivors.clear();
            this.exits.clear();
            this.items.clear();
            this.fixedObjects.clear();
            this.portals.clear();
            this.obstacles.clear();
            for (int i = 0; i < this.settings.numZombies; i++) {
                Zombie tmp = new Zombie(this.zombies, this.allGameElements, this.board, this.settings.zombieSleep);
            }

            for (int i = 0; i < this.settings.numPlayers; i++) {
                Survivor tmp = new Survivor(this.survivors, this.allGameElements, this.board, "Player 1");
            }

            for (int i = 0; i < this.settings.numExits; i++) {
                Exit tmp = new Exit(this.exits, this.allGameElements, this.fixedObjects, this.board);
            }

            for (int i = 0; i < this.settings.numRemedies; i++) {
                Remedy tmp = new Remedy(this.remedies, this.allGameElements, this.fixedObjects, this.board);
            }

            if (this.settings.hasPortals) {
                for (int i = 0; i < 2; i++) {
                    Portal tmp = new Portal(this.portals, this.allGameElements, this.fixedObjects, this.board);
                }
            }

            if (this.settings.numItems > 0) {
                for (int i = 0; i < this.settings.numItems; i++) {
                    Item tmp = new Item(this.items, this.allGameElements, this.fixedObjects, this.board);
                }
            }

            if (this.settings.numObstacles > 0) {
                for (int i = 0; i < this.settings.numObstacles; i++) {
                    Obstacle tmp = new Obstacle(this.obstacles, this.allGameElements, this.fixedObjects, this.board);
                }
            }

        } while(!isWinnableGame());
        System.out.println("Game created!");
    }

    private boolean isWinnableGame() {
        int minDistancePlayer = 0, minDistanceZombie = 0;
        for (Exit e : this.exits) {
            for (Survivor s : this.survivors) {
                if (minDistancePlayer == 0 || s.calculateDistanceToExit(e) < minDistancePlayer) {
                    minDistancePlayer = s.calculateDistanceToExit(e);
                }
            }
            for (Zombie z : this.zombies) {
                if (minDistanceZombie == 0 || z.calculateDistanceToExit(e) < minDistanceZombie) {
                    minDistanceZombie = z.calculateDistanceToExit(e);
                }
            }
        }
        return minDistanceZombie >= minDistancePlayer;
    }

    private boolean areZombiesAlive() {
        for (Zombie z : this.zombies) {
            if (z.isAlive()) {
                return true;
            }
        }
        return false;
    }

    public void startGame() throws Exception {
        Scanner sc = new Scanner(System.in);
        String input; // Variable zum Verarbeiten der User-Eingabe
        boolean isValid = false; // Variabel zum Überprüfen, ob zugelassene Zeichen zum Bewegen etc. eingegeben wurden
        boolean hasWon = false; // Variable zum Überprüfen, ob gewonnen wurde

        try {


            do {
                //drawBoard(BOARD_WIDTH, BOARD_HEIGHT, survivor, zombies, exit, remedies, portals, settings);
                this.board.drawBoard(this.fixedObjects, this.survivors, this.zombies);
                // Hier wird geprüft, ob ein zugelassenes Zeichen eingegeben wurde -> falls nicht, so lange wiederholen, bis etwas Zugelassenes eingegeben wurde
                do {
                    isValid = false;
                    for (Survivor s : this.survivors) {
                        System.out.println(s.getPlayerName() + ", what is your next move? [w = move up | a = move left | s = move down | d = move right | q = exit | Confirm input with ENTER]");
                        input = sc.nextLine();
                        switch (input) {
                            case "w": {
                                if (s.isValidMove(0, -1, board, this.obstacles)) {
                                    s.move(Direction.UP, this.board);
                                    if (this.areZombiesAlive()) {
                                        this.board.increaseScore(10);
                                    }
                                    isValid = true;
                                } else {
                                    System.out.println("Invalid move...");
                                }
                                break;
                            }
                            case "a": {
                                if (s.isValidMove(-1, 0, board, this.obstacles)) {
                                    s.move(Direction.LEFT, this.board);
                                    if (this.areZombiesAlive()) {
                                        this.board.increaseScore(10);
                                    }
                                    isValid = true;
                                } else {
                                    System.out.println("Invalid move...");
                                }
                                break;
                            }
                            case "s": {
                                if (s.isValidMove(0, 1, board, this.obstacles)) {
                                    s.move(Direction.DOWN, this.board);
                                    if (this.areZombiesAlive()) {
                                        this.board.increaseScore(10);
                                    }
                                    isValid = true;
                                } else {
                                    System.out.println("Invalid move...");
                                }
                                break;
                            }
                            case "d": {
                                if (s.isValidMove(1, 0, board, this.obstacles)) {
                                    s.move(Direction.RIGHT, this.board);
                                    if (this.areZombiesAlive()) {
                                        this.board.increaseScore(10);
                                    }
                                    isValid = true;
                                } else {
                                    System.out.println("Invalid move...");
                                }
                                break;
                            }
                            case "q": {
                                System.exit(42);
                                isValid = true;
                                break;
                            }
                            case "e": {
                                if (s.activatePowerUp()) {
                                    this.board.activatePowerUp();
                                    isValid = false;
                                } else {
                                    System.out.println("There is no item...");
                                    isValid = false;
                                }
                                break;
                            }
                            default:
                                isValid = false;
                        }
                    }
                } while (!isValid);

                for (Survivor s : this.survivors) {
                    // Heilmittel
                    for (Remedy r : this.remedies) {
                        if (r.getLocation().equals(s.getLocation())) {
                            for (Survivor su : this.survivors) {
                                su.increaseAllPickedRemedies();
                                this.board.increaseScore(50);
                            }
                            s.increasePickedRemedies();
                            r.setLocation(-1, -1);
                        }
                    }
                    // Portale
                    if (this.settings.hasPortals) {
                        for (Portal p : this.portals) {
                            if (s.getLocation().equals(p.getLocation())) {
                                p.teleport(s, this.portals);
                                break;
                            }
                        }
                    }
                    // Items
                    for (Item i : this.items) {
                        if (i.getLocation().equals(s.getLocation())) {
                            s.setActivatableItem(i);
                            this.board.setActivatableItem(i);
                            this.board.increaseScore(25);
                            i.setLocation(-1, -1);
                        }
                    }
                    // Überprüfung, ob alle Heilmittel eingesammelt wurden
                    if (s.getAllPickedRemedies() == this.settings.numRemedies) {
                        s.setHasRemedy(true);
                    }
                }

                for (Zombie z : this.zombies) {
                    if (z.getRoundsToNextMove() > 0) {
                        z.decreaseRoundsToNextMove();
                    } else {
                        z.move(this.survivors, this.fixedObjects);
                    }
                }

                // Hier wird die Gewinnbedingung überprüft. Der Spieler muss alle Heilmittel eingesammelt haben UND sich in der Position des Ausgangs befinden.
                for (Survivor s : this.survivors) {
                    for (Exit e : this.exits) {
                        if (s.getLocation().equals(e.getLocation()) && s.hasRemedy()) {
                            hasWon = true;
                            this.board.increaseScore(100);
                            this.board.drawBoard(fixedObjects, survivors, zombies);
                            this.board.printWinMessage();
                        } else if (s.ateByZombies(this.zombies)) {
                            this.board.drawBoard(this.fixedObjects, this.survivors, this.zombies);
                            this.board.printLoseMessage();
                            System.exit(42);
                        } else if (s.getLocation().equals(e.getLocation()) && !s.hasRemedy()) {
                            System.out.println("Oh no, there's something missing...");
                        }
                    }
                }
            } while (!hasWon);
        } catch (Exception e) {

        }
    }
}
