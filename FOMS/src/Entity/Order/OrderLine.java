package Entity.Order;

import Entity.Food.FoodItem;

public class OrderLine {
    private FoodItem item;
	private int itemQuantity;
	private Customisation custom;
	private String customOrder;
	
	public FoodItem getItem()
	{
		return this.item;
	}
	
	public int getitemQuanity()
	{
		return this.itemQuantity;
	}
	
	public Customisation getCustom()
	{
		return this.custom;
	}
	
	public String getCustomOrder()
	{
		return this.customOrder;
	}
}
