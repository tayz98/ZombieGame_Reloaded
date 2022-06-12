package game_elements;

public class Survivor extends Character {

    private int pickedRemedies;
    private int steps;
    private boolean hasRemedy;

    public Survivor(int xPosition, int yPosition) {
        super(xPosition, yPosition);
        this.pickedRemedies = 0;
        this.steps = 0;
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
        return "S";
    }
}
