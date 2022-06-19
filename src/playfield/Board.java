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

    private int width;              // width of board
    private int height;             // height of board
    private int score = 0;          // score of the player
    private int roundsActive = 0;   // rounds that an item is active
    private Item activatableItem;   // item that is collected by the player
    private Item activeItem;        // activated item by the player.
    private PlayType playType;      // variable to determine if the game will be a terminal or window game.
    private String message = "";    // message for status updates.

    /**
     * Constructs a Board element depending on the given size.
     * @param width     amount of field in x-direction.
     * @param height    amount of field in y-direction.
     * @param playType  variable to declare if the game will be a terminal or window game.
     */
    public Board(final int width, final int height, final PlayType playType) {
        this.width = width;
        this.height = height;
    }

    /**
     * Method to set the item that is collected.
     * @param activatableItem   Item that is collected.
     */
    public void setActivatableItem(Item activatableItem) {
        this.activatableItem = activatableItem;
    }

    /**
     * Method ro return the item that is activatable.
     * @return the item that is activatable.
     */
    public Item getActivatableItem() {
        return activatableItem;
    }

    /**
     * Method to return the active item.
     * @return the active item in use.
     */
    public Item getActiveItem() {
        return activeItem;
    }

    /**
     * Method to return the amount of rounds that the item is still active.
     * @return the amount of rounds that the item is still active.
     */
    public int getRoundsActive() {
        return roundsActive;
    }

    /**
     * Method to get status-message.
     * @return status-message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Method to set the status-message.
     * @param message   message to display as information.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Method to activate a power-up.
     */
    public void activatePowerUp() {
        if (this.activatableItem != null) {
            this.roundsActive = 3;
            this.activeItem = this.activatableItem;
            this.activatableItem = null;
        }
    }

    /**
     * Method to return the width.
     * @return the width of the board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method to return the height.
     * @return the height of the board.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Method to get the score
     * @return the active score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Method to increase the active score.
     * @param points    amount of points to increase.
     */
    public void increaseScore(int points) {
        this.score += points;
    }

    /**
     * Method draws the score and powerup item
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
     * Method decreases the round timer for the powerup.
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
     * Method draws a horizontal line with the given symbol.
     * @param symbol    smybold to display as a line.
     */
    private void drawHorizontalLine(String symbol) {
        for (int i = 0; i < this.getWidth() + 2; i++) {
            System.out.print(symbol + "\t");
        }
        System.out.println();
    }

    /**
     * Method takes all GameElements and draws their points on the board.
     * @param fixedObjects  list of all fixed objects.
     * @param survivors     list of all survivors.
     * @param zombies       list of all zombies.
     * @param nextRound     true, if a new round has begun.
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
     * Method prints a welcome message.
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
     * Method prints a win message with the score.
     */
    public void printWinMessage() {
        this.drawHorizontalLine("-");
        System.out.println(" * CONGRATS! YOU WON!");
        System.out.println(" * YOU ESCAPED FROM THE ZOMBIE(S)");
        System.out.println(" * YOUR SCORE: " + this.getScore());
        this.drawHorizontalLine("-");
    }

    /**
     * Method prints a loss message.
     */
    public void printLoseMessage() {
        this.drawHorizontalLine("-");
        System.out.println(" * OH NO!");
        System.out.println(" * THE ZOMBIE HAS A DELICIOUS MEAL");
        System.out.println(" * THE SURVIVORS LOST");
        this.drawHorizontalLine("-");
    }

    /**
     * Method prints the queries for adjusting the settings.
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
