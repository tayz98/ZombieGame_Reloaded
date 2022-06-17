package enums;

import java.util.List;
import java.util.Random;

public enum ZombieTypes {
    NORMAL,
    JUMPER;

    private static final List<ZombieTypes> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ZombieTypes randomItemType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}

