package playfield;

import game_elements.*;
import org.w3c.dom.Text;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Documentation: https://processing.org/reference
// good examples: http://learningprocessing.com/examples/

/*
- Ideen und Anmerkungen:
- Hauptmenü (+ Hintergrundbild)
- Grid wurde erstellt
- Points hinzufügen und auf Grid skalieren.
- Kindklassen erstellen für Grid usw.?
*/


/*
public class Area extends  PApplet {



    // images of the game elements
    PImage zombie;
    PImage powerUp;
    PImage remedy;
    PImage door;

    Cell[][] grid; // 2D array of the board of type Cell
    Board board = new Board(10,10);

    // amount of cols and rows on the board
    int cols = 10;
    int rows = 10;

    List<Point> field = new ArrayList<>(); // list of our points aka game elements

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        // pictures
        zombie = loadImage("https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/325/zombie_1f9df.png");
        powerUp = loadImage("https://x-up.ws/i/e2d34a1a6a48.jpg");
        remedy = loadImage("https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/sony/336/medical-symbol_2695-fe0f.png");
        door = loadImage("https://emojigraph.org/media/apple/door_1f6aa.png");
        // Initialize columns and rows
        grid = new Cell[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                // Initialize each object
                grid[i][j] = new Cell(i * 80, j * 80, 80, 80);
            }
        }
    }

    public void draw() {

        // Begin loop for columns
        background(0);
        // The counter variables i and j are also the column and row numbers and
        // are used as arguments to the constructor for each object in the grid.
        for (int i = 1; i < cols-1; i++) {
            for (int j = 1; j < rows-1; j++) {
                // Display each object
                grid[i][j].display();
                Point tmp = new Point();
                tmp.setLocation(i*80, j*80);
                field.add(tmp);
            }
        }
        for (Zombie z : zombies) {
            image(zombie, (float) z.getX(), (float) z.getY(), 75,75);
        }
        fill(192,192,192);
        textSize(30);
        text("Score: "+ board.getScore(),20,50);
        textSize(30);
        text("Highscore: ",400,50);
        text("Powerups: ", 20, 750);
        image(powerUp, 180, 730, 70, 70);
    }


    class Cell {
        // A cell object knows about its location in the grid
        // as well as its size with the variables x,y,w,h
        float x, y;   // x,y location
        float w, h;   // width and height


        // Cell Constructor
        Cell(float tempX, float tempY, float tempW, float tempH) {
            x = tempX;
            y = tempY;
            w = tempW;
            h = tempH;
        }

        void display() {
            stroke(255);
            fill(0);
            rect(x, y, w, h);
        }
    }
} */