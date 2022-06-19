/**
 * @package game_elements
 * @file Zombie.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Zombie Class is represented as a point which is used by the AI.
 */

package game_elements;

import enums.GameElements;
import enums.ZombieTypes;
import playfield.Board;
import java.util.List;

public class Zombie extends GameCharacter {

    private boolean isAlive = true; // if the zombies dies (because of an item), it will disappear from the board.
    private int roundsToNextMove = 0;
    private ZombieTypes type; // different types of zombies exist with different play behaviour

    /**
     * Zombie Constructor. Adds the object Zombie to the Zombie list.
     * <p>
     * Also changes the setting for a zombie depending on its type.
     * @param zombies
     * @param allElements
     * @param board
     * @param roundsToNextMove
     */
    public Zombie(List<Zombie> zombies, List<GameElement> allElements, Board board, int roundsToNextMove) {
        super(allElements, board);
        zombies.add(this);
        this.setType(ZombieTypes.randomItemType());
        if (this.type == ZombieTypes.JUMPER && roundsToNextMove == 0) {
            this.setRoundsToNextMove(1);
        } else {
            this.setRoundsToNextMove(roundsToNextMove);
        }
    }

    public Zombie(List<Zombie> zombies, List<GameElement> allElements, Board board, int roundsToNextMove, ZombieTypes type) {
        super(allElements, board);
        zombies.add(this);
        this.setType(type);
        if (this.type == ZombieTypes.JUMPER && roundsToNextMove == 0) {
            this.setRoundsToNextMove(1);
        } else {
            this.setRoundsToNextMove(roundsToNextMove);
        }
    }

    // getter and setter methods:
    public void setType(ZombieTypes type) {
        this.type = type;
    }

    public ZombieTypes getType() {
        return type;
    }

    public int getRoundsToNextMove() {
        return roundsToNextMove;
    }

    public void setRoundsToNextMove(int roundsToNextMove) {
        this.roundsToNextMove = roundsToNextMove;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void decreaseRoundsToNextMove() {
        this.roundsToNextMove--;
    }

    /**
     * @param survivor
     * @return the amount of turns needed to reach the survivor.
     */
    public int distanceToSurvivor(Survivor survivor) {
        return (int) Math.max(Math.abs(this.getX() - survivor.getX()), Math.abs(this.getY() - survivor.getY()));
    }

    /**
     * distanceToSurvivor() calculates the distance to the survivor
     * @param survivor
     * @param direction
     * @return an integer represented with the amount of turns needed to reach the survivor.
     */
    public int distanceToSurvivor(Survivor survivor, String direction) {
        switch(direction) {
            case "x" -> {
                return (int) Math.abs(this.getX() - survivor.getX());
            }
            case "y" -> {
                return (int) Math.abs(this.getY() - survivor.getY());
            }
            default -> {
                return 0;
            }
        }
    }

    /**
     * The zombie is not allowed to collect GameObjects.
     * <p>
     * This method checks if the zombie's position is the same as the one from a GameObject.
     * @param x
     * @param y
     * @param fixedObjects
     * @return
     */

    /**
     * isValidMove() checks if the zombie could move to the location of a fixedObject
     * @param x
     * @param y
     * @param fixedObjects
     * @return true if positions are not the same
     */
    private boolean isValidMove(int x, int y, List<GameElement> fixedObjects) {
        for (GameElement e : fixedObjects) {
            if (e.getX() == x && e.getY() == y) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method moves the zombie. <p>
     * It also checks the type of the zombie and adjusts the movement pattern.
     * @param survivors
     * @param fixedObjects
     * @throws Exception message if something went wrong.
     */
    public void move(List<Survivor> survivors, List<GameElement> fixedObjects) throws Exception{
        Survivor nearestSurvivor = null;
        int distance = 0, x, y;
        try {
            // find the nearest Survivor
            for (Survivor s : survivors) {
                if (distance == 0 || this.distanceToSurvivor(s) < distance) {
                    distance = this.distanceToSurvivor(s);
                    nearestSurvivor = s;
                }
            }
            x = (int) this.getX();
            y = (int) this.getY();

            switch (this.type) {
                case NORMAL -> {
                    int numPossibilities = 1;
                    if (this.distanceToSurvivor(nearestSurvivor, "x") > 0 && this.distanceToSurvivor(nearestSurvivor, "y") > 0) {
                        numPossibilities = 3;
                    }
                    // Movement in horizontal direction
                    assert nearestSurvivor != null; //
                    if (x < nearestSurvivor.getX()) {
                        x++;
                    } else if (x > nearestSurvivor.getX()) {
                        x--;
                    }
                    // Movement in vertical direction
                    if (y < nearestSurvivor.getY()) {
                        y++;
                    } else if (y > nearestSurvivor.getY()) {
                        y--;
                    }
                    if (isValidMove(x, y, fixedObjects)) {
                        this.setLocation(x, y);
                    } else if (numPossibilities > 1) {
                        if (isValidMove(x, (int) this.getY(), fixedObjects)) {
                            this.setLocation(x, (int) this.getY());
                        } else {
                            if (isValidMove((int) this.getX(), y, fixedObjects)) {
                                this.setLocation((int) this.getX(), y);
                            }
                        }
                    }
                }
                case JUMPER -> {
                    assert nearestSurvivor != null;
                    int deltaX = (int) (nearestSurvivor.getX() - this.getX());
                    int deltaY = (int) (nearestSurvivor.getY() - this.getY());

                    do {
                        if (Math.abs(deltaX) > Math.abs(deltaY)) {
                            if (deltaX < 0) {
                                if (isValidMove((int) this.getX() + Math.max(-3, deltaX), (int) this.getY(), fixedObjects)) {
                                    this.translate(Math.max(-3, deltaX), 0);
                                    break;
                                } else {
                                    deltaX++;
                                }
                            } else {
                                if (isValidMove((int) this.getX() + Math.min(3, deltaX), (int) this.getY(), fixedObjects)) {
                                    this.translate(Math.min(3, deltaX), 0);
                                    break;
                                } else {
                                    deltaX--;
                                }
                            }
                        } else {
                            if (deltaY < 0) {
                                if (isValidMove((int) this.getX(), (int) this.getY() + Math.max(-3, deltaY), fixedObjects)) {
                                    this.translate(0, Math.max(-3, deltaY));
                                    break;
                                } else {
                                    deltaY++;
                                }
                            } else {
                                if (isValidMove((int) this.getX(), (int) this.getY() + Math.min(3, deltaY), fixedObjects)) {
                                    this.translate(0, Math.min(3, deltaY));
                                    break;
                                } else {
                                    deltaY--;
                                }
                            }
                        }
                    } while(deltaX != 0 || deltaY != 0);
                    this.roundsToNextMove = 1;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toBoard() {
        switch(this.type) {
            case NORMAL -> {
                return "Z";
            }
            case JUMPER -> {
                return "ZJ";
            }
            default -> {
                return "";
            }
        }
    }

    @Override
    public GameElements toGameBoard() {
        return GameElements.ZOMBIE;
    }

    /**
     *
     * @param exit
     * @return the amount of needed rounds for reaching the exit.
     */
    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) Math.max(Math.abs(exit.getX() - this.getX()), Math.abs(exit.getY() - this.getY()));
    }
}
