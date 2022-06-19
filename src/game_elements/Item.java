/**
 * @package game_elements
 * @file Item.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Item class inherits from GameObject. It can be used by the survivor to get an advantage.
 */


package game_elements;

import enums.GameElements;
import enums.ItemTypes;
import playfield.Board;
import java.util.List;

public class Item extends GameElement {

    /**
     * * An Item can have these types:
     *      *  -> STUN GUN (...)
     *      *  -> SHIELD (protects the player...)
     *      *  -> FLASH (....)
     */
    ItemTypes type;

    // Item adds itself to the list of items, allElements, fixedObjects.
    public Item(List<Item> items, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        items.add(this);
        fixedObjects.add(this);
        this.setType(ItemTypes.randomItemType());
    }

    // getter and setter methods:
    public ItemTypes getType() {
        return type;
    }

    public void setType(ItemTypes type) {
        this.type = type;
    }

    @Override
    public String toBoard() {
        switch (this.type) {
            /*
            case STUN_GUN -> {
                return "G";
            } */
            /* case SHIELD -> {
                return  "O";
            } */
            case FLASH -> {
                return "F";
            }
            /* case FIRE_GUN -> {
                return "W";
            } */
            default -> {
                return "";
            }
        }
    }

    @Override
    public GameElements toGameBoard() {
        return GameElements.ITEM;
    }
}
