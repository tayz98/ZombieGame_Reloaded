package playfield;

import enums.PlayType;
import game.ZGame;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;


public class GameBoard extends PApplet {

    public static final int BOARD_HEIGHT = 12;
    public static final int BOARD_WIDTH = 36;

    Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT, PlayType.WINDOW);
    ZGame ZombieGame = new ZGame(board);

    // 0 = start, 1 = info, 2 = setup, 3 = game, 4 = game over
    int gameState = 1;
    int xSize = board.getWidth();
    int ySize = board.getHeight();
    int xField = 30;
    int yField = 30;
    int margin = 50;
    int boxTopHeight = 100;
    int boxBotHeight = 100;
    int gridHeight = ySize * yField;
    int gridWidth = xSize * xField;
    ArrayList<PImage> images = new ArrayList<>();
    Point p = new Point(1, 1);

    PImage zombieImg;
    PImage flashImg;
    PImage exitImg;
    PImage remedyImg;
    PImage survivorImg;
    PImage obstacleImg;
    PImage portalImg;

    public void clearBoard() {
        background(20);
    }

    public void drawContinue() {
        fill(100, 50);
        textSize(20);
        textAlign(RIGHT);
        text("[ENTER] to continue", width - 20, height - 20);
    }

    public void drawExit() {
        fill(100, 50);
        textSize(20);
        textAlign(LEFT);
        text("[ESC] to exit", 20, 30);
    }

    public void drawBack() {
        fill(100, 50);
        textSize(20);
        textAlign(LEFT);
        text("[BACKSPACE] to go back", 20, height - 20);
    }

    public void drawBoard() {
        fill(20);
        stroke(40);
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                rect(margin + j * xField, margin + boxTopHeight + i * xField, xField, yField);
                if (p.getX() == j && p.getY() == i) {
                    image(survivorImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                }
            }
        }
    }

    public void drawStatusBar() {
        stroke(0,0,0,0);
        fill(20);
        rect(margin, margin, gridWidth, boxTopHeight);
    }

    public void drawBottomBar() {
        stroke(0,0,0,0);
        fill(20);
        rect(margin, margin + boxTopHeight + gridHeight, gridWidth, boxBotHeight);
    }

    public void drawMainMenu() {
        background(20);
        fill(255, random(100));
        textSize(64);
        textAlign(CENTER);
        text("Welcome to Zombieland", width / 2f, height / 2f);
        drawContinue();
        drawExit();
    }

    public void drawInfo() {
        background(20);
        int size = xField;
        int listHeight = height - 100;
        int heightPerItem = listHeight / 10;
        int imgStart = 200;
        int textStart = imgStart + 50;
        int controlStart = width - 450;
        int yOffset = 100;

        fill(100, 100);
        textSize(50);
        textAlign(CENTER);
        text("information and controls", width / 2f, 60);
        textSize(20);
        text("(see manual for details)", width / 2f, 85);
        // information
        textSize(25);
        textAlign(LEFT, TOP);
        image(survivorImg, imgStart, heightPerItem + yOffset, size, size);
        image(zombieImg, imgStart, 2 * heightPerItem + yOffset, size, size);
        image(zombieImg, imgStart, 3 * heightPerItem + yOffset, size, size);
        image(exitImg, imgStart, 4 * heightPerItem + yOffset, size, size);
        image(remedyImg, imgStart, 5 * heightPerItem + yOffset, size, size);
        image(portalImg, imgStart, 6 * heightPerItem + yOffset, size, size);
        image(flashImg, imgStart, 7 * heightPerItem + yOffset, size, size);
        image(obstacleImg, imgStart, 8 * heightPerItem + yOffset, size, size);
        text("Survivor", textStart, heightPerItem + yOffset);
        text("Normal Zombie", textStart, 2 * heightPerItem + yOffset);
        text("Jumper Zombie", textStart, 3 * heightPerItem + yOffset);
        text("Exit", textStart, 4 * heightPerItem + yOffset);
        text("Remedy", textStart, 5 * heightPerItem + yOffset);
        text("Portal", textStart, 6 * heightPerItem + yOffset);
        text("Item \"Flash\"", textStart, 7 * heightPerItem + yOffset);
        text("Obstacle", textStart, 8 * heightPerItem + yOffset);

        // controls
        textSize(25);
        text("[W] = move up", controlStart, heightPerItem + yOffset);
        text("[A] = move left", controlStart, 2 * heightPerItem + yOffset);
        text("[S] = move down", controlStart, 3 * heightPerItem + yOffset);
        text("[D] = move right", controlStart, 4 * heightPerItem + yOffset);
        text("[E] = use item", controlStart, 5 * heightPerItem + yOffset);

        drawContinue();
        drawBack();
        drawExit();
    }

    public void drawDifficulty() {
        background(20);
        drawContinue();
        drawBack();
        drawExit();
    }

    public void settings() {
        int x = xSize * xField + 2 * margin;
        int y = ySize * yField + 2 * margin + boxTopHeight + boxBotHeight;
        size(x, y);
    }

    public void setup() {
        background(20);
        zombieImg = loadImage("playfield/images/zombieNormal.png");
        flashImg = loadImage("playfield/images/flash.png");
        exitImg = loadImage("playfield/images/exit.png");
        remedyImg = loadImage("playfield/images/remedy.png");
        survivorImg = loadImage("playfield/images/survivor.png");
        portalImg = loadImage("playfield/images/portal.png");
        obstacleImg = loadImage("playfield/images/obstacle.png");
    }

    public void draw() {
        switch (gameState) {
            case 0 -> {
                drawMainMenu();
            }
            case 1 -> {
                drawInfo();
            }
            case 2 -> {
                drawDifficulty();
            }
            case 3 -> {
                clearBoard();
                drawStatusBar();
                drawBottomBar();
                drawBoard();
            }
            case 4 -> {

            }
        }
    }

    public void mousePressed() {

    }

    public void keyPressed() {
        switch (keyCode) {
            case 10 -> gameState++;
            case 8 -> gameState--;
        }
        /*
        if (gameState == 1) {
            switch (keyCode) {
                case 65 -> {p.setLocation(p.getX() - 1, p.getY());}     // A
                case 68 -> {p.setLocation(p.getX() + 1, p.getY());}    // D
                case 83 -> {p.setLocation(p.getX(), p.getY() + 1);}     // S
                case 87 -> {p.setLocation(p.getX(), p.getY() - 1);}     // W
                case 27 -> {System.exit(42);}                       // ESC
                case 69 -> {// ITEM}                                        // E
                }
            }
        } else {
            switch (keyCode) {
                case 10 -> {
                    gameState = 1;
                }
            }
            println("not ingame");
        }

         */
    }

    public static void main(String[] args) {
        PApplet.main("playfield.GameBoard");
    }

}
