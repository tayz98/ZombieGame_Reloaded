/**
 * @package game_object
 * @file Characters.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description abstract class for all characters in this game.
 */

package game_elements;

import playfield.Board;
import java.util.List;

public abstract class GameCharacter extends GameElement {
    public boolean alive;

    public GameCharacter(int xPosition, int yPosition, String color, boolean alive) {
        //super(xPosition, yPosition, color);
        this.alive = alive;
    }

    public GameCharacter(List<GameElement> allElements, Board board) {
        super(allElements, board);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract String toBoard();

    public abstract int calculateDistanceToExit(Exit exit);
}


