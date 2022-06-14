package game_elements;

import playfield.Board;

import java.util.List;

public abstract class GameObject extends GameElement {

    private boolean isCollectible;

    public void objectWasUsed() {
        this.isCollectible = false;
        // set isCollectible to false. and make the object disappear.
    }

    public GameObject(List<GameElement> allElements, Board board) {
        super(allElements, board);
    }

}

