package Entity.Food;

import java.io.Serializable;

/**
 * Represents a food item in the system.
 * Implements the IFoodItem interface and provides details such as name, price, branch, and item type.
 */

public class FoodItem implements IFoodItem, Serializable{

    String name;
    double price;
    String branch;
    ItemType itemType;
    String description;

    public FoodItem()
    {}
    /**
     * Constructor to create a food item with initial values but with default description.
     * @param _n The name of the food item.
     * @param _p The price of the food item.
     * @param _bt The branch where the food item is available.
     * @param _it The type of the food item.
     */

    public FoodItem(String _n, double _p, String _bt, ItemType _it)
    {
        this.name = _n;
        this.price = _p;
        this.branch = _bt;
        this.itemType = _it;
        this.description = "No item description.";
    }

    /**
     * Constructor to create a food item with initial values including description.
     * @param _n The name of the food item.
     * @param _p The price of the food item.
     * @param _bt The branch where the food item is available.
     * @param _it The type of the food item.
     * @param _desc The description of the food item.
     */

    public FoodItem(String _n, double _p, String _bt, ItemType _it, String _desc)
    {
        this.name = _n;
        this.price = _p;
        this.branch = _bt;
        this.itemType = _it;
        this.description = _desc;
    }

    /**
     * Returns the name of the food item.
     * @return Name of the food item.
     */

    public String getFoodItemName()
    {
        return this.name;
    }

    /**
     * Returns the price of the food item.
     * @return Price of the food item.
     */

    public double getFoodItemPrice()
    {
        return this.price;
    }

    /**
     * Returns the branch where the food item is available.
     * @return Branch of the food item.
     */

    public String getFoodItemBranch()
    {
        return this.branch;
    }

    /**
     * Sets the branch of the food item.
     * @param _bt New branch name.
     */

    public void setFoodItemBranch(String _bt){
        this.branch = _bt;
    }

    /**
     * Returns the type of the food item.
     * @return Type of the food item.
     */

    public ItemType getFoodItemType()
    {
        return this.itemType;
    }

    /**
     * Sets the type of the food item.
     * @param _it New item type.
     */

    public void setFoodItemType(ItemType _it){
        this.itemType = _it;
    }

    /**
     * Returns the description of the food item.
     * @return Description of the food item.
     */

    public String getFoodItemDesc()
    {
        return this.description;
    }

    /**
     * Sets the description of the food item.
     * @param _d New description.
     */

    public void setDescription(String _d)
    {
        this.description = _d;
    }

    /**
     * Sets the price of the food item.
     * @param price New price.
     */

    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Sets the name of the food item.
     * @param _n New name.
     */

    public void setFoodItemName(String _n){
        this.name = _n;
    }

    /**
     * Gets the name of the food item.
     * @return Current name.
     */

    public String getName() {
        return name;
    }
}
