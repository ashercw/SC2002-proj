package Controller.Request;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import Entity.Food.FoodItem;
import Entity.Food.ItemType; // If ItemType is used for setting the type of food items.
import Entity.Order.Order;
import Entity.Order.OrderLine;
import Entity.Order.OrderStatus; // If setting the order status upon creation.
import Entity.Order.OrderType; // For handling different types of orders (DINE-IN, TAKEAWAY).
import Controller.Request.OrderController; 



public class OrderController {
    private List<Order> orders = new ArrayList<>();

    public Order placeOrder(List<OrderLine> orderLines, OrderType orderType) {
        double totalPrice = 0;
        for (OrderLine line : orderLines) {
            totalPrice += line.getItem().getFoodItemPrice() * line.getitemQuanity();
        }
        //TODO:get branch name when placing order
        Order newOrder = new Order(OrderStatus.ORDERPLACED, orderType, totalPrice, orderLines, orderLines.size(),branchName);
        orders.add(newOrder); // Simulating adding to a repository
        return newOrder;
    }
}
