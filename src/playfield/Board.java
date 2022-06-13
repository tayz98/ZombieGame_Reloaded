package playfield;

import game_elements.GameElement;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Board {
    private int width;
    private int height;

    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void drawBoard(final List<GameElement> allElements) {
        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        String sign = "-";
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                for (GameElement elem : allElements) {
                    if (elem.getX() == j && elem.getY() == i) {
                        sign = elem.toBoard();
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
    public static void printWinMessage() {
        System.out.println("***********************************");
        System.out.println("*                                 *");
        System.out.println("*       CONGRATS! YOU WON!        *");
        System.out.println("*   YOU ESCAPED FROM THE ZOMBIE   *");
        System.out.println("*                                 *");
        System.out.println("***********************************");
    }

    // Methode zur Ausgabe einer Verlierer-Nachricht
    public static void printLoseMessage() {
        System.out.println("***********************************");
        System.out.println("*                                 *");
        System.out.println("*               OH NO!            *");
        System.out.println("* THE ZOMBIE HAS A DELICIOUS MEAL *");
        System.out.println("*                                 *");
        System.out.println("***********************************");
    }
}
