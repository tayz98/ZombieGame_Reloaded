/**
 * @package game_elements
 * @file Portal.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The portal ports (the survivor) to its linked portal.
 */

package game_elements;

import playfield.Board;

import java.util.List;

public class Portal extends GameElement {

    /**
     * Portal constructor. It adds itself to the list of portals and GameObjects.
     * @param portals
     * @param allElements
     * @param fixedObjects
     * @param board
     */
    public Portal(List<Portal> portals, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        portals.add(this);
        fixedObjects.add(this);
    }

    @Override // kann raus
    public String toString() {
        return "\uD83C\uDF00"; // https://emojipedia.org/cyclone/
    }

    @Override // kann raus
    public String toBoard() {
        return "o";
    }

    @Override
    public String toGameBoard() {
        return "Portal";
    }

    // methods:


    /**
     * Simple port logic, needs the survivor and portal list as arguments.
     * @param s
     * @param portals
     */
    public void teleport(Survivor s, List<Portal> portals) {
        // if the survivor position equals the position of portal 1, port to portal 0.
        if (s.getLocation().equals(portals.get(0).getLocation())) { // was ist, wenn die survivor position == portals.get(1) position ist?
            s.setLocation(portals.get(1).getLocation());
        } else {
            s.setLocation(portals.get(0).getLocation()); // if the position equals portal 1, port to portal 0.
        }
    }
}