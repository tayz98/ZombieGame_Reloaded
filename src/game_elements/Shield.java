package game_elements;

/*
    Protects the player for a specific amount of rounds.
    Symbol has the unicode:
 */
public class Shield extends GameObject {
    public Shield(int xPosition, int yPosition, String color, boolean isCollectible) {
        super(xPosition, yPosition, color, isCollectible);
    }

    @Override
    public String toString() {
        return "\uD83D\uDEE1"; // https://emojipedia.org/shield/
    }

    @Override
    public String toBoard() {
        return null;
    }

    public void protectSurvior() {

    }
}


