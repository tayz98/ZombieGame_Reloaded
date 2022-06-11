package playfield;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

// Documentation: https://processing.org/reference

/* Ideen und Anmerkungen:
- Hauptmenü (+ Hintergrundbild)
- Elemente als Text darstellen könnte schwierig werden, da die Zeichen eine unterschiedliche Größe haben und sich überlappen können..
- usw.


 */

public class Area extends  PApplet {
    // ArrayList<Integer> x = new ArrayList<>(), y= new ArrayList<>();
    String Survivor = "#";


    public void settings() {
     size(1280,720); // Fenstergröße
    }

    public void setup() {
        fill(255,255,255); // Farbe des Texts
        textSize(50); // Größe der Punkte auf dem Spiel
        background(0);

    }


    public void draw() {
        // nur ein Test, lässt sich sicherlich auch in einer for Schleife umsetzen...
    for(int i= 0; i<500;i++) {
        text("-", 350+i, 100);
    }
    for (int j=0; j<300;j++) {
        text("-", 350, 100+j);
    }
        for (int k=0; k<500;k++) {
            text("-", 350+k, 400);
        }

        for (int l=0; l<300;l++) {
            text("-", 850, 100+l);
        }
        }
    }