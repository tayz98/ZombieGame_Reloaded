package game_elements;

import playfield.Board;

import java.util.List;

public class Remedy extends GameObject {
    public Remedy(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    public Remedy(List<Remedy> remedies, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        remedies.add(this);
        fixedObjects.add(this);
    }

    @Override
    public String toString() {
        return "\u2695";
    }

    @Override
    public String toBoard() {
        return "\u2695";
    }
}


