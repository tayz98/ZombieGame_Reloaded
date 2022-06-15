package game_elements;

import playfield.Board;

import java.util.List;

public class Obstacle extends GameObject{
    public Obstacle(List<Obstacle> obstacles, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        obstacles.add(this);
        fixedObjects.add(this);
    }

    @Override
    public String toBoard() {
        return "X";
    }
}
