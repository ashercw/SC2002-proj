package Entity;

public class Customer {
    private int orderID;
	private BranchType location;

	//Constructor
	public Customer(int _oID, BranchType _b)
	{
		this.orderID = _oID;
		this.location = _b;
	} 

	//Accessors and Mutators
	public int getOrderID()
	{
		return this.orderID;
	}
	
	public BranchType getLoc()
	{
		return this.location;
	}

}
