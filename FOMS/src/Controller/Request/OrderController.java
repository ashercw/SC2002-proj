package Controller.Request;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import Entity.Order.Order;
import Entity.Order.OrderLine;
import Entity.Order.OrderStatus;
import Entity.Order.OrderType;
import Entity.Order.Customisation;

public class OrderController {
    Scanner scanner = new Scanner(System.in);
    private Map<Integer, Order> Orders = new HashMap<>();
    String moreItems = "1";
    
    public void addOrder(OrderLine orderLine){
        OrderStatus orderStatus = OrderStatus.valueOf(scanner.nextLine().toUpperCase());
        OrderType orderType = OrderType.valueOf(scanner.nextLine().toUpperCase());
        String branchName = scanner.nextLine();

        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);

        double totalPrice = calculateTotalPrice(orderLines);

        Order newOrder = new Order(orderStatus, orderType, totalPrice, orderLines, orderLines.size(), branchName);
        Orders.put(newOrder.getOrderID(), newOrder);
    }

    public void updateOrder(OrderLine updatedOrderLine){
        int orderID = updatedOrderLine.getOrderId();
        Order orderUpdate = Orders.get(orderID);
        if(orderUpdate != null){
            // Get the first OrderLine item
            List<OrderLine> orderLines = orderUpdate.getOrderLine();
            if (orderID >= 0 && orderID < orderLines.size()) {
                OrderLine orderLineToBeUpdated = orderLines.get(orderID);
                orderLineToBeUpdated.setItem(updatedOrderLine.getItem());
                orderLineToBeUpdated.setItemQuantity(updatedOrderLine.getitemQuanity());
                double newTotalPrice = calculateTotalPrice(orderLines);
                orderUpdate.setTotalPrice(newTotalPrice);
            }else{
                System.out.println("No OrderLine items found to update.");
            }
        }else{
            System.out.println("Order ID not found.");
        }
}

    private double calculateTotalPrice(List<OrderLine> orderLines) {
        double total = 0.0;
        for (OrderLine line : orderLines) {
            total += line.getItem().getFoodItemPrice() * line.getitemQuanity();
        }
        return total;
    }

    public void cancelOrder(OrderLine cancel){
        int orderID = cancel.getOrderId();
        Order OrderCancel = Orders.get(orderID);
        if(OrderCancel != null){
            Orders.remove(orderID);
        }else{
            System.out.println("Order not found!");
        }
    }
    public void displayOrder(int orderID){
        Order displayOrderID = Orders.get(orderID);
        if(displayOrderID != null){
            displayOrderID.getOrderID();
            displayOrderID.getBranchName();
            displayOrderID.getOrderStatus();
            displayOrderID.getOrderType();
            displayOrderID.getTotalPrice();
            displayOrderID.getOrderQuantity();

            List<OrderLine> orderLines = displayOrderID.getOrderLine();
            for (OrderLine line : orderLines) {
                line.getItem().getName();
                line.getItem().getFoodItemPrice();
                line.getitemQuanity();
            }
        }else{
            System.out.println("Order not found!");
        }
    }

    public void customizeOrder(int orderID, Customisation customisation) {
        Order order = Orders.get(orderID);  // Retrieve the order using the order ID
        if (order != null) {
            List<OrderLine> orderLines = order.getOrderLine();
            if (orderID >= 0 && orderID < orderLines.size()) {
                OrderLine orderLine = orderLines.get(orderID);  // Get the specific order line
                orderLine.setCustomisation(customisation);  // Set the new customization for the order line
            }else{
                System.out.println("Order not in queue!");
            }
        }else{
            System.out.println("Order not found!");
        }
    }
}
