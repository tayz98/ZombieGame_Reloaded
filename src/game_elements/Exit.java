/**
 * @package game_elements
 * @file Exit.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Exit Class is represented as the final destination point in the game to win it. It inherits from GameObject.
 */

package game_elements;

import playfield.Board;

import java.util.List;

public class Exit extends GameObject {

    /**
     * Exit Constructor. By calling it, it adds itself to every list of the following params.
     * @param exits
     * @param allElements
     * @param fixedObjects
     * @param board
     */
    public Exit(List<Exit> exits, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        exits.add(this);
        fixedObjects.add(this);
    }

    @Override
    public String toBoard() { // kann raus
        return "#";
    }
}


