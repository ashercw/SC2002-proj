package Entity.Order;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import Entity.Food.FoodItem;

/**
 * Represents an order placed in the restaurant.
 * This class contains details such as order status, type, total price, items ordered,
 * quantity, ID, branch name, and order placement time.
 */

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
	
	/**
     * Constructor for creating an order.
     * @param _os The status of the order.
     * @param _ot The type of order.
     * @param _tp The total price of the order.
     * @param _orderLineList The list of items in the order.
     * @param _oq The quantity of the order.
     * @param _bn The branch name where the order is placed.
     */
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
	
/**
     * Sets the order placement time and expiry time.
     */

	public void setplaceOrderTime()
	{
		this.placeOrderTime = LocalTime.now();
		this.expiry = placeOrderTime.plus(DISCARDTIME, ChronoUnit.MINUTES);
	}

	/**
     * Checks if the order has expired.
     * @return True if the order has not expired, false otherwise.
     */
	public boolean hasExpired()
	{
		boolean isbefore = this.placeOrderTime.isBefore(this.expiry);
		if(!isbefore)
		{
			this.setOrderStatus(OrderStatus.DISCARDED);
		}
		return isbefore;
	}

	/**
     * Retrieves the order status.
     * @return The status of the order.
     */
	public OrderStatus getOrderStatus()
	{
		return this.orderStatus;
	}
	
	/**
     * Retrieves the order type.
     * @return The type of the order.
     */
	public OrderType getOrderType()
	{
		return this.orderType;
	}
	
	/**
     * Retrieves the total price of the order.
     * @return The total price of the order.
     */
	public double getTotalPrice()
	{
		return this.totalPrice;
	}

	/**
     * Sets the total price of the order.
     * @param totalPrice The total price to be set.
     */
	public double setTotalPrice(double totalPrice)
	{
		return this.totalPrice = totalPrice;
	}
	
	/**
     * Retrieves the list of items in the order.
     * @return The list of items in the order.
     */
	public ArrayList getOrderLine()
	{
		return this.orderItemsList;
	}
	
	/**
     * Retrieves the quantity of the order.
     * @return The quantity of the order.
     */
	public int getOrderQuantity()
	{
		return this.orderQuantity;
	}
	
	/**
     * Retrieves the ID of the order.
     * @return The ID of the order.
     */
	public int getOrderID()
	{
		return this.orderID;
	}

	/**
     * Retrieves the branch name where the order is placed.
     * @return The branch name.
     */
	public String getBranchName()
	{
		return this.branchName;
	}

	/**
     * Retrieves the total number of orders.
     * @return The total number of orders.
     */
	public int getTotalOrder()
	{
		return this.totalOrder;
	}

    /**
     * Adds an item to the order.
     * @param newItem The item to be added to the order.
     */
	public void addItem(OrderLine newItem) {
        orderItemsList.add(newItem);
    }
    
	/**
     * Sets the status of the order.
     * @param orderStatus The status to be set.
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

	/**
     * Sets the quantity of the order.
     * @param orderQuant The quantity to be set.
     */
	public void setOrderQuant(int orderQuant)
	{
		this.orderQuantity = orderQuant;
	}
}
