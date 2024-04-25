package Entity.Order;

import java.util.List;

import Entity.Food.FoodItem;

public class Order {
    private OrderStatus orderStatus;
	private OrderType orderType;
	private double totalPrice;
	private List<OrderLine> orderItemsList;
	private int orderQuantity;
	private int orderID;
	private String branchName;
	private static int totalOrder = 0;
	
	
	//constructor
	public Order(OrderStatus _os, OrderType _ot, double _tp, List<OrderLine> _orderLineList, int _oq, String _bn)
	{
		this.orderStatus = _os;
		this.orderType = _ot;
		this.totalPrice = _tp;
		this.orderItemsList = _orderLineList;
		this.orderQuantity = _oq;
		this.orderID = ++totalOrder;
		this.branchName = _bn;
	}
	
	//Accessors and mutators
	public OrderStatus getOrderStatus()
	{
		return this.orderStatus;
	}
	
	public OrderType getOrderType()
	{
		return this.orderType;
	}
	
	public double getTotalPrice()
	{
		return this.totalPrice;
	}

	public double setTotalPrice(double totalPrice)
	{
		return this.totalPrice = totalPrice;
	}
	
	public List<OrderLine> getOrderLine()
	{
		return this.orderItemsList;
	}
	
	public int getOrderQuantity()
	{
		return this.orderQuantity;
	}
	
	public int getOrderID()
	{
		return this.orderID;
	}

	public String getBranchName()
	{
		return this.branchName;
	}

	public int getTotalOrder()
	{
		return this.totalOrder;
	}


	public void addItem(OrderLine newItem) {
        orderItemsList.add(newItem);
    }
    
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
