package game_elements;

import java.util.List;

public class Survivor extends Character {

    private int pickedRemedies;
    private int steps;
    private boolean hasRemedy;
    private int remedyCount;

    public Survivor(List<Survivor> survivors, List<GameElement> allElements, int width, int height) {
        super(allElements, width, height);
        survivors.add(this);
    }

    public Survivor(int xPosition, int yPosition, String color, boolean alive, List<GameElement> survivors, List<GameElement> allElements) {
        super(xPosition, yPosition, color, alive);
        this.pickedRemedies = 0;
        this.steps = 0;
        survivors.add(this);
        allElements.add(this);
    }

    public int getPickedRemedies() {
        return pickedRemedies;
    }

    public void setPickedRemedies(int pickedRemedies) {
        this.pickedRemedies = pickedRemedies;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public boolean isHasRemedy() {
        return hasRemedy;
    }

    public void setHasRemedy(boolean hasRemedy) {
        this.hasRemedy = hasRemedy;
    }

    @Override
    public void move() {

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
