package game_elements;

/*
    Ports the survivor to the corresponding other portal.
    What happens if the zombies goes over the Portal? (exception handling maybe)
    Unicode:
 */
public class Portal extends Object{
    public Portal(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    @Override
    public String ToString() {
        return "\uD83C\uDF00"; // https://emojipedia.org/cyclone/
    }

    public void teleport() {

    }
}