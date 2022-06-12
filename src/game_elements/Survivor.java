package game_elements;

public class Survivor extends Character {

    private int pickedRemedies;
    private int steps;
    private boolean hasRemedy;
    private int remedyCount;

    public int getRemedyCount() {
        return remedyCount;
    }

    public void setRemedyCount(int remedyCount) {
        this.remedyCount = remedyCount;
    }



    public Survivor(int xPosition, int yPosition, String color, boolean alive) {
        super(xPosition, yPosition, color, alive);
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
    public String ToString() {
        return "S";
    }
}
