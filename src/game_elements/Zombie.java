package game_elements;

public class Zombie extends Character {

    public Zombie(int xPosition, int yPosition, String color, boolean alive) {
        super(xPosition, yPosition, color, alive);
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
    public String ToString() {
        return "Z";
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }
}