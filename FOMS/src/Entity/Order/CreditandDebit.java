package Entity.Order;

/**
 * Represents a credit or debit card payment method.
 */

public class CreditandDebit implements Payment {
    
    /**
     * Processes a credit or debit card payment.
     *
     * @param amount The amount to be paid.
     * @return True if the payment is successfully processed, otherwise false.
     */

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit/debit card payment of $" + amount);
        return true;
    }

}
