/**
 * @package game_elements
 * @file Remedy.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: This class is represented as a collectible element, which helps to win the game.
 */

package game_elements;

import enums.GameElements;
import playfield.Board;
import java.util.List;

public class Remedy extends GameElement {

    /**
     * Constructs a Remedy element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements, remedies and fixedObjects.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     * @param remedies      list of all remedies.
     * @param fixedObjects  list of all fixed (non-movable) elements.
     */
    public Remedy(List<Remedy> remedies, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        remedies.add(this);
        fixedObjects.add(this);
    }

    /**
     * Method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
    @Override
    public String toBoard() {
        return "\u2695";
    }

    /**
     * Method to return the GameElement type.
     * @return the GameElement type.
     */
    @Override
    public GameElements toGameBoard() {
        return GameElements.REMEDY;
    }
}


