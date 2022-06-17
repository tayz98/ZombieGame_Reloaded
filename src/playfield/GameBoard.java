package playfield;

import processing.core.PApplet;

import java.awt.*;

public class GameBoard extends PApplet {

    Point p = new Point(5,5);

    public void settings() {
        size(500, 500);
    }

    public void setup() {

    }

    public void draw() {
        rect(1,1,498,200);
    }

    public static void main(String[] args) {
        PApplet.main("playfield.GameBoard");
    }

}
