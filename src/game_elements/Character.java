/**
 * @package game_object
 * @file Characters.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description abstract class for all characters in this game.
 */

package game_elements;

import java.util.List;
import java.util.Random;

public abstract class Character extends GameElement {
    public boolean alive;

    public Character(int xPosition, int yPosition, String color, boolean alive) {
        //super(xPosition, yPosition, color);
        this.alive = alive;
    }

    public Character(List<GameElement> allElements, int width, int height) {
        super(allElements, width, height);
    }

    public Character() {

    }

    public abstract void move();

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract String toBoard();

    public abstract int calculateDistanceToExit(Exit exit);
}


