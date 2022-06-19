/**
 * @package game_elements
 * @file Remedy.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: This class is represented as a collectible element, which is required to win the game.
 */

package game_elements;

import enums.GameElements;
import playfield.Board;

import java.util.List;

public class Remedy extends GameElement {

    /**
     * Remedy Constructor. It adds itself to the list of remedies and GameObjects.
     * @param remedies
     * @param allElements
     * @param fixedObjects
     * @param board
     */
    public Remedy(List<Remedy> remedies, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        remedies.add(this);
        fixedObjects.add(this);
    }

    @Override
    public String toBoard() {
        return "\u2695";
    }

    @Override
    public GameElements toGameBoard() {
        return GameElements.REMEDY;
    }
}


