package game_elements;

import enums.Direction;
import playfield.Board;

import java.util.List;

public class Survivor extends GameCharacter {

    private int pickedRemedies;
    private int allPickedRemedies;
    private int steps = 0;
    private int speed = 1;
    private int roundsActive = 0;
    private Item activatableItem;
    private boolean hasRemedy;
    private String playerName;

    public Survivor(List<Survivor> survivors, List<GameElement> allElements, Board board, String playerName) {
        super(allElements, board);
        survivors.add(this);
        this.playerName = playerName;
    }

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
        this.pickedRemedies++;
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

    public void move(Direction direction, Board board) throws Exception {
        try {
            switch (direction) {
                // Wenn der Survivor auf den Spielfeldrand trifft, bleibt er stehen (über Min- und Max-Methode!)
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
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }
    }

    public boolean ateByZombies(List<Zombie> zombies) throws Exception {
        try {
            for (Zombie z : zombies) {
                if (z.getLocation().equals(this.getLocation())) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }
        return false;
    }

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

    @Override
    public String toBoard() {
        return "S";
    }

    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) (Math.abs(exit.getX() - this.getX()) + Math.abs(exit.getY() - this.getY()));
    }
}

