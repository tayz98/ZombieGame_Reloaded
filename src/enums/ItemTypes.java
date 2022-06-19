/**
 * @name ItemTypes
 * @package enums
 * @file ItemTypes.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description Items are categorized into types. So far only the flash item is in use.
 */

package enums;

import java.util.List;
import java.util.Random;

public enum ItemTypes {
    FLASH;

    private static final List<ItemTypes> VALUES = List.of(values());    // list with all item types.
    private static final int SIZE = VALUES.size();                      // contains the amount of items in the values list.
    private static final Random RANDOM = new Random();

    // getter for returning a random item.
    public static ItemTypes randomItemType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
