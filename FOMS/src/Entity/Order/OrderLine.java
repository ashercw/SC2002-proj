package Entity.Order;

import java.io.Serializable;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;

public class OrderLine implements Serializable{
    //private String itemName;
    //private ItemType itemType;
    private int itemQuantity;
    private String custom;
    private double itemTotal;

    private FoodItem item;

    public OrderLine(FoodItem item, int itemQuantity, String custom) {
        this.item = item;
        //this.itemType = itemType;
        this.itemQuantity = itemQuantity;
        this.custom = custom;
        this.itemTotal = itemQuantity * item.getFoodItemPrice();
    }

    /*public OrderLine(String _f, int _q, ItemType itemType, String custom) {
        this.itemName = _f;
        this.itemType = itemType;
        this.itemQuantity = _q;
        this.custom = custom;
    }*/

    public FoodItem getItem()
    {
        return this.item;
    }
    public double getItemTotPrice()
    {
        return this.itemTotal;
    }

    /*public String getItem() {
        return this.itemName;
    }*/

    /*public ItemType getItemType() {
        return this.itemType;
    }*/

    /*public void setItem(String item) {
        this.itemName = item;
    }*/

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getCustomisation() {
        return this.custom;
    }

    public void setCustomisation(String custom) {
        this.custom = custom;
    }

    public void printOrderLine(OrderLine orderLine)
    {
        System.out.println("Item: " + orderLine.getItem().getFoodItemName() + " | Quanity: " 
        + orderLine.getItemQuantity() + " | Customisation: " + orderLine.getCustomisation());
    }
}
