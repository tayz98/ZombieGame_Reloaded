/**
 * @package game_elements
 * @file Obstacle.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Obstalce class is represented as a point which prevents the player and zombie going through it.
 */

package game_elements;

import enums.GameElements;
import playfield.Board;
import java.util.List;

public class Obstacle extends GameElement {

    /**
     * Constructs an obstacle element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements, obstacles and fixedObjects.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     * @param obstacles     list of all obstacles.
     * @param fixedObjects  list of all fixed (non-movable) elements.
     */
    public Obstacle(List<Obstacle> obstacles, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        obstacles.add(this);
        fixedObjects.add(this);
    }

    /**
     * Method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
    @Override
    public String toBoard() {
        return "X";
    }

    /**
     * Method to return the GameElement type.
     * @return the GameElement type.
     */
    @Override
    public GameElements toGameBoard() {
        return GameElements.OBSTACLE;
    }
}
