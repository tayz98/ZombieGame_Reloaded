package game_elements;

import playfield.Board;

import java.util.List;

public class Zombie extends GameCharacter {

    // kann weg?
    public Zombie(int xPosition, int yPosition, String color, boolean alive, List<GameElement> zombies, List<GameElement> allElements) {
        super(xPosition, yPosition, color, alive);
        zombies.add(this);
        allElements.add(this);
    }

    public Zombie(List<Zombie> zombies, List<GameElement> allElements, Board board) {
        super(allElements, board);
        zombies.add(this);
    }

    private boolean isAlive; // if the zombies dies (because of an item), it will disappear from the board.
    private boolean isSleeping;


    public void attack() {
    // wenn der zombie in der gleichen Position wie der Survivor ist, wird ein Attack ausgeführt.
        // jedoch kann der Spieler einen Angriff überleben, wenn dieser z. B. ein Schild hat.
    }

    public int distanceToSurvivor(Survivor survivor) {
        return (int) Math.max(Math.abs(this.getX() - survivor.getX()), Math.abs(this.getY() - survivor.getY()));
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

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }
}

/*
    public static void moveZombies(final Point survivor, final List<Point> zombies) throws Exception {
        // Der Zombie bewegt sich immer in Richtung der x- und y-Koordinate des Spielers
        // da er diagonal gehen kann, werden beide Richtungen ausgewertet
        try {
            for (Point z : zombies) {
                int x = (int) z.getX();
                int y = (int) z.getY();

                // Bewegung in x-Richtung
                if (x < survivor.getX()) {
                    x++;
                } else if (x > survivor.getX()) {
                    x--;
                }
                // Bewegung in y-Richtung
                if (y < survivor.getY()) {
                    y++;
                } else if (y > survivor.getY()) {
                    y--;
                }
                z.setLocation(x, y);
            }
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }

    }

 */
