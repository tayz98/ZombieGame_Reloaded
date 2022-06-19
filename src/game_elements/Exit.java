/**
 * @package game_elements
 * @file Exit.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Exit Class is represented as the final destination point in the game to win it. It inherits from GameElement.
 */

package game_elements;

import enums.GameElements;
import playfield.Board;

import java.util.List;

public class Exit extends GameElement {

    /**
     * Constructs an Exit element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements, exits and fixedObjects.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     * @param exits         list of all exits.
     * @param fixedObjects  list of all fixed (non-movable) elements.
     */
    public Exit(List<Exit> exits, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        exits.add(this);
        fixedObjects.add(this);
    }

    /**
     * Method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
    @Override
    public String toBoard() {
        return "#";
    }

    /**
     * Method to return the GameElement type.
     * @return the GameElement type.
     */
    @Override
    public GameElements toGameBoard() {
        return GameElements.EXIT;
    }
}


