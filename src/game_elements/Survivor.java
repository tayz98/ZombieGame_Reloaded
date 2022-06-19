/**
 * @package game_elements
 * @file Survivor.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Survivor Class is represented as a point which is used by the player.
 */

package game_elements;

import enums.Direction;
import enums.GameElements;
import playfield.Board;
import java.util.List;

public class Survivor extends GameCharacter {

    private int amountOfPickedRemedies;
    private int allPickedRemedies;
    private int speed = 1;
    private int steps = 0;
    private int roundsActive = 0;           // number of rounds the item will be active.
    private Item activatableItem;           // collected item which is able to activate
    private boolean hasRemedy;              // checks if the survivor picked all remedies.
    private String playerName;

    /**
     * Constructs a Survivor element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements and survivors. <p>
     * Sets the players name.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     * @param survivors     list of all survivors.
     * @param playerName    name of player.
     */
    public Survivor(List<Survivor> survivors, List<GameElement> allElements, Board board, String playerName) {
        super(allElements, board);
        survivors.add(this);
        this.playerName = playerName;
    }

    /**
     * Method to set the activatable item after one is picked up.
     * @param activatableItem   item that is picked up.
     */
    public void setActivatableItem(Item activatableItem) {
        this.activatableItem = activatableItem;
    }

    /**
     * returns the number of rounds that an item will be active.
     * @return number of rounds that an item will be active.
     */
    public int getRoundsActive() {
        return roundsActive;
    }

    /**
     * returns the speed for the survivor.
     * @return the speed that the survivor has.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Method to set up the survivors speed.
     * @param speed speed-rate (1 = normal, 2 = flash item activated)..
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Method to set number of rounds that an item will be active.
     * @param roundsActive  number of rounds that the item will be active.
     */
    public void setRoundsActive(int roundsActive) {
        this.roundsActive = roundsActive;
    }

    /**
     * Method to decrease the rounds by one that an item is still active.
     */
    public void decreaseRoundsActive() {
        if (this.roundsActive > 0) {
            this.roundsActive--;
        }
    }

    /**
     * Method to decrease Speed if item "flash" is used.
     */
    public void decreaseSpeedIfNeeded() {
        if (this.getSpeed() > 1 && this.getRoundsActive() == 0) {
            this.setSpeed(1);
        }
    }

    /**
     * returns players name.
     * @return the players name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Method increases picked up remedies for this survivor.
     */
    public void increasePickedRemedies() {
        this.amountOfPickedRemedies++;
    }

    /**
     * Method increases all picked up remedies by all survivors by one.
     */
    public void increaseAllPickedRemedies() {
        this.allPickedRemedies++;
    }

    /**
     * returns the number of picked up remedies for all survivors.
     * @return the number of picked up remedies for all survivors.
     */
    public int getAllPickedRemedies() { return this.allPickedRemedies; }

    /**
     * Method to check if all remedies have been picked up.
     * @return true, if all remedies have been picked up.
     */
    public boolean hasRemedy() {
        return this.hasRemedy;
    }

    /**
     * Method increases steps by one.
     */
    public void increaseSteps() {
        this.steps++;
    }

    /**
     * Method to set that all remedies have been picked up.
     * @param hasRemedy true, if all remedies have been picked up.
     */
    public void setHasRemedy(boolean hasRemedy) {
        this.hasRemedy = hasRemedy;
    }

    /**
     * move() uses the enum (direction) and moves the survivor according to the user input.
     * @param direction     up, down, left or right.
     * @param board         instance of the board.
     */
    public void move(Direction direction, Board board) {
            switch (direction) {
                // When the survivor hits the edge of the field, he stops (via min and max method!)
                case LEFT -> {
                    this.setLocation(Math.max(this.getX() - this.getSpeed(), 0), this.getY());
                }
                case DOWN -> {
                    this.setLocation(this.getX(), Math.min(this.getY() + this.getSpeed(), board.getHeight() - 1));
                }
                case RIGHT -> {
                    this.setLocation(Math.min(this.getX() + this.getSpeed(), board.getWidth() - 1), this.getY());
                }
                case UP -> {
                    this.setLocation(this.getX(), Math.max(this.getY() - this.getSpeed(), 0));
                }
                default -> {
                    System.out.println("Wrong input");
                }
            }
            this.increaseSteps();
            this.decreaseRoundsActive();
            this.decreaseSpeedIfNeeded();
        }

    /**
     * This method checks if the zombie can eat the survivor by comparing the positions.
     * @param zombies       list of all zombies.
     * @return true if the position is the same, else false.
     */
    public boolean ateByZombies(List<Zombie> zombies) {
        for (Zombie z : zombies) {
            if (z.getLocation().equals(this.getLocation())) {
                return true;
            }
        }
        return false;
    }

    /**
     * activatePowerUp() returns a boolean depending on if the powerUp was used.
     * @return true if a powerup was used.
     */
    public boolean activatePowerUp() {
        if (this.activatableItem == null) {
            return false;
        } else {
            switch(this.activatableItem.type) {
                case FLASH -> {
                    this.setSpeed(2);
                    this.setRoundsActive(3);
                }
            }
            this.setActivatableItem(null);
            return true;
        }
    }

    /**
     * This method checks, if the move of the survivor is possible by comparing the position of the survivor with the positions of obstacles.
     * @param deltaX        delta of x-position.
     * @param deltaY        delta of y-position.
     * @param board         instance of the board.
     * @param obstacles     list of all obstacles.
     * @return true if the survivor is able to move to the given direction.
     */
    public boolean isValidMove(int deltaX, int deltaY, Board board, List<Obstacle> obstacles) {
        int xPosition = (int) (this.getX() + deltaX); // new x-position to check
        int yPosition = (int) (this.getY() + deltaY); // new y-position to check

        if (xPosition < 0 || xPosition >= board.getWidth() || yPosition < 0 || yPosition >= board.getHeight()) {
            return false;
        } else {
            for (Obstacle o : obstacles) {
                if (o.getX() == this.getX() + deltaX && o.getY() == this.getY() + deltaY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
    @Override
    public String toBoard() {
        return "S";
    }

    /**
     * Method to return the GameElement type.
     * @return the GameElement type.
     */
    @Override
    public GameElements toGameBoard() {
        return GameElements.SURVIVOR;
    }

    /**
     * Calculates the amount of needed rounds for reaching the exit.
     * @param exit
     * @return the amount of needed rounds for reaching the exit.
     */
    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) (Math.abs(exit.getX() - this.getX()) + Math.abs(exit.getY() - this.getY()));
    }
}

