/**
 * @package game_elements
 * @file Obstacle.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Obstalce class is represented as a point which prevents the player and zombie going through it.
 */

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
    public String toBoard() { // kann raus
        return "X";
    }
}
