package Entity;

/**
 * The Customer class represents a customer entity.
 */
public class Customer {
    private int orderID;
	private String location;

	/**
     * Constructs a new Customer object with the specified order ID and location.
     *
     * @param _oID The order ID of the customer
     * @param _b   The location of the customer
     */
	public Customer(int _oID, String _b)
	{
		this.orderID = _oID;
		this.location = _b;
	} 

	/**
     * Retrieves the order ID of the customer.
     *
     * @return The order ID of the customer
     */
	public int getOrderID()
	{
		return this.orderID;
	}
	
	/**
     * Retrieves the location of the customer.
     *
     * @return The location of the customer
     */
	public String getLoc()
	{
		return this.location;
	}

}
