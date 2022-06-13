package game_elements;

import java.util.List;

public class Zombie extends Character {

    // kann weg?
    public Zombie(int xPosition, int yPosition, String color, boolean alive, List<GameElement> zombies, List<GameElement> allElements) {
        super(xPosition, yPosition, color, alive);
        zombies.add(this);
        allElements.add(this);
    }

    public Zombie(List<Zombie> zombies, List<GameElement> allElements, int width, int height) {
        super(allElements, width, height);
        zombies.add(this);
    }

    private boolean isAlive; // if the zombies dies (because of an item), it will disappear from the board.
    private boolean isSleeping;

    @Override
    public void move() {

    }
    public void attack() {
    // wenn der zombie in der gleichen Position wie der Survivor ist, wird ein Attack ausgeführt.
        // jedoch kann der Spieler einen Angriff überleben, wenn dieser z. B. ein Schild hat.
    }

    @Override
    public String toString() {
        return GameElementEnums.ZOMBIE.toString();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toBoard() {
        return "Z";
    }

    @Override
    public int calculateDistanceToExit(Exit exit) {
        return (int) Math.max(Math.abs(exit.getX() - this.getX()), Math.abs(exit.getY() - this.getY()));
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }
}
