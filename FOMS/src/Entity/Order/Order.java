package Entity.Order;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import Entity.Food.FoodItem;

public class Order implements Serializable{
    private OrderStatus orderStatus;
	private OrderType orderType;
	private double totalPrice;
	private ArrayList orderItemsList;
	private int orderQuantity;
	private int orderID;
	private String branchName;
	private static int totalOrder = 0;
	private LocalTime placeOrderTime;
	private LocalTime expiry;
	private static final int DISCARDTIME = 1;
	
	
	//constructor
	public Order(OrderStatus _os, OrderType _ot, double _tp, ArrayList _orderLineList, int _oq, String _bn)
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

	public void setplaceOrderTime()
	{
		this.placeOrderTime = LocalTime.now();
		this.expiry = placeOrderTime.plus(DISCARDTIME, ChronoUnit.MINUTES);
	}

	public boolean hasExpired()
	{
		boolean isbefore = this.placeOrderTime.isBefore(this.expiry);
		if(!isbefore)
		{
			this.setOrderStatus(OrderStatus.DISCARDED);
		}
		return isbefore;
	}

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
	
	public ArrayList getOrderLine()
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

	public void setOrderQuant(int orderQuant)
	{
		this.orderQuantity = orderQuant;
	}
}
