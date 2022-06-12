/**
 * @package game_object
 * @file Characters.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description abstract class for all characters in this game.
 */

package game_elements;

public abstract class Character extends GameElement {

    public Character(int xPosition, int yPosition, String color, boolean alive) {
        super(xPosition, yPosition, color);
        this.alive = alive;
    }

    public abstract void move();
    public boolean alive;
}

