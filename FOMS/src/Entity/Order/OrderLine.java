package Entity.Order;

import Entity.Food.FoodItem;

public class OrderLine {
    private FoodItem item;
	private int itemQuantity;
	private Customisation custom;
	private String customOrder;

	//Constructor
	public OrderLine(FoodItem _f, int _q, Customisation _c, String _co)
	{
		this.item = _f;
		this.itemQuantity = _q;
		this.custom = _c;
		this.customOrder = _co;
	}
	
	//Accessors and mutators
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
