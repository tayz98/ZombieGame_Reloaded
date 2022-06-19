/**
 * @name ZombieTypes
 * @package enums
 * @file ZombieTypes.java
 * @version 1.0
 * @authors Veronica Zylla, Sören Zacharias, Alexander Nachtigal
 * @email veronica.zylla@student.fh-kiel.de, soeren.zacharias@student.fh-kiel.de, alexander.nachtigal@student.fh-kiel.de
 * @description Zombies are categorized into types. A jumper and a normal zombie exist in this game so far.
 */

package enums;

import java.util.List;
import java.util.Random;

public enum ZombieTypes {
    NORMAL,
    JUMPER;

    // initializing a list with ZombieTypes and its size.
    private static final List<ZombieTypes> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ZombieTypes randomItemType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

