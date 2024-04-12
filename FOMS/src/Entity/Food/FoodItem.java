package Entity.Food;

public class FoodItem implements IFoodItem{

    String name;
    double price;
    String branch;
    ItemType itemType;
    String description;

    public FoodItem(String _n, double _p, String _bt, ItemType _it)
    {
        this.name = _n;
        this.price = _p;
        this.branch = _bt;
        this.itemType = _it;
        this.description = "No item description.";
    }

    public void setDescription(String _d)
    {
        this.description = _d;
    }




}
