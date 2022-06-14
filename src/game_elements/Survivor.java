package game_elements;

import playfield.Board;

import java.util.List;

public class Survivor extends GameCharacter {

    private int pickedRemedies;
    private int allPickedRemedies;
    private int steps = 0;
    private boolean hasRemedy;
    private int remedyCount;

    public Survivor(List<Survivor> survivors, List<GameElement> allElements, Board board) {
        super(allElements, board);
        survivors.add(this);
    }

    public Survivor(int xPosition, int yPosition, String color, boolean alive, List<GameElement> survivors, List<GameElement> allElements) {
        super(xPosition, yPosition, color, alive);
        this.pickedRemedies = 0;
        this.steps = 0;
        survivors.add(this);
        allElements.add(this);
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

    public boolean move(String input, Board board) throws Exception {
        try {
            switch (input) {
                // Wenn der Survivor auf den Spielfeldrand trifft, bleibt er stehen (über Min- und Max-Methode!)
                case "a" -> {
                    this.setLocation(Math.max(this.getX() - 1, 0), this.getY());
                    this.increaseSteps();
                    return true;
                }
                case "s" -> {
                    this.setLocation(this.getX(), Math.min(this.getY() + 1, board.getHeight() - 1));
                    this.increaseSteps();
                    return true;
                }
                case "d" -> {
                    this.setLocation(Math.min(this.getX() + 1, board.getWidth() - 1), this.getY());
                    this.increaseSteps();
                    return true;
                }
                case "w" -> {
                    this.setLocation(this.getX(), Math.max(this.getY() - 1, 0));
                    this.increaseSteps();
                    return true;
                }
                case "q" -> System.out.println("Exit game...");
                default -> {
                    System.out.println("Wrong input");
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }
        return false;
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
    public String toString() {
        return GameElementEnums.SURVIVOR.toString();
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

