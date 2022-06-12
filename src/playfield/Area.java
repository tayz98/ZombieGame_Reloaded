package playfield;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

// Documentation: https://processing.org/reference
// good examples: http://learningprocessing.com/examples/

/* Ideen und Anmerkungen:
- Hauptmenü (+ Hintergrundbild)
- Grid wurde erstellt
- Points hinzufügen und auf Grid skalieren.


 */

public class Area extends  PApplet {
// Example 16-6: Drawing a grid of squares

    // Size of each cell in the grid, ratio of window size to video size
// 80 * 8 = 640
// 60 * 8 = 480
    int videoScale = 8;

    // Number of columns and rows in our system
    int cols, rows;

    public void settings() {
        size(640, 480);

        // Initialize columns and rows
        cols = width / videoScale;
        rows = height / videoScale;
    }

    public void drawTest() {
        text("#", 3,3);
    }

    public void draw() {

        // Begin loop for columns
        for (int i = 5; i < cols-5; i++) {
            // Begin loop for rows
            for (int j = 5; j < rows-5; j++) {

                // Scaling up to draw a rectangle at (x,y)
                int x = i * videoScale;
                int y = j * videoScale;
                fill(255); // das Board bekommt einen weißen Hintergrund
                stroke(0); // schwarze Linien.
                // For every column and row, a rectangle is drawn at an (x,y) location scaled and sized by videoScale.
                rect(x, y, videoScale, videoScale);

                fill(0);
                textSize(20);
                text("Score: ", 20, 25);
                text("Highscore: ", 400, 25);

            }
        }
    }
}