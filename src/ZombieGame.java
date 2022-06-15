/**
 * @name Main
 * @package
 * @file Main.Java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description This file is the start point of the program. For more information about the program: see the README.MD file
 */

import processing.core.PApplet;
import Game.Settings;
import Game.ZGame;
import playfield.Board;

public class ZombieGame {

    // hier werden Konstanten für die Spielfeldgröße definiert
    public static final int BOARD_HEIGHT = 12;
    public static final int BOARD_WIDTH = 36;

    public static void main(String[] args) throws Exception {

        // Am Anfang wird eine Willkommensnachricht ausgegeben, die dem Spieler erklärt, wie das Spiel funktioniert
        // Board.printWelcomeMessage();
        // Settings settings = new Game.Settings(); // Konstruktor für das Game.Settings-Objekt: hier werden gleichzeitig noch die Game.Settings abgefragt und gesetzt -> siehe Klasse "Game.Settings"

        Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        ZGame ZombieGame = new ZGame(board);
        ZombieGame.adjustGame();
        ZombieGame.setupGame();
        ZombieGame.startGame();
    }
}
