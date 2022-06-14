/**
 * @name Main
 * @package
 * @file Main.Java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description This file is the start point of the program. For more information about the program: see the README.MD file
 */

//import processing.core.PApplet; // test
import enums.Direction;
import enums.ItemTypes;
import game_elements.*;
import playfield.Board;
import java.awt.Point;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.*;

public class ZombieGame {

    // hier werden Konstanten für die Spielfeldgröße definiert
    public static final int BOARD_HEIGHT = 5;
    public static final int BOARD_WIDTH = 10;

    public static void main(String[] args) throws Exception {

        // Am Anfang wird eine Willkommensnachricht ausgegeben, die dem Spieler erklärt, wie das Spiel funktioniert
        // Board.printWelcomeMessage();

        //Settings settings = new Settings(); // Konstruktor für das Settings-Objekt: hier werden gleichzeitig noch die Settings abgefragt und gesetzt -> siehe Klasse "Settings"
        Settings settings = new Settings(1, 1, 1, 1, 20, 1, false, false, true);
        Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        Scanner sc = new Scanner(System.in);

        List<Survivor> survivors = new ArrayList<>();           // Liste mit Survivors
        List<Zombie> zombies = new ArrayList<>();               // Liste mit Zombies
        List<Remedy> remedies = new ArrayList<>();              // Liste mit Heilmitteln
        List<Exit> exits = new ArrayList<>();                   // Liste mit Ausgängen
        List<Portal> portals = new ArrayList<>();               // Liste mit Portalen
        List<Item> items = new ArrayList<>();                   // Liste mit Items
        List<GameElement> fixedObjects = new ArrayList<>();     // Liste mit allen Objekten, die sich nicht bewegen
        List<GameElement> allGameElements = new ArrayList<>();  // Liste mit allen Game Elementen

        // Initialisierung der Spielelemente und Festlegung der Spawns
        setupGame(allGameElements, fixedObjects, zombies, survivors, exits, remedies, portals, items, settings, board);

        String input; // Variable zum Verarbeiten der User-Eingabe
        boolean isValid = false; // Variabel zum Überprüfen, ob zugelassene Zeichen zum Bewegen etc. eingegeben wurden
        boolean hasWon = false; // Variable zum Überprüfen, ob gewonnen wurde

        // Schleife, solange das Spiel nicht gewonnen oder verloren wurde
        do {
            //drawBoard(BOARD_WIDTH, BOARD_HEIGHT, survivor, zombies, exit, remedies, portals, settings);
            board.drawBoard(fixedObjects, survivors, zombies);
            // Hier wird geprüft, ob ein zugelassenes Zeichen eingegeben wurde -> falls nicht, so lange wiederholen, bis etwas Zugelassenes eingegeben wurde
            do {
                for (Survivor s : survivors) {
                    System.out.println(s.getPlayerName() + ", what is your next move? [w = move up | a = move left | s = move down | d = move right | q = exit | Confirm input with ENTER]");
                    input = sc.nextLine();
                    switch(input) {
                        case "w": {
                            s.move(Direction.UP, board);
                            if (areZombiesAlive(zombies)) {
                                board.increaseScore(10);
                            }
                            isValid = true;
                            break;
                        }
                        case "a": {
                            s.move(Direction.LEFT, board);
                            if (areZombiesAlive(zombies)) {
                                board.increaseScore(10);
                            }
                            isValid = true;
                            break;
                        }
                        case "s": {
                            s.move(Direction.DOWN, board);
                            if (areZombiesAlive(zombies)) {
                                board.increaseScore(10);
                            }
                            isValid = true;
                            break;
                        }
                        case "d": {
                            s.move(Direction.RIGHT, board);
                            if (areZombiesAlive(zombies)) {
                                board.increaseScore(10);
                            }
                            isValid = true;
                            break;
                        }
                        case "q": {
                            System.exit(42);
                            isValid = true;
                            break;
                        }
                        case "e": {
                            // TO-DO usePowerUp
                            isValid = true;
                            break;
                        }
                        default: isValid = false;
                    }
                }
            } while (!isValid);

            for (Remedy r : remedies) {
                for (Survivor s : survivors) {
                    if (r.getLocation().equals(s.getLocation())) {
                        for (Survivor su : survivors) {
                            su.increaseAllPickedRemedies();
                            board.increaseScore(50);
                        }
                        s.increasePickedRemedies();
                        r.setLocation(-1, -1);
                    }
                }
            }

            // Falls Portale aktiviert wurden:
            if (settings.hasPortals) {
                for (Survivor s : survivors) {
                    // Falls die Position des Spielers der Position eines Portales entspricht, wird der Spieler zu der Position des anderen Portals teleportiert.
                    if (s.getLocation().equals(portals.get(0).getLocation()))  {
                        s.setLocation(portals.get(1).getLocation());
                    } else if (s.getLocation().equals(portals.get(1).getLocation())) {
                        s.setLocation(portals.get(0).getLocation());
                    }
                }
            }

            // Einsammeln von Items
            for (Survivor s : survivors) {
                for (Item i : items) {
                    if (i.getLocation().equals(s.getLocation())) {
                        s.setActivatableItem(i);
                        board.setActivatableItem(i);
                        i.setLocation(-1, -1);
                    }
                }
            }

            // Überprüfung, ob der Spieler, alle Heilmittel eingesammelt hat.
            for (Survivor s : survivors) {
                if (s.getAllPickedRemedies() == settings.numRemedies) {
                    s.setHasRemedy(true);
                }
            }

            // Wenn der Sleep-Modus aktiviert wurde, wird der Zombie in einen "Schlafmodus" gesetzt, bis eine bestimmte Anzahl von Schritten erreicht wurde.
            if (settings.hasSleepMode) {
                for (Survivor s : survivors) {
                    if (s.getSteps() >= settings.zombieSleep) {
                        for (Zombie z : zombies) {
                            z.move(survivors);
                        }
                    }
                }
                // ansonsten ist der Zombie direkt von Anfang an wach.
            } else {
                for (Zombie z : zombies) {
                    z.move(survivors);
                }
            }

            // Hier wird die Gewinnbedingung überprüft. Der Spieler muss alle Heilmittel eingesammelt haben UND sich in der Position des Ausgangs befinden.
            for (Survivor s : survivors) {
                for (Exit e : exits) {
                    if (s.getLocation().equals(e.getLocation()) && s.hasRemedy()) {
                        hasWon = true;
                        board.increaseScore(100);
                        board.drawBoard(fixedObjects, survivors, zombies);
                        board.printWinMessage();
                    } else if (s.ateByZombies(zombies)) {
                        board.drawBoard(fixedObjects, survivors, zombies);
                        board.printLoseMessage();
                        System.exit(42);
                    } else if (s.getLocation().equals(e.getLocation()) && !s.hasRemedy()) {
                        System.out.println("Oh no, there's something missing...");
                    }
                }
            }
        } while (!hasWon);
    }

    // Methode zum Aufsetzen eines "gewinnbaren" Spiels
    public static void setupGame(List<GameElement> allElements, List<GameElement> fixedObjects, List<Zombie> zombies,
                                 List<Survivor> survivors, List<Exit> exits, List<Remedy> remedies,
                                 List<Portal> portals, List<Item> items, Settings settings, Board board) {

        do {
            allElements.clear();
            zombies.clear();
            survivors.clear();
            exits.clear();
            items.clear();
            fixedObjects.clear();
            System.out.println("Berechne Spiel...");
            for (int i = 0; i < settings.numZombies; i++) {
                Zombie tmp = new Zombie(zombies, allElements, board);
            }

            for (int i = 0; i < settings.numPlayers; i++) {
                Survivor tmp = new Survivor(survivors, allElements, board, "Player 1");
            }

            for (int i = 0; i < settings.numExits; i++) {
                Exit tmp = new Exit(exits, allElements, fixedObjects, board);
            }

            for (int i = 0; i < settings.numRemedies; i++) {
                Remedy tmp = new Remedy(remedies, allElements, fixedObjects, board);
            }

            if (settings.hasPortals) {
                for (int i = 0; i < 2; i++) {
                    Portal tmp = new Portal(portals, allElements, fixedObjects, board);
                }
            }

            if (settings.numItems > 0) {
                for (int i = 0; i < settings.numItems; i++) {
                    Item tmp = new Item(items, allElements, fixedObjects, board);
                }
            }

        } while(!isWinnableGame(survivors, zombies, exits));
    }

    public static boolean isWinnableGame(List<Survivor> survivors, List<Zombie> zombies, List<Exit> exits) {
        int minDistancePlayer = 0, minDistanceZombie = 0;
        for (Exit e : exits) {
            for (Survivor s : survivors) {
                if (minDistancePlayer == 0 || s.calculateDistanceToExit(e) < minDistancePlayer) {
                    minDistancePlayer = s.calculateDistanceToExit(e);
                }
            }
            for (Zombie z : zombies) {
                if (minDistanceZombie == 0 || z.calculateDistanceToExit(e) < minDistanceZombie) {
                    minDistanceZombie = z.calculateDistanceToExit(e);
                }
            }
        }
        return minDistanceZombie >= minDistancePlayer;
    }

    public static boolean areZombiesAlive(List<Zombie> zombies) {
        for (Zombie z : zombies) {
            if (z.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
