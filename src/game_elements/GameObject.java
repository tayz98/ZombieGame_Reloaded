package game_elements;

public abstract class GameObject extends GameElement {
    public GameObject(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color);
        this.isCollectible = isCollectible;
    }

    private boolean isCollectible;

    public void objectWasUsed() {
        this.isCollectible = false;
        // set isCollectible to false. and make the object disappear.

    }
}

