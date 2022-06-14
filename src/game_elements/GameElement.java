package game_elements;

import playfield.Board;
import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class GameElement extends Point {

    private String color;

    public GameElement(List<GameElement> allElements, Board board) {
        this.setRandomLocation(board, allElements);
        allElements.add(this);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract String toBoard();

    public void setRandomLocation(Board board, final List<GameElement> allElements) {
        int x, y;
        try {
            Random rand = new Random();
            boolean isValid = true;
            do {
                x = rand.nextInt(board.getWidth() - 1);
                y = rand.nextInt(board.getHeight() - 1);
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
