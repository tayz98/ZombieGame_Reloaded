package game_elements;

/*
    Ports the survivor to the corresponding other portal.
    What happens if the zombies goes over the Portal? (exception handling maybe)
    Unicode:
 */
public class Portal extends GameObject {
    public Portal(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    @Override
    public String toString() {
        return "\uD83C\uDF00"; // https://emojipedia.org/cyclone/
    }

    @Override
    public String toBoard() {
        return null;
    }

    public void teleport() {

    }
}