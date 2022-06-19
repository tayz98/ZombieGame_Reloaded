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
import playfield.Board;
import java.util.List;

public class Survivor extends GameCharacter {


    private int amountOfPickedRemedies;
    private int allPickedRemedies;
    private int speed = 1;
    private int steps = 0;
    private int roundsActive = 0;
    private Item activatableItem;
    private boolean hasRemedy;
    private String playerName;

    /**
     * Survivor Constructor. The Survivor object adds itself to the list of survivors.
     * @param survivors
     * @param allElements
     * @param board
     * @param playerName
     */
    public Survivor(List<Survivor> survivors, List<GameElement> allElements, Board board, String playerName) {
        super(allElements, board);
        survivors.add(this);
        this.playerName = playerName;
    }

    // getter and setter methods:

    public void setActivatableItem(Item activatableItem) {
        this.activatableItem = activatableItem;
    }

    public int getRoundsActive() {
        return roundsActive;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRoundsActive(int roundsActive) {
        this.roundsActive = roundsActive;
    }

    public void decreaseRoundsActive() {
        if (this.roundsActive > 0) {
            this.roundsActive--;
        }
    }

    public void decreaseSpeedIfNeeded() {
        if (this.getSpeed() > 1 && this.getRoundsActive() == 0) {
            this.setSpeed(1);
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public void increasePickedRemedies() {
        this.amountOfPickedRemedies++;
    }

    public void increaseAllPickedRemedies() {
        this.allPickedRemedies++;
    }

    public int getAllPickedRemedies() { return this.allPickedRemedies; }

    public boolean hasRemedy() {
        return this.hasRemedy;
    }

    public void increaseSteps() {
        this.steps++;
    }

    public void setHasRemedy(boolean hasRemedy) {
        this.hasRemedy = hasRemedy;
    }

    /**
     * This method uses the enum (direction) and moves the survivor according to his input.
     * @param direction
     * @param board
     */
    public void move(Direction direction, Board board) {
        try {
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
                    System.out.println("Wrong input"); // evtl. einen rekursiven Aufruf einbauen, sodass der Benutzer nochmal eine Eingabe tätigen kann?
                }
            }
            this.increaseSteps();
            this.decreaseRoundsActive();
            this.decreaseSpeedIfNeeded();
        } catch (Exception e) {
            System.err.println("Something went wrong!"); // kann mMn. raus. Wir haben beim switch doch ein default.

        }
    }

    /**
     * This method checks if the zombie can eat the survivor by comparing the positions.
     * @param zombies
     * @return true if the position is the same, else false.
     */
    public boolean ateByZombies(List<Zombie> zombies) {
        try {
            for (Zombie z : zombies) {
                if (z.getLocation().equals(this.getLocation())) {
                    // weitere Abfrage einbauen, ob der Survivor ein Schild bzw. Schutz hat?
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Something went wrong!"); // notwendig? mir fällt spontan kein Szenario ein, welche Fehler beim Try entstehen könnten.
        }
        return false;
    }

    /**
     * CHANGE ME
     * @return
     */
    public boolean activatePowerUp() { // eine Methode namens "activate" sollte mMn. ein Void sein oder eine andere Methode aufrufen.
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
     * @param deltaX
     * @param deltaY
     * @param board
     * @param obstacles
     * @return CHANGE ME
     */
    public boolean isValidMove(int deltaX, int deltaY, Board board, List<Obstacle> obstacles) { // vielleicht noch Zombies hinzufügen?
        int xPosition = (int) (this.getX() + deltaX); // needs explanation
        int yPosition = (int) (this.getY() + deltaY);

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

    @Override
    public String toBoard() { // kann raus
        return "S";
    }

    @Override
    public String toGameBoard() {
        return "Survivor";
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

