/**
 * @package game_elements
 * @file Portal.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The portal ports (the survivor) to its linked portal.
 */

package game_elements;

import enums.GameElements;
import playfield.Board;
import java.util.List;

public class Portal extends GameElement {

    /**
     * Constructs a portal element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements, portals and fixedObjects.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     * @param portals       list of all portals.
     * @param fixedObjects  list of all fixed (non-movable) elements.
     */
    public Portal(List<Portal> portals, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        portals.add(this);
        fixedObjects.add(this);
    }

    /**
     * Method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
    @Override //
    public String toBoard() {
        return "o";
    }

    /**
     * Method to return the GameElement type.
     * @return the GameElement type.
     */
    @Override
    public GameElements toGameBoard() {
        return GameElements.PORTAL;
    }

    /**
     * Simple port logic, needs the survivor and portal list as arguments. <p>
     * Ports the player to the other portal.
     * @param s         instance of the survivor who enters a portal.
     * @param portals   list of all portals.
     */
    public void teleport(Survivor s, List<Portal> portals) {
        // if the survivor position equals the position of portal 1, port to portal 0.
        if (s.getLocation().equals(portals.get(0).getLocation())) {
            s.setLocation(portals.get(1).getLocation());
        } else {
            // if the position equals portal 1, port to portal 0.
            s.setLocation(portals.get(0).getLocation());
        }
    }
}