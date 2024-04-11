public class PaymentController {
    public boolean processPayment(double amount, String paymentMethod) {
        Payment paymentProcessor;
        switch (paymentMethod.toLowerCase()) {
            case "credit/debit":
                paymentProcessor = new CreditAndDebit();
                break;
            case "paynow":
                paymentProcessor = new PayNow();
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
        return paymentProcessor.processPayment(amount);
    }
}