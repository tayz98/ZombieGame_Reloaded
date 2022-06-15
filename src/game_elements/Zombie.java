package game_elements;

import enums.ZombieTypes;
import playfield.Board;
import java.util.List;

public class Zombie extends GameCharacter {

    private boolean isAlive = true; // if the zombies dies (because of an item), it will disappear from the board.
    private int roundsToNextMove = 0;
    private ZombieTypes type;


    public Zombie(List<Zombie> zombies, List<GameElement> allElements, Board board, int roundsToNextMove) {
        super(allElements, board);
        zombies.add(this);
        this.setType(ZombieTypes.randomItemType());
        if (this.type == ZombieTypes.JUMPER && roundsToNextMove == 0) {
            this.setRoundsToNextMove(1);
        } else {
            this.setRoundsToNextMove(roundsToNextMove);
        }
    }

    public Zombie(List<Zombie> zombies, List<GameElement> allElements, Board board, int roundsToNextMove, ZombieTypes type) {
        super(allElements, board);
        zombies.add(this);
        this.setType(type);
        if (this.type == ZombieTypes.JUMPER && roundsToNextMove == 0) {
            this.setRoundsToNextMove(1);
        } else {
            this.setRoundsToNextMove(roundsToNextMove);
        }
    }

    public void attack() {
    // wenn der zombie in der gleichen Position wie der Survivor ist, wird ein Attack ausgeführt.
        // jedoch kann der Spieler einen Angriff überleben, wenn dieser z. B. ein Schild hat.
    }

    public void setType(ZombieTypes type) {
        this.type = type;
    }

    // übleradene Funktion
    public int distanceToSurvivor(Survivor survivor) {
        return (int) Math.max(Math.abs(this.getX() - survivor.getX()), Math.abs(this.getY() - survivor.getY()));
    }

    // übleradene Funktion
    public int distanceToSurvivor(Survivor survivor, String direction) {
        switch(direction) {
            case "x" -> {
                return (int) Math.abs(this.getX() - survivor.getX());
            }
            case "y" -> {
                return (int) Math.abs(this.getY() - survivor.getY());
            }
            default -> {
                return 0;
            }
        }
    }

    public int getRoundsToNextMove() {
        return roundsToNextMove;
    }

    public void setRoundsToNextMove(int roundsToNextMove) {
        this.roundsToNextMove = roundsToNextMove;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void decreaseRoundsToNextMove() {
        this.roundsToNextMove--;
    }

    private boolean isValidMove(int x, int y, List<GameElement> fixedObjects) {
        for (GameElement e : fixedObjects) {
            if (e.getX() == x && e.getY() == y) {
                return false;
            }
        }
        return true;
    }

    public void move(List<Survivor> survivors, List<GameElement> fixedObjects) throws Exception {
        Survivor nearestSurvivor = null;
        int distance = 0, x, y;
        try {
            // den nähsten Spieler herausfinden
            for (Survivor s : survivors) {
                if (distance == 0 || this.distanceToSurvivor(s) < distance) {
                    distance = this.distanceToSurvivor(s);
                    nearestSurvivor = s;
                }
            }
            x = (int) this.getX();
            y = (int) this.getY();

            switch (this.type) {
                case NORMAL -> {
                    int numPossibilities = 1;
                    if (this.distanceToSurvivor(nearestSurvivor, "x") > 0 && this.distanceToSurvivor(nearestSurvivor, "y") > 0) {
                        numPossibilities = 3;
                    }
                    // Bewegung in x-Richtung
                    if (x < nearestSurvivor.getX()) {
                        x++;
                    } else if (x > nearestSurvivor.getX()) {
                        x--;
                    }
                    // Bewegung in y-Richtung
                    if (y < nearestSurvivor.getY()) {
                        y++;
                    } else if (y > nearestSurvivor.getY()) {
                        y--;
                    }
                    if (isValidMove(x, y, fixedObjects)) {
                        this.setLocation(x, y);
                        break;
                    } else if (numPossibilities > 1) {
                        if (isValidMove(x, (int) this.getY(), fixedObjects)) {
                            this.setLocation(x, (int) this.getY());
                            break;
                        } else if (numPossibilities > 2) {
                            if (isValidMove((int) this.getX(), y, fixedObjects)) {
                                this.setLocation((int) this.getX(), y);
                                break;
                            }
                        }
                    }
                }
                case JUMPER -> {
                    int deltaX = (int) (nearestSurvivor.getX() - this.getX());
                    int deltaY = (int) (nearestSurvivor.getY() - this.getY());

                    do {
                        if (Math.abs(deltaX) > Math.abs(deltaY)) {
                            if (deltaX < 0) {
                                if (isValidMove((int) this.getX() + Math.max(-3, deltaX), (int) this.getY(), fixedObjects)) {
                                    this.translate(Math.max(-3, deltaX), 0);
                                    break;
                                } else {
                                    deltaX++;
                                }
                            } else {
                                if (isValidMove((int) this.getX() + Math.min(3, deltaX), (int) this.getY(), fixedObjects)) {
                                    this.translate(Math.min(3, deltaX), 0);
                                    break;
                                } else {
                                    deltaX--;
                                }
                            }
                        } else {
                            if (deltaY < 0) {
                                if (isValidMove((int) this.getX(), (int) this.getY() + Math.max(-3, deltaY), fixedObjects)) {
                                    this.translate(0, Math.max(-3, deltaY));
                                    break;
                                } else {
                                    deltaY++;
                                }
                            } else {
                                if (isValidMove((int) this.getX(), (int) this.getY() + Math.min(3, deltaY), fixedObjects)) {
                                    this.translate(0, Math.min(3, deltaY));
                                    break;
                                } else {
                                    deltaY--;
                                }
                            }
                        }
                    } while(deltaX != 0 || deltaY != 0);
                    this.roundsToNextMove = 1;
                }
            }
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }
    }

    @Override
    public String toBoard() {
        switch(this.type) {
            case NORMAL -> {
                return "Z";
            }
            case JUMPER -> {
                return "ZJ";
            }
            default -> {
                return "";
            }
        }
    }

    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) Math.max(Math.abs(exit.getX() - this.getX()), Math.abs(exit.getY() - this.getY()));
    }
}
