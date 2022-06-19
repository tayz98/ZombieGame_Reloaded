/**
 * @package game_elements
 * @file GameCharacter.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description abstract class for all movable characters (survivors & zombies) in this game.
 */

package game_elements;

import enums.GameElements;
import playfield.Board;
import java.util.List;

public abstract class GameCharacter extends GameElement {

    /**
     * Constructs a GameCharacter and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     */
    public GameCharacter(List<GameElement> allElements, Board board) {
        super(allElements, board);
    }

    /**
     * Abstract method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
    public abstract String toBoard();

    /**
     * Abstract method to return the GameElements type.
     * @return a GameELements type.
     */
    public abstract GameElements toGameBoard();

    /**
     * This method calculates the distance to the given exits location.
     * @param exit  instance of the nearest exit.
     * @return distance to the given exit.
     */
    public abstract int calculateDistanceToExit(Exit exit);
}


