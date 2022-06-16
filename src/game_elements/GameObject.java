/**
 * package game_elements
 * file GameObject.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: gameobject is the super class of all non-moveable elements in the game. (e.g. items, remedys, exit)
 */

package game_elements;

import playfield.Board;

import java.util.List;


public abstract class GameObject extends GameElement {

    private boolean isCollectible;

    /**
     * This method will be called when a GameCharacter is on the same point as an GameObject
     * After calling the method, the object will disappear.
     *
     */
    public void objectWasUsed() {
        this.isCollectible = false;
        // set isCollectible to false. and make the object disappear.
    }

    public GameObject(List<GameElement> allElements, Board board) {
        super(allElements, board);
    }

}

