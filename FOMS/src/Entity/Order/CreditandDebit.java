package Entity.Order;

public class CreditandDebit implements Payment {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit/debit card payment of $" + amount);
        return true;
    }

}
