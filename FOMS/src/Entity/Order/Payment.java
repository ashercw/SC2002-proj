package Entity.Order;

/**
 * @author Reuben Farrel, Saffron
 */

public interface Payment {
    public abstract boolean processPayment(double amount);
}
