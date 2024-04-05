package Entity.Order;

public class Order {
    private OrderStatus orderStatus;
	private OrderType orderType;
	private double totalPrice;
	private OrderLine orderItems;
	private int orderQuantity;
	private int orderID;
	private static int totalOrder = 0;
	
	
	//constructor
	public Order(OrderStatus _os, OrderType _ot, double _tp, OrderLine _oi, int _oq, int _oid)
	{
		this.orderStatus = _os;
		this.orderType = _ot;
		this.totalPrice = _tp;
		this.orderItems = _oi;
		this.orderQuantity = _oq;
		this.orderID = ++totalOrder;
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
	
	public OrderLine getOrderLine()
	{
		return this.orderItems;
	}
	
	public int getOrderQuantity()
	{
		return this.orderQuantity;
	}
	
	public int getOrderID()
	{
		return this.orderID;
	}

}
