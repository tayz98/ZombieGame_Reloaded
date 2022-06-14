package game_elements;

import playfield.Board;

import java.util.List;

public class Zombie extends GameCharacter {

    private boolean isAlive = true; // if the zombies dies (because of an item), it will disappear from the board.
    private int roundsToNextMove = 0;


    public Zombie(List<Zombie> zombies, List<GameElement> allElements, Board board, int roundsToNextMove) {
        super(allElements, board);
        zombies.add(this);
        this.setRoundsToNextMove(roundsToNextMove);
    }

    public void attack() {
    // wenn der zombie in der gleichen Position wie der Survivor ist, wird ein Attack ausgeführt.
        // jedoch kann der Spieler einen Angriff überleben, wenn dieser z. B. ein Schild hat.
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
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }
    }

    @Override
    public String toBoard() {
        return "Z";
    }

    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) Math.max(Math.abs(exit.getX() - this.getX()), Math.abs(exit.getY() - this.getY()));
    }
}
