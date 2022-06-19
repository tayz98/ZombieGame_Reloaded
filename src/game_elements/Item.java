/**
 * @package game_elements
 * @file Item.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description: The Item class inherits from GameElement. It can be used by the survivor to get an advantage.
 */

package game_elements;

import enums.GameElements;
import enums.ItemTypes;
import playfield.Board;
import java.util.List;

public class Item extends GameElement {

    ItemTypes type;

    /**
     * Constructs an Item element and gets a list of all instantiated elements and the board. <p>
     * Adds created item to allElements, items and fixedObjects.
     * @param allElements   list of all elements in the game.
     * @param board         instance of the created board.
     * @param items         list of all items.
     * @param fixedObjects  list of all fixed (non-movable) elements.
     */
    public Item(List<Item> items, List<GameElement> allElements, List<GameElement> fixedObjects, Board board) {
        super(allElements, board);
        items.add(this);
        fixedObjects.add(this);
        this.setType(ItemTypes.randomItemType());
    }

    /**
     * Returns the type of the item.
     * @return type of item.
     */
    public ItemTypes getType() {
        return type;
    }

    /**
     * Sets the type of the item.
     * @param type  type of the item.
     */
    public void setType(ItemTypes type) {
        this.type = type;
    }

    /**
     * Method returns a String for the game in terminal-use.
     * @return character to display on the board in terminal-use.
     */
    @Override
    public String toBoard() {
        switch (this.type) {
            case FLASH -> {
                return "F";
            }
            default -> {
                return "";
            }
        }
    }

    /**
     * Method to return the GameElement type.
     * @return the GameElement type.
     */
    @Override
    public GameElements toGameBoard() {
        return GameElements.ITEM;
    }
}
