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

    private boolean isAlive = true;     // if the zombie dies (because of an item), it will disappear from the board.
    private int roundsToNextMove = 0;   // rounds until the next move of a zombie.
    private ZombieTypes type;           // different types of zombies exist with different play behaviour

    /**
     * Constructs a Zombie element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements and zombies. <p>
     * Sets roundToNextMove.
     * @param allElements       list of all elements in the game.
     * @param board             instance of the created board.
     * @param zombies           list of all zombies.
     * @param roundsToNextMove  rounds to next move of the zombie.
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

    /**
     * Constructs a Zombie element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements and zombies. <p>
     * Sets roundToNextMove and gets the zombie type as input.
     * @param allElements       list of all elements in the game.
     * @param board             instance of the created board.
     * @param zombies           list of all zombies.
     * @param roundsToNextMove  rounds to next move of the zombie.
     * @param type              type of zombie.
     */
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

    /**
     * Method to set the type of the zombie.
     * @param type  type of zombie.
     */
    public void setType(ZombieTypes type) {
        this.type = type;
    }

    /**
     * Method returns the type of the zombie.
     * @return the type of the zombie.
     */
    public ZombieTypes getType() {
        return type;
    }

    /**
     * Method returns the rounds to the next move.
     * @return rounds to next move.
     */
    public int getRoundsToNextMove() {
        return roundsToNextMove;
    }

    /**
     * Method sets the number of rounds until the zombie is able to move again.
     * @param roundsToNextMove  number of rounds the zombie is able to move.
     */
    public void setRoundsToNextMove(int roundsToNextMove) {
        this.roundsToNextMove = roundsToNextMove;
    }

    /**
     * Method to return if a zombie is alive
     * @return true, if zombie is still alive.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Method decreases the rounds until the zombie is able to move.
     */
    public void decreaseRoundsToNextMove() {
        this.roundsToNextMove--;
    }

    /**
     * @param survivor      instance of the survivor.
     * @return the amount of fields to reach the survivor.
     */
    public int distanceToSurvivor(Survivor survivor) {
        return (int) Math.max(Math.abs(this.getX() - survivor.getX()), Math.abs(this.getY() - survivor.getY()));
    }

    /**
     * distanceToSurvivor() calculates the distance to the survivor depending on the direction (x or y).
     * @param survivor      object of the survivor.
     * @param direction     x: calculates distance in x-direction, y: calculates distance in y-direction.
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
     * Method checks if the zombie could move to the location of a fixedObject
     * @param x             x-location of the wanted move.
     * @param y             y-location of the wanted move.
     * @param fixedObjects  list of all fixed objects.
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
     * @param survivors     list of all survivors.
     * @param fixedObjects  list of all fixed objects.
     * @throws Exception    message if something went wrong.
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
                // movement for the normal zombie
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
                // movement for the jumper zombie
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

    /**
     * Method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
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

    /**
     * Method to return the GameElement type.
     * @return the GameElement type.
     */
    @Override
    public GameElements toGameBoard() {
        return GameElements.ZOMBIE;
    }

    /**
     * This method calculates the distance to the given exits location.
     * @param exit  instance of the nearest exit.
     * @return distance to the given exit.
     */
    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) Math.max(Math.abs(exit.getX() - this.getX()), Math.abs(exit.getY() - this.getY()));
    }
}
