package Entity.Order;

import Entity.Food.FoodItem;

public class OrderLine {
    private FoodItem item;
    private int itemQuantity;
    private Customisation custom;
    private int orderId;

    public OrderLine(FoodItem _f, int _q, Customisation _c) {
        this.item = _f;
        this.itemQuantity = _q;
        this.custom = _c;
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

    public Customisation getCustomisation() {
        return this.custom;
    }

    public void setCustomisation(Customisation custom) {
        this.custom = custom;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
