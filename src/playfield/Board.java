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

    public Board(final int width, final int height, final PlayType playType) {
        this.width = width;
        this.height = height;
    }

    public void setActivatableItem(Item activatableItem) {
        this.activatableItem = activatableItem;
    }

    public void activatePowerUp() {
        this.roundsActive = 3;
        this.activeItem = this.activatableItem;
        this.activatableItem = null;
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

    public void decreaseActiveRounds() {
        if (this.roundsActive == 1) {
            this.roundsActive--;
            this.activeItem = null;
        } else if (this.roundsActive > 0) {
            this.roundsActive--;
        }
    }

    private void drawHorizontalLine(String symbol) {
        for (int i = 0; i < this.getWidth() + 2; i++) {
            System.out.print(symbol + "\t");
        }
        System.out.println();
    }

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

    // Methode zur Ausgabe einer Willkommensnachricht
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

    // Methode zur Ausgabe einer Gewinner-Nachricht
    public void printWinMessage() {
        this.drawHorizontalLine("-");
        System.out.println(" * CONGRATS! YOU WON!");
        System.out.println(" * YOU ESCAPED FROM THE ZOMBIE(S)");
        System.out.println(" * YOUR SCORE: " + this.getScore());
        this.drawHorizontalLine("-");
    }

    // Methode zur Ausgabe einer Verlierer-Nachricht
    public void printLoseMessage() {
        this.drawHorizontalLine("-");
        System.out.println(" * OH NO!");
        System.out.println(" * THE ZOMBIE HAS A DELICIOUS MEAL");
        System.out.println(" * THE SURVIVORS LOST");
        this.drawHorizontalLine("-");
    }

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
