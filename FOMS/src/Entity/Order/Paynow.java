package Entity.Order;
/**
 * Represents a payment method using the Paynow service.
 * Implements the Payment interface.
 */
public class Paynow implements Payment {
    /**
     * Processes a payment transaction using the Paynow service.
     * 
     * @param amount The amount to be paid.
     * @return true if the payment is successfully processed, false otherwise.
     */
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Paynow payment of $" + amount);
        return true;
    }
}
