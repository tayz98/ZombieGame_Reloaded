package game_elements;

public class Remedy extends GameObject {
    public Remedy(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    @Override
    public String ToString() {
        return "\u2695";
    }
}


