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

    public ZombieTypes getType() {
        return type;
    }

    public void setType(ZombieTypes type) {
        this.type = type;
    }

    public int distanceToSurvivor(Survivor survivor) {
        return (int) Math.max(Math.abs(this.getX() - survivor.getX()), Math.abs(this.getY() - survivor.getY()));
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

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void decreaseRoundsToNextMove() {
        this.roundsToNextMove--;
    }

    public void move(List<Survivor> survivors) throws Exception {
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
                    this.setLocation(x, y);
                }
                case JUMPER -> {
                    int xDistance = (int) (nearestSurvivor.getX() - this.getX());
                    int yDistance = (int) (nearestSurvivor.getY() - this.getY());
                    if (Math.abs(xDistance) > Math.abs(yDistance)) {
                        if (xDistance < 0) {
                            this.translate(Math.max(-3, xDistance), 0);
                        } else {
                            this.translate(Math.min(3, xDistance), 0);
                        }
                    } else {
                        if (yDistance < 0) {
                            this.translate(0, Math.max(-3, yDistance));
                        } else {
                            this.translate(0, Math.min(3, yDistance));
                        }
                    }
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
