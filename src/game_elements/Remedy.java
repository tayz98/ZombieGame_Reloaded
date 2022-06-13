package game_elements;

import java.util.List;

public class Remedy extends GameObject {
    public Remedy(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    public Remedy(List<Remedy> remedies, List<GameElement> allElements, int width, int height) {
        super(allElements, width, height);
        remedies.add(this);
    }

    @Override
    public String toString() {
        return "\u2695";
    }

    @Override
    public String toBoard() {
        return null;
    }
}


