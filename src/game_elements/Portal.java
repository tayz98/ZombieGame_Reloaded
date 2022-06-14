package game_elements;

import playfield.Board;

import java.util.List;

/*
    Ports the survivor to the corresponding other portal.
    What happens if the zombies goes over the Portal? (exception handling maybe)
    Unicode:
 */
public class Portal extends GameObject {
    public Portal(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    public Portal(List<Portal> portals, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        portals.add(this);
        fixedObjects.add(this);
    }

    @Override
    public String toString() {
        return "\uD83C\uDF00"; // https://emojipedia.org/cyclone/
    }

    @Override
    public String toBoard() {
        return "o";
    }

    public void teleport() {

    }
}