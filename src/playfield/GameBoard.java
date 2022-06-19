package playfield;

import enums.Direction;
import enums.PlayType;
import enums.ZombieTypes;
import game.ZGame;
import game_elements.GameElement;
import game_elements.Survivor;
import game_elements.Zombie;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;

public class GameBoard extends PApplet {

    public static final int BOARD_HEIGHT = 12;
    public static final int BOARD_WIDTH = 36;

    Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT, PlayType.WINDOW);
    ZGame ZombieGame = new ZGame(board);

    // 0 = start, 1 = info, 2 = setup, 3 = game, 4 = game lost, 5 = game won
    int gameState = 0;
    int xSize = board.getWidth();
    int ySize = board.getHeight();
    int xField = 30;
    int yField = 30;
    int margin = 50;
    int boxTopHeight = 100;
    int boxBotHeight = 100;
    int gridHeight = ySize * yField;
    int gridWidth = xSize * xField;
    int difficulty = 1;
    int difficultyOffset = 0;

    PImage zombieNormalImg;
    PImage zombieJumperImg;
    PImage flashImg;
    PImage exitImg;
    PImage remedyImg;
    PImage survivorImg;
    PImage obstacleImg;
    PImage portalImg;

    private void clearBoard() {
        background(20);
    }

    private void drawContinue() {
        fill(100, 50);
        textSize(20);
        textAlign(RIGHT);
        text("[ENTER] to continue", width - 20, height - 20);
    }

    private void drawExit() {
        fill(100, 50);
        textSize(20);
        textAlign(LEFT);
        text("[ESC] to exit", 20, 30);
    }

    private void drawBack() {
        fill(100, 50);
        textSize(20);
        textAlign(LEFT);
        text("[BACKSPACE] to go back", 20, height - 20);
    }

    private void drawBoard(List<Zombie> zombies, List<Survivor> survivors, List<GameElement> fixedObjects) {
        fill(20);
        stroke(40);
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                rect(margin + j * xField, margin + boxTopHeight + i * xField, xField, yField);
                
                for (GameElement e : fixedObjects) {
                    if (e.getX() == j && e.getY() == i) {
                        switch (e.toGameBoard()) {
                            case EXIT -> {
                                image(exitImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                            }
                            case ITEM -> {
                                image(flashImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                            }
                            case OBSTACLE -> {
                                image(obstacleImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                            }
                            case PORTAL -> {
                                image(portalImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                            }
                            case REMEDY -> {
                                image(remedyImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                            }
                            default -> {
                            }
                        }
                    }
                }
                
                for (Survivor s : survivors) {
                    if (s.getX() == j && s.getY() == i) {
                        image(survivorImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                    }
                }

                for (Zombie z : zombies) {
                    if (z.getX() == j && z.getY() == i) {
                        if (z.getType() == ZombieTypes.NORMAL) {
                            image(zombieNormalImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                        } else {
                            image(zombieJumperImg, margin + j * xField + 0.1f * xField, margin + boxTopHeight + i * xField + 0.1f * yField, xField * 0.8f, yField * 0.8f);
                        }

                    }
                }
            }
        }
        drawExit();
    }

    private void drawStatusBar() {
        stroke(0,0,0,0);
        fill(20);
        rect(margin, margin, gridWidth, boxTopHeight);

        textSize(30);
        fill(100, 100);
        textAlign(LEFT);
        // Score
        text("Score: " + board.getScore(), width - margin - 150, margin + boxTopHeight / 2f);

        // Activatable item

        text("Item ready", margin, margin + 25);

        // Active item
        text("Active item", margin, margin + 75);
        fill(20);
        stroke(100);
        rect(margin + 150, margin, 30, 30, 10);
        rect(margin + 150, margin + 50, 30, 30, 10);
        if (board.getActivatableItem() != null) {
            image(flashImg, margin + 150, margin, 30, 30);
            textSize(30);
            fill(100, 100);
            textAlign(LEFT);
            text("[E] to activate", margin + 200, margin + 25);
        }

        if (board.getActiveItem() != null) {
            image(flashImg, margin + 150, margin + 50, 30, 30);
            textSize(30);
            fill(100, 100);
            textAlign(LEFT);
            text(board.getRoundsActive() + " rounds left", margin + 200, margin + 75);
        }
    }

    private void drawBottomBar() {
        stroke(0,0,0,0);
        fill(20);
        rect(margin, margin + boxTopHeight + gridHeight, gridWidth, boxBotHeight);
        fill(100, 100);
        textSize(30);
        textAlign(CENTER);
        text(board.getMessage(), width / 2f, margin + boxTopHeight + gridHeight + 50);
    }

    private void drawMainMenu() {
        background(20);
        fill(255, random(50));
        textSize(64);
        textAlign(CENTER);
        text("Welcome to Zombieland", width / 2f, height / 2f);
        drawContinue();
        drawExit();
    }

    private void drawInfo() {
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
        image(zombieNormalImg, imgStart, 2 * heightPerItem + yOffset, size, size);
        image(zombieJumperImg, imgStart, 3 * heightPerItem + yOffset, size, size);
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

    private void drawDifficulty() {
        background(20);
        fill(100, 100);
        textSize(50);
        textAlign(CENTER);
        text("choose difficulty", width / 2f, 60);
        textSize(20);
        text("(see manual for details, choose with UP and DOWN)", width / 2f, 85);

        fill(100, 100);
        stroke(0,0,0,0);
        triangle(width / 2f - 50, 170 + difficultyOffset, width / 2f - 50, 200 + difficultyOffset, width / 2f - 20, 185 + difficultyOffset);

        textSize(50);
        textAlign(LEFT);
        text("EASY", width / 2f, 200);
        text("MEDIUM", width / 2f, 300);
        text("HARD", width / 2f, 400);
        drawContinue();
        drawBack();
        drawExit();
    }

    private void drawEndMessage() {
        fill(20, 10);
        rect(0,0,width, height);
        fill(255, 10);
        textSize(100);
        textAlign(CENTER);
        String message = "";
        if (gameState == 4) {
            message = "OH NO, YOU LOST...";
        } else if (gameState == 5) {
            message = "YOU WON! " + board.getScore() + " Points!";
        } else {
            message = "ERROR";
        }
        text(message, width / 2f, height / 2f);
        textSize(50);
        text("Press [ESC] to exit or [ENTER] to start new game!", width / 2f, height / 2f + 100);
    }

    public void settings() {
        int x = xSize * xField + 2 * margin;
        int y = ySize * yField + 2 * margin + boxTopHeight + boxBotHeight;
        size(x, y);
    }

    public void setup() {
        background(20);
        zombieNormalImg = loadImage("playfield/images/zombieNormal.png");
        zombieJumperImg = loadImage("playfield/images/zombieJumper.png");
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
                ckeckWinCondition();
                clearBoard();
                drawStatusBar();
                drawBottomBar();
                drawBoard(ZombieGame.getZombies(), ZombieGame.getSurvivors(), ZombieGame.getFixedObjects());
            }
            case 4, 5 -> {
                drawEndMessage();
            }
        }
    }

    public void mousePressed() {

    }

    public void keyPressed() {
        switch (gameState) {
            case 0 -> {
                switch (keyCode) {
                    case 10 -> gameState++;
                    case 27 -> {System.exit(42);}
                }
            }
            case 1 -> {
                switch (keyCode) {
                    case 10 -> gameState++;
                    case 8 -> gameState--;
                    case 27 -> {System.exit(42);}
                }
            }
            case 2 -> {
                switch (keyCode) {
                    case 10 -> {
                        gameState++;
                        ZombieGame.adjustGame(difficulty);
                        ZombieGame.setupGame();
                    }
                    case 8 -> gameState--;
                    case 27 -> {System.exit(42);}
                    case 38 -> {
                        if (difficulty > 1) {
                            difficulty--;
                            difficultyOffset = (difficulty - 1) * 100;
                        }
                    }
                    case 40 -> {
                        if (difficulty < 3) {
                            difficulty++;
                            difficultyOffset = (difficulty - 1) * 100;
                        }
                    }
                }
            }
            case 3 -> {
                switch (keyCode) {
                    case 65 -> {
                        for (Survivor s : ZombieGame.getSurvivors()) {
                            if (s.isValidMove(-1, 0, board, ZombieGame.getObstacles())) {
                                s.move(Direction.LEFT, this.board);
                                try {
                                    ZombieGame.nextRound();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        }
                    }     // A
                    case 68 -> {
                        for (Survivor s : ZombieGame.getSurvivors()) {
                            if (s.isValidMove(1, 0, board, ZombieGame.getObstacles())) {
                                s.move(Direction.RIGHT, this.board);
                                try {
                                    ZombieGame.nextRound();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        }
                    }    // D
                    case 83 -> {
                        for (Survivor s : ZombieGame.getSurvivors()) {
                            if (s.isValidMove(0, 1, board, ZombieGame.getObstacles())) {
                                s.move(Direction.DOWN, this.board);
                                try {
                                    ZombieGame.nextRound();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        }
                    }     // S
                    case 87 -> {
                        for (Survivor s : ZombieGame.getSurvivors()) {
                            if (s.isValidMove(0, -1, board, ZombieGame.getObstacles())) {
                                s.move(Direction.UP, this.board);
                                try {
                                    ZombieGame.nextRound();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        }
                    }     // W
                    case 27 -> {System.exit(42);}                       // ESC
                    case 69 -> {
                        for (Survivor s : ZombieGame.getSurvivors()) {
                            s.activatePowerUp();
                            board.activatePowerUp();
                        }
                    }
                }
            }
            case 4, 5 -> {
                switch (keyCode) {
                    case 10 -> startNewGame();
                    case 27 -> {System.exit(42);}
                }
            }
        }
    }

    private void startNewGame() {
        gameState = 0;
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT, PlayType.WINDOW);
        ZombieGame = new ZGame(board);
    }

    private void ckeckWinCondition() {
        if (ZombieGame.isHasWon()) {
            gameState = 5;
        } else if (ZombieGame.isHasLost()) {
            gameState = 4;
        }
    }

    public static void main(String[] args) {
        PApplet.main("playfield.GameBoard");
    }

}
