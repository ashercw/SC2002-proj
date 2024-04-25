package Controller.Request;

import Entity.Order.CreditandDebit;
import Entity.Order.Payment;
import Entity.Order.Paynow;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Reuben Farrel, Christian
 */

public class PaymentController {
    private static final Map<String, Class<? extends Payment>> paymentMethodMap = new HashMap<>();

    static {
        paymentMethodMap.put("credit/debit", CreditandDebit.class);
        paymentMethodMap.put("paynow", Paynow.class);
    }

    public boolean processPayment(double amount, String paymentMethod) {
        Class<? extends Payment> paymentClass = paymentMethodMap.get(paymentMethod.toLowerCase());
        if (paymentClass == null) {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }

        try {
            Constructor<? extends Payment> constructor = paymentClass.getDeclaredConstructor();
            Payment paymentProcessor = constructor.newInstance();
            return paymentProcessor.processPayment(amount);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new RuntimeException("Failed to create payment processor instance", e);
        }
    }

    public static void registerPaymentMethod(String paymentMethod, Class<? extends Payment> paymentClass) {
        paymentMethodMap.put(paymentMethod.toLowerCase(), paymentClass);
    }
}