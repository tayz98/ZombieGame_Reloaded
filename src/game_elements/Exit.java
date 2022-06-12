package game_elements;

public class Exit extends Object{
    public Exit(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    @Override
    public String ToString() {
        return "#";
    }
}


