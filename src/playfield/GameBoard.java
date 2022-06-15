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

    public void draw(Point p) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (p.getX() == i && p.getY() == j) {
                    fill(200);
                } else {
                    fill(100);
                }
                rect(i*20,j*20,20, 20);
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("playfield.GameBoard");
    }

}
