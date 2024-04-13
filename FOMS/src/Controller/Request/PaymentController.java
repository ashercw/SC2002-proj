package Controller.Request;

import Entity.Order.CreditandDebit;
import Entity.Order.Payment;
import Entity.Order.Paynow;

public class PaymentController {
    public boolean processPayment(double amount, String paymentMethod) {
        Payment paymentProcessor;
        switch (paymentMethod.toLowerCase()) {
            case "credit/debit":
                paymentProcessor = new CreditandDebit();
                break;
            case "paynow":
                paymentProcessor = new Paynow();
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
        return paymentProcessor.processPayment(amount); //recursive function?
    }
}