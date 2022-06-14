package game_elements;

import enums.Direction;
import playfield.Board;

import java.util.List;

public class Survivor extends GameCharacter {

    private int pickedRemedies;
    private int allPickedRemedies;
    private int steps = 0;
    private Item activatableItem;
    private int passiveItemRoundsLeft = 0;
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

    public int getSteps() {
        return steps;
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
                    this.setLocation(Math.max(this.getX() - 1, 0), this.getY());
                    this.increaseSteps();
                }
                case DOWN -> {
                    this.setLocation(this.getX(), Math.min(this.getY() + 1, board.getHeight() - 1));
                    this.increaseSteps();
                }
                case RIGHT -> {
                    this.setLocation(Math.min(this.getX() + 1, board.getWidth() - 1), this.getY());
                    this.increaseSteps();
                }
                case UP -> {
                    this.setLocation(this.getX(), Math.max(this.getY() - 1, 0));
                    this.increaseSteps();
                }
                default -> {
                    System.out.println("Wrong input");
                }
            }
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

    @Override
    public String toBoard() {
        return "S";
    }

    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) (Math.abs(exit.getX() - this.getX()) + Math.abs(exit.getY() - this.getY()));
    }
}

