package enums;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ItemTypes {
    SHIELD,
    FLASH,
    STUN_GUN,
    FIRE_GUN;

    private static final List<ItemTypes> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static ItemTypes randomItemType()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
