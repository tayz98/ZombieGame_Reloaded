/**
 * @name ZombieGame
 * @package
 * @file ZombieGame.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description This file is the start point of the program. For more information about the program: see the README.MD file
 */

import enums.PlayType;
import game.ZGame;
import playfield.Board;

public class ZombieGame {

    // constants for the board size.
    public static final int BOARD_HEIGHT = 12;
    public static final int BOARD_WIDTH = 36;

    public static void main(String[] args) throws Exception {

        // instantiating the board and the  game.
        Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT, PlayType.TERMINAL);
        ZGame ZombieGame = new ZGame(board);
        ZombieGame.adjustGame();
        ZombieGame.setupGame();
        ZombieGame.startGame();
    }
}
