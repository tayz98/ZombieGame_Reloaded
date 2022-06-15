package game_elements;

import playfield.Board;

import java.util.List;

public class Portal extends GameObject {
    public Portal(List<Portal> portals, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        portals.add(this);
        fixedObjects.add(this);
    }

    @Override
    public String toString() {
        return "\uD83C\uDF00"; // https://emojipedia.org/cyclone/
    }

    @Override
    public String toBoard() {
        return "o";
    }

    // Methode zum Teleportieren eines Spielers
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