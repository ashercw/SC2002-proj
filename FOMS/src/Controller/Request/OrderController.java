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
        System.out.println("Enter order status");
        OrderStatus orderStatus = OrderStatus.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter order type");
        OrderType orderType = OrderType.valueOf(scanner.nextLine().toUpperCase());

        System.out.println("Enter Branch (NTU, JP, JE)");
        String branchName = scanner.nextLine();

        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);

        double totalPrice = calculateTotalPrice(orderLines);

        Order newOrder = new Order(orderStatus, orderType, totalPrice, orderLines, orderLines.size(), branchName);
        Orders.put(newOrder.getOrderID(), newOrder);
        System.out.println("Order added successfully. Order ID: " + newOrder.getOrderID());
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
                System.out.println("OrderLine updated successfully.");
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
            System.out.println("Order ID: " + displayOrderID.getOrderID());
            System.out.println("Branch Name: " + displayOrderID.getBranchName());
            System.out.println("Order Status: " + displayOrderID.getOrderStatus());
            System.out.println("Order Type: " + displayOrderID.getOrderType());
            System.out.println("Total Price: $" + displayOrderID.getTotalPrice());
            System.out.println("Total Items: " + displayOrderID.getOrderQuantity());

            List<OrderLine> orderLines = displayOrderID.getOrderLine();
            System.out.println("Items in Order:");
            for (OrderLine line : orderLines) {
                System.out.println(" - Item Name: " + line.getItem().getName());
                System.out.println("   Price: $" + line.getItem().getFoodItemPrice());
                System.out.println("   Quantity: " + line.getitemQuanity());
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
                System.out.println("Order ID " + orderID + " has been customized to " + customisation + ".");
            }else{
                System.out.println("Order not in queue!");
            }
        }else{
            System.out.println("Order not found!");
        }
    }
}
