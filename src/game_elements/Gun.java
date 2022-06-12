package game_elements;

/*
    Gun kills a random or specific (maybe the nearest/most dangerous) zombie
    Symbol has the unicode:
 */
public class Gun extends GameObject {

    public Gun(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    @Override
    public String ToString() {
        return "\uD83D\uDD2B"; // https://unicode-table.com/de/1F52B/
    }
    public void shoot() {
        // kill the nearest zombie
    }
}
