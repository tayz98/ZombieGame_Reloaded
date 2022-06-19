/**
 * @package game_elements
 * @file GameElement.Java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: this abstract class contains all "elements" (like: items/characters)
 *               since a GameElement is a "point" it can also inherit from this class.
 */

package game_elements;

import enums.GameElements;
import playfield.Board;
import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class GameElement extends Point {

    /**
     * Constructs a GameElement and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     */
    public GameElement(List<GameElement> allElements, Board board) {
        this.setRandomLocation(board, allElements);
        allElements.add(this);
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
     * setRandomLocation() sets a point with random x and y coordinates for a game element.
     * <p>
     * This method also prevents giving elements the same coordinates,
     * by checking if the same coordinate combination already exists for one element in allElements.
     * @param board         instance fo the game board.
     * @param allElements   list with all elements of the game.
     */
    public void setRandomLocation(Board board, final List<GameElement> allElements) {
        int x, y;
        try {
            Random rand = new Random();
            boolean isValid = true;
            do {
                isValid = true;
                x = rand.nextInt(board.getWidth() - 1);
                y = rand.nextInt(board.getHeight() - 1);
                this.setLocation(x, y);
                for (GameElement elem : allElements) {
                    // if the determined random coordinates equals an element position -> stop
                    if (elem.getLocation().equals(this.getLocation())) {
                        isValid = false;
                        break;
                    }
                }
            } while(!isValid);
        } catch (Exception e) {
            System.err.println("Something went wrong! :)");
        }
    }
}
