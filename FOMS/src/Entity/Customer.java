package Entity;

public class Customer {
    private int orderID;
	private String location;

	//Constructor
	public Customer(int _oID, String _b)
	{
		this.orderID = _oID;
		this.location = _b;
	} 

	//Accessors and Mutators
	public int getOrderID()
	{
		return this.orderID;
	}
	
	public String getLoc()
	{
		return this.location;
	}

}
