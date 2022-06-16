/**
 * package game_elements
 * file GameElement.Java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: this abstract class contains all "elements" (like: items/characters)
 *               since a GameElement is a "point" it can also inherit from this class.
 */

package game_elements;

import playfield.Board;
import java.awt.*;
import java.util.List;
import java.util.Random;


public abstract class GameElement extends Point {

    private String color; // kann raus.

    /**
     * The constructor gets a list of all instantiated elements and the board
     * @params: List<GameElement> allElements, Board board
     */
    public GameElement(List<GameElement> allElements, Board board) {
        this.setRandomLocation(board, allElements);
        allElements.add(this);
    }


    public String getColor() { // kann raus
        return color;
    } // kann raus

    public void setColor(String color) { // kann raus
        this.color = color;
    } // kann raus

    public abstract String toBoard(); // kann raus

    /**
     * With the help of the Random class, all game elements are assigned a random X and Y coordinate.
     * <p>
     * This method also prevents giving elements the same coordinates
     * by checking if the same coordinate combination already exists.
     * @param board
     * @param allElements
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
                    //&& !(elem == this)) {
                    if (elem.getLocation().equals(this.getLocation())) { // if the determined coordinates from the rand method
                        isValid = false;                                 // equals an element position -> stop.
                        break;
                    }
                }
            } while(!isValid);
        } catch (Exception e) {
            System.err.println("Something went wrong! :)"); // was könnte den zu einem Fehler führen, dass wir hier eine Exception brauchen? - Alex
        }
    }
}
