package Entity.Order;

/**
 * The Payment interface represents the payment process for an order.
 *
 * @author Reuben Farrel, Saffron
 */

public interface Payment {
    /**
     * Processes the payment for an order.
     * 
     * @param amount The amount to be processed for payment.
     * @return true if the payment is successfully processed, false otherwise.
     */
    public abstract boolean processPayment(double amount);
}
