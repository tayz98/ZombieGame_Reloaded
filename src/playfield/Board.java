/**
 * @package playfield
 * @file Board.Java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Board class is used as playfield for the game.
 */

package playfield;

import enums.PlayType;
import game_elements.GameElement;
import game_elements.Item;
import game_elements.Survivor;
import game_elements.Zombie;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Board {

    private int width;
    private int height;
    private int score = 0;
    private int roundsActive = 0;
    private Item activatableItem;
    private Item activeItem;
    private PlayType playType;
    private String message = "";

    public Board(final int width, final int height, final PlayType playType) {
        this.width = width;
        this.height = height;
    }

    // getter and setter methods:

    public void setActivatableItem(Item activatableItem) {
        this.activatableItem = activatableItem;
    }

    public Item getActivatableItem() {
        return activatableItem;
    }

    public Item getActiveItem() {
        return activeItem;
    }

    public int getRoundsActive() {
        return roundsActive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void activatePowerUp() {
        if (this.activatableItem != null) {
            this.roundsActive = 3;
            this.activeItem = this.activatableItem;
            this.activatableItem = null;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    /**
     * drawStatusBar() draws the score and powerup item
     */
    public void drawStatusBar() {
        System.out.println("Punkte: " + this.getScore());
        String item = "-";
        if (this.activatableItem != null) {
            item = this.activatableItem.getType().name();
        }
        System.out.println("Aktivierbares Item: " + item);
        if (this.activeItem != null) {
            System.out.println("Active item " + this.activeItem.getType() + " for " + this.roundsActive + " rounds left.");
        }
    }

    /**
     * decreaseActiveRounds() decreases the round timer for the powerup.
     */
    public void decreaseActiveRounds() {
        if (this.roundsActive == 1) {
            this.roundsActive--;
            this.activeItem = null;
        } else if (this.roundsActive > 0) {
            this.roundsActive--;
        }
    }

    /**
     * drawHorizontalLine(String symbol) draws a horizontal line with the given symbol.
     * @param symbol
     */
    private void drawHorizontalLine(String symbol) {
        for (int i = 0; i < this.getWidth() + 2; i++) {
            System.out.print(symbol + "\t");
        }
        System.out.println();
    }

    /**
     * drawBoard(...) takes all GameElements and draws their points on the board.
     * @param fixedObjects
     * @param survivors
     * @param zombies
     * @param nextRound
     */
    public void drawBoard(final List<GameElement> fixedObjects, final List<Survivor> survivors, final List<Zombie> zombies, boolean nextRound) {
        if (nextRound) {
            this.decreaseActiveRounds();
        }
        this.drawStatusBar();
        this.drawHorizontalLine("*");
        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        String sign = "";
        for (int i = 0; i < this.height; i++) {
            System.out.print("*\t");
            for (int j = 0; j < this.width; j++) {
                for (GameElement elem : fixedObjects) {
                    if (elem.getX() == j && elem.getY() == i) {
                        sign = elem.toBoard();
                    }
                }

                for (Survivor s : survivors) {
                    if (s.getX() == j && s.getY() == i) {
                        sign = s.toBoard();
                    }
                }

                for (Zombie z : zombies) {
                    if (z.getX() == j && z.getY() == i) {
                        sign = z.toBoard();
                    }
                }

                printStream.print(sign + "\t");
                sign = "";
            }
            System.out.print("*\t");
            System.out.println();
        }
        this.drawHorizontalLine("*");
    }

    /**
     * printWelcomeMessage() prints a welcome message.
     */
    public void printWelcomeMessage() {
        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8); // wird benötigt, um medizinisches Zeichen anzuzeigen
        this.drawHorizontalLine("-");
        System.out.println(" WELCOME IN ZOMBIELAND");
        System.out.println(" TRY TO SURVIVE");
        System.out.println();
        System.out.println(" [S]\t= Survivor");
        System.out.println(" [Z]\t= Normal Zombie");
        System.out.println(" [ZJ]\t= Jumper Zombie");
        System.out.println(" [#]\t= Exit");
        printStream.println(" [" + "\u2695" + "]\t= Remedy");
        System.out.println(" [o]\t= Portal");
        System.out.println(" [F]\t= Item Flash");
        System.out.println(" [X]\t= Obstacle");
        System.out.println("(* see manual for further information)");
        System.out.println();
        this.drawHorizontalLine("-");
    }

    /**
     * printWinMessage() prints a win meesage with the score.
     */
    public void printWinMessage() {
        this.drawHorizontalLine("-");
        System.out.println(" * CONGRATS! YOU WON!");
        System.out.println(" * YOU ESCAPED FROM THE ZOMBIE(S)");
        System.out.println(" * YOUR SCORE: " + this.getScore());
        this.drawHorizontalLine("-");
    }

    /**
     * printLoseMessage() prints a lose message.
     */
    public void printLoseMessage() {
        this.drawHorizontalLine("-");
        System.out.println(" * OH NO!");
        System.out.println(" * THE ZOMBIE HAS A DELICIOUS MEAL");
        System.out.println(" * THE SURVIVORS LOST");
        this.drawHorizontalLine("-");
    }

    /**
     * printAdjustSettings() prints the queries for adjusting the settings.
     */
    public void printAdjustSettings() {
        this.drawHorizontalLine("-");
        System.out.println();
        System.out.println("Pick your difficulty (* see manual for further information)");
        System.out.println();
        System.out.println(" [1] EASY");
        System.out.println(" [2] MEDIUM");
        System.out.println(" [3] HARD");
        System.out.println(" [4] CUSTOM");
        System.out.println();
        this.drawHorizontalLine("-");
    }
}
