package game_elements;

import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class GameElement extends Point {
    private String color;

    public GameElement(List<GameElement> allElements, int width, int height) {
        this.setRandomLocation(width, height, allElements);
        allElements.add(this);
    }

    public GameElement() {

    }

    public abstract String toString();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



    /*
    public GameElement(int xPosition, int yPosition, String color) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
    }

     */

    /*
    public GameElement(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

     */

    public GameElement(List<GameElement> elements, List<GameElement> allElements) {

    }

    public abstract String toBoard();

    public void setRandomLocation(final int width, final int height, final List<GameElement> allElements) {
        int x, y;
        try {
            Random rand = new Random();
            boolean isValid = true;
            do {
                x = rand.nextInt(width - 1);
                y = rand.nextInt(height - 1);
                this.setLocation(x, y);
                for (GameElement elem : allElements) {
                    if (elem.equals(this) && !(elem == this)) {
                        isValid = false;
                        break;
                    }
                }
            } while(!isValid);
        } catch (Exception e) {
            System.err.println("Something went wrong! :)");
        }
    }
}
