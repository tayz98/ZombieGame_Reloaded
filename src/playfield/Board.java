package playfield;

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
    private Item activatableItem;

    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public void setActivatableItem(Item activatableItem) {
        this.activatableItem = activatableItem;
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
    }

    public void drawBoard(final List<GameElement> fixedObjects, final List<Survivor> survivors, final List<Zombie> zombies) {
        this.drawStatusBar();
        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        String sign = "-";
        for (int i = 0; i < this.height; i++) {
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

                printStream.print(sign);
                sign = "-";
            }
            System.out.println();
        }
    }

    // Methode zur Ausgabe einer Willkommensnachricht
    public static void printWelcomeMessage() {
        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8); // wird benötigt, um medizinisches Zeichen anzuzeigen
        System.out.println("***********************************");
        System.out.println("*                                 *");
        System.out.println("*       WELCOME IN ZOMBIELAND     *");
        System.out.println("*          TRY TO SURVIVE         *");
        System.out.println("*                                 *");
        System.out.println("*     [S] = Survivor              *");
        System.out.println("*     [Z] = Zombie                *");
        System.out.println("*     [#] = Exit                  *");
        printStream.println("*     [" + "\u2695" + "] = Remedy                *");
        System.out.println("*     [o] = Portal                *");
        System.out.println("*                                 *");
        System.out.println("***********************************");
    }

    // Methode zur Ausgabe einer Gewinner-Nachricht
    public void printWinMessage() {
        System.out.println("********************************");
        System.out.println(" CONGRATS! YOU WON!");
        System.out.println(" YOU ESCAPED FROM THE ZOMBIE(S)");
        System.out.println(" YOUR SCORE: " + this.getScore());
        System.out.println("********************************");
    }

    // Methode zur Ausgabe einer Verlierer-Nachricht
    public void printLoseMessage() {
        System.out.println("********************************");
        System.out.println(" OH NO!");
        System.out.println(" THE ZOMBIE HAS A DELICIOUS MEAL");
        System.out.println(" THE SURVIVORS LOST");
        System.out.println("********************************");
    }
}
