package Entity.Food;

public class FoodItem implements IFoodItem{

    String name;
    double price;
    String branch;
    ItemType itemType;
    String description;

    public FoodItem()
    {}

    public FoodItem(String _n, double _p, String _bt, ItemType _it)
    {
        this.name = _n;
        this.price = _p;
        this.branch = _bt;
        this.itemType = _it;
        this.description = "No item description.";
    }

    public FoodItem(String _n, double _p, String _bt, ItemType _it, String _desc)
    {
        this.name = _n;
        this.price = _p;
        this.branch = _bt;
        this.itemType = _it;
        this.description = _desc;
    }

    public String getFoodItemName()
    {
        return this.name;
    }

    public double getFoodItemPrice()
    {
        return this.price;
    }

    public String getFoodItemBranch()
    {
        return this.branch;
    }

    public ItemType getFoodItemType()
    {
        return this.itemType;
    }

    public String getFoodItemDesc()
    {
        return this.description;
    }

    public void setDescription(String _d)
    {
        this.description = _d;
    }

    public double setPrice(double price)
    {
        return this.price;
    }



    public String getName() {
        return name;
    }
}
