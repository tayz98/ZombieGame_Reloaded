package game_elements;

import playfield.Board;

import java.util.List;

public class Exit extends GameObject {
    public Exit(List<Exit> exits, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        exits.add(this);
        fixedObjects.add(this);
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


