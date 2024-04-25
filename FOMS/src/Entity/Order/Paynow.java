package Entity.Order;

public class Paynow implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing Paynow payment of $" + amount);
        return true;
    }
}
