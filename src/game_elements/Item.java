package game_elements;

import enums.ItemTypes;
import playfield.Board;
import java.util.List;

public class Item extends GameObject {

    ItemTypes type;

    public Item(List<Item> items, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        items.add(this);
        fixedObjects.add(this);
        this.setType(ItemTypes.randomItemType());
    }

    public ItemTypes getType() {
        return type;
    }

    public void setType(ItemTypes type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public String toBoard() {
        switch (this.type) {
            case STUN_GUN -> {
                return "G";
            }
            case SHIELD -> {
                return  "O";
            }
            case FLASH -> {
                return "F";
            }
            case FIRE_GUN -> {
                return "W";
            }
            default -> {
                return "";
            }
        }
    }
}
