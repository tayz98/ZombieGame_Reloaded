package game_elements;

import java.util.List;

public class Exit extends GameObject {
    public Exit(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    public Exit(List<Exit> exits, List<GameElement> allElements, int width, int height) {
        super(allElements, width, height);
        exits.add(this);
    }

    @Override
    public String toString() {
        return "#";
    }

    @Override
    public String toBoard() {
        return "#";
    }
}


