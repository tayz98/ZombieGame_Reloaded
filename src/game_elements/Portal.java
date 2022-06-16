/**
 * @package game_elements
 * @file Portal.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The portal ports the survivor point to its linked portal.
 */

package game_elements;

import playfield.Board;

import java.util.List;

public class Portal extends GameObject {
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

    // methods:


    /**
     * Simple port logic, needs the survivor and portal list as argument.
     * @param s
     * @param portals
     */
    public void teleport(Survivor s, List<Portal> portals) {
        System.out.println(portals.get(0).getLocation());
        System.out.println(portals.get(1).getLocation());
        if (s.getLocation().equals(portals.get(0).getLocation())) {
            s.setLocation(portals.get(1).getLocation());
        } else {
            s.setLocation(portals.get(0).getLocation());
        }
    }
}