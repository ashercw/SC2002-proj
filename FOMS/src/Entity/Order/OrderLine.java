package Entity.Order;

import Entity.Food.FoodItem;

public class OrderLine {
    private FoodItem item;
    private int itemQuantity;
    private String custom;
    private int orderId;

    public OrderLine(FoodItem _f, int _q, String custom) {
        this.item = _f;
        this.itemQuantity = _q;
        this.custom = custom;
    }

    public FoodItem getItem() {
        return this.item;
    }

    public void setItem(FoodItem item) {
        this.item = item;
    }

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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
