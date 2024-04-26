package Entity.Food;

import java.io.Serializable;

/**
 * Enum representing different types of items in a food menu.
 * This enum includes items such as burgers, set meals, drinks, and sides.
 * It implements Serializable to allow its instances to be serialized.
 */

public enum ItemType implements Serializable{
    BURGER,
	SETMEAL,
	DRINK,
	SIDE
}

