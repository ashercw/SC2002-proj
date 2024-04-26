package Entity.Order;

import java.io.Serializable;

import Entity.Food.FoodItem;
import Entity.Food.ItemType;

/**
 * Represents a line item in an order.
 * Each line item contains a food item, quantity, customizations, and total price.
 */

public class OrderLine implements Serializable{
    //private String itemName;
    //private ItemType itemType;
    private int itemQuantity;
    private String custom;
    private double itemTotal;

    private FoodItem item;

    /**
     * Constructs an OrderLine object with the specified food item, quantity, and customizations.
     * @param item The food item for this order line.
     * @param itemQuantity The quantity of the food item.
     * @param custom The customizations for the food item.
     */

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


     /**
     * Retrieves the food item associated with this order line.
     * @return The food item.
     */
    public FoodItem getItem()
    {
        return this.item;
    }

    /**
     * Retrieves the total price for this order line.
     * @return The total price.
     */
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


    /**
     * Retrieves the quantity of the food item in this order line.
     * @return The quantity.
     */

    public int getItemQuantity() {
        return this.itemQuantity;
    }

    /**
     * Sets the quantity of the food item in this order line.
     * Recalculates the total price accordingly.
     * @param itemQuantity The new quantity.
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
        this.itemTotal = this.itemQuantity*(getItem().getFoodItemPrice());
    }

    /**
     * Retrieves the customizations for the food item in this order line.
     * @return The customizations.
     */
    public String getCustomisation() {
        return this.custom;
    }

    /**
     * Sets the customizations for the food item in this order line.
     * @param custom The new customizations.
     */
    public void setCustomisation(String custom) {
        this.custom = custom;
    }

    /**
     * Prints details of the order line.
     * @param orderLine The order line to be printed.
     */
    public void printOrderLine(OrderLine orderLine)
    {
        System.out.println("Item: " + orderLine.getItem().getFoodItemName() + " | Quanity: " 
        + orderLine.getItemQuantity() + " | Customisation: " + orderLine.getCustomisation());
    }
}
