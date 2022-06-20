/**
 * @name ZGame
 * @package game
 * @file ZGame.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description this class contains the game logic.
 */

package game;

import enums.Difficulties;
import enums.Direction;
import exceptions.UnsupportedInput;
import game_elements.*;
import playfield.Board;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ZGame {

    private Settings settings;                                              // settings object
    private final Board board;                                              // board object
    private final List<Survivor> survivors = new ArrayList<>();             // survivors list
    private final List<Zombie> zombies = new ArrayList<>();                 // zombies list
    private final List<Remedy> remedies = new ArrayList<>();                // remedies list
    private final List<Exit> exits = new ArrayList<>();                     // exits list
    private final List<Portal> portals = new ArrayList<>();                 // portals list
    private final List<Item> items = new ArrayList<>();                     // items list
    private final List<Obstacle> obstacles = new ArrayList<>();             // obstacles list
    private final List<GameElement> fixedObjects = new ArrayList<>();       // list with all game objects that are non-movable.
    private final List<GameElement> allGameElements = new ArrayList<>();    // list with all existing elements on the game.
    private boolean hasWon = false;                                         // variable to track if the game is won.
    private boolean hasLost = false;                                        // variable to track if the game is lost.

    /**
     * Constructs a zombie game with a board as the parameter.
     * @param board     instance ob the board object.
     */
    public ZGame(Board board) {
        this.board = board;
    }

    /**
     * returns a list of all zombies in the game.
     * @return list of all zombies in the game.
     */
    public List<Zombie> getZombies() {
        return zombies;
    }

    /**
     * returns a list of all survivors in the game.
     * @return list of all survivors in the game.
     */
    public List<Survivor> getSurvivors() {
        return survivors;
    }

    /**
     * returns a list of all fixed objects in the game.
     * @return list of all fixed objects in the game.
     */
    public List<GameElement> getFixedObjects() {
        return fixedObjects;
    }

    /**
     * returns a list of all obstacles in the game.
     * @return list of all obstacles in the game.
     */
    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     *  method to check if the game is won or still running.
     * @return true, if the game is won.
     */
    public boolean isHasWon() {
        return hasWon;
    }

    /**
     *  method to check if the game is lost or still running.
     * @return true, if the game is lost.
     */
    public boolean isHasLost() {
        return hasLost;
    }

    /**
     * method asks the user for the difficulty input when called.
     */
    public void adjustGame() {
        Scanner sc = new Scanner(System.in);
        board.printWelcomeMessage();
        System.out.println("Press [1] to continue");
        String input = sc.nextLine();
        if (!Objects.equals(input, "1")) {
            throw new UnsupportedInput("Wrong input, please enter '1' next time.");
        }
        board.printAdjustSettings();
        input = sc.nextLine();
        switch(input) {
            case "1" -> {
                this.settings = new Settings(Difficulties.EASY);
            }
            case "2" -> {
                this.settings = new Settings(Difficulties.MEDIUM);
            }
            case "3" -> {
                this.settings = new Settings(Difficulties.HARD);
            }
            case "4" -> {
                this.settings = new Settings();
            }
            default -> {
                throw new UnsupportedInput("Wrong input. Program is going to shutdown. cya");
            }
        }
    }

    /**
     * method sets the difficulty setting to the received argument.
     * @param difficulty 1 = easy, 2 = medium, 3 = hard
     */
    public void adjustGame(int difficulty) {
        switch(difficulty) {
            case 1 -> {
                this.settings = new Settings(Difficulties.EASY);
            }
            case 2 -> {
                this.settings = new Settings(Difficulties.MEDIUM);
            }
            case 3 -> {
                this.settings = new Settings(Difficulties.HARD);
            }
            case 4 -> {
                this.settings = new Settings();
            }
            default -> {
                throw new UnsupportedInput("Wrong input. Program is going to shutdown. cya");
            }
        }
    }

    /**
     * method creates a winnable game-board.
     */
    public void setupGame() {
        do {
            // clear list of GameElements for every iteration.
            this.allGameElements.clear();
            this.zombies.clear();
            this.survivors.clear();
            this.exits.clear();
            this.items.clear();
            this.fixedObjects.clear();
            this.portals.clear();
            this.obstacles.clear();

            // try new point positions as long as the game is not winnable.
            for (int i = 0; i < this.settings.getNumZombies(); i++) {
                Zombie tmp = new Zombie(this.zombies, this.allGameElements, this.board, this.settings.getZombieSleep());
            }
            for (int i = 0; i < this.settings.getNumPlayers(); i++) {
                Survivor tmp = new Survivor(this.survivors, this.allGameElements, this.board, "Player 1");
            }
            for (int i = 0; i < this.settings.getNumExits(); i++) {
                Exit tmp = new Exit(this.exits, this.allGameElements, this.fixedObjects, this.board);
            }
            for (int i = 0; i < this.settings.getNumRemedies(); i++) {
                Remedy tmp = new Remedy(this.remedies, this.allGameElements, this.fixedObjects, this.board);
            }
            if (this.settings.hasPortals()) {
                for (int i = 0; i < 2; i++) {
                    Portal tmp = new Portal(this.portals, this.allGameElements, this.fixedObjects, this.board);
                }
            }
            if (this.settings.getNumItems() > 0) {
                for (int i = 0; i < this.settings.getNumItems(); i++) {
                    Item tmp = new Item(this.items, this.allGameElements, this.fixedObjects, this.board);
                }
            }
            if (this.settings.getNumObstacles() > 0) {
                for (int i = 0; i < this.settings.getNumObstacles(); i++) {
                    Obstacle tmp = new Obstacle(this.obstacles, this.allGameElements, this.fixedObjects, this.board);
                }
            }
        } while(!isWinnableGame());
    }

    /**
     * method compares the amount of turns the player and zombie needs to reach the exit.
     * method doesn't check possible use of items and remedy spawns.
     * @return true, if the game is winnable for the user
     */
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

    /**
     * checks if at least one zombie is live.
     * @return true if at least one zombie is alive.
     */
    private boolean areZombiesAlive() {
        for (Zombie z : this.zombies) {
            if (z.isAlive()) {
                return true;
            }
        }
        return false;
    }

    /**
     * increases the score for every new round. <br>
     * checks the locations for every gameElement and will do something (explained in the methods) if positions are equal. <br>
     * checks if the game has been won or lost.
     * @throws Exception (if an error happened)
     */
    public void nextRound() throws Exception {
        board.setMessage("");
        board.decreaseActiveRounds();
        if (this.areZombiesAlive()) {
            this.board.increaseScore(10);
        }

        for (Survivor s : this.survivors) {
            // checks remedy position and increases remedy counter if survivor and remedy positions are equal.
            // also increases  the score by 50
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
            // checks portal position and ports the survivor if they are on the same position.
            if (this.settings.hasPortals()) {
                for (Portal p : this.portals) {
                    if (s.getLocation().equals(p.getLocation())) {
                        p.teleport(s, this.portals);
                        break;
                    }
                }
            }
            // checks item position and survivor position.
            // if they are equal -> increase score by 25 and give the survivor the item as an collectible.
            for (Item i : this.items) {
                if (i.getLocation().equals(s.getLocation())) {
                    s.setActivatableItem(i);
                    this.board.setActivatableItem(i);
                    this.board.increaseScore(25);
                    i.setLocation(-1, -1);
                }
            }
            // checks if all remedies were picked up.
            if (s.getAllPickedRemedies() == this.settings.getNumRemedies()) {
                s.setHasRemedy(true);
            }
        }

        // checks if zombies can move (for example if they are in a sleep mode)
        for (Zombie z : this.zombies) {
            if (z.getRoundsToNextMove() > 0) {
                z.decreaseRoundsToNextMove();
            } else {
                z.move(this.survivors, this.fixedObjects);
            }
        }

        // checks the winning requirements and increases the score.
        for (Survivor s : this.survivors) {
            for (Exit e : this.exits) {
                if (s.getLocation().equals(e.getLocation()) && s.hasRemedy()) {
                    this.hasWon = true;
                    this.board.increaseScore(100);
                } else if (s.ateByZombies(this.zombies)) {
                    this.hasLost = true;
                } else if (s.getLocation().equals(e.getLocation()) && !s.hasRemedy()) {
                    board.setMessage("Oh no, there is something missing");
                }
            }
        }
    }

    /**
     * startGame() displays the board with its GameElements. <br>
     * Also takes input for moving the characters etc.
     */
    public void startGame() {
        Scanner sc = new Scanner(System.in);
        String input;                           // is used for processing user input
        boolean isValid = false;                // if the user input is valid, this variable will change to true.

        try {
            do {
                this.board.drawBoard(this.fixedObjects, this.survivors, this.zombies, true);
                // Here it is checked whether a permitted character has been entered -> if not, repeat until something permitted has been entered.
                do {
                    // movement:
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
                                    this.board.drawBoard(this.fixedObjects, this.survivors, this.zombies, false);
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

                    if (this.settings.hasPortals()) {
                        for (Portal p : this.portals) {
                            if (s.getLocation().equals(p.getLocation())) {
                                p.teleport(s, this.portals);
                                break;
                            }
                        }
                    }

                    for (Item i : this.items) {
                        if (i.getLocation().equals(s.getLocation())) {
                            s.setActivatableItem(i);
                            this.board.setActivatableItem(i);
                            this.board.increaseScore(25);
                            i.setLocation(-1, -1);
                        }
                    }

                    if (s.getAllPickedRemedies() == this.settings.getNumRemedies()) {
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

                for (Survivor s : this.survivors) {
                    for (Exit e : this.exits) {
                        if (s.getLocation().equals(e.getLocation()) && s.hasRemedy()) {
                            this.hasWon = true;
                            this.board.increaseScore(100);
                            this.board.drawBoard(fixedObjects, survivors, zombies, true);
                            this.board.printWinMessage();
                        } else if (s.ateByZombies(this.zombies)) {
                            this.board.drawBoard(this.fixedObjects, this.survivors, this.zombies, true);
                            this.board.printLoseMessage();
                            System.exit(42);
                        } else if (s.getLocation().equals(e.getLocation()) && !s.hasRemedy()) {
                            System.out.println("Oh no, there's something missing...");
                        }
                    }
                }
            } while (!this.hasWon);
        } catch (Exception e) {
            System.err.println("for some reason, an error happened. Please inform us, how to reproduce it.");
        }
    }
}
