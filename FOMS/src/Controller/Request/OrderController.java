package Controller.Request;

import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import Entity.Food.FoodItem;
import Entity.Order.Order;
import Entity.Order.OrderLine;
import Entity.Order.OrderStatus;
import Entity.Order.OrderType;
import Others.IO;
import Others.TextDBOrder;

/***
 * @author Elbert Gunawan, Saffron Lim and Christian Asher Widjaja
 */

public class OrderController {
    Scanner scanner = new Scanner(System.in);
    private Map<Integer, Order> Orders = new HashMap<>();
    String moreItems = "1";

    /*
     * public void addOrder(OrderLine orderLine){
     * OrderStatus orderStatus =
     * OrderStatus.valueOf(scanner.nextLine().toUpperCase());
     * OrderType orderType = OrderType.valueOf(scanner.nextLine().toUpperCase());
     * String branchName = scanner.nextLine();
     * 
     * List<OrderLine> orderLines = new ArrayList<>();
     * orderLines.add(orderLine);
     * 
     * double totalPrice = calculateTotalPrice(orderLines);
     * 
     * Order newOrder = new Order(orderStatus, orderType, totalPrice, orderLines,
     * orderLines.size(), branchName);
     * Orders.put(newOrder.getOrderID(), newOrder);
     * }
     */

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void createOrder(String branch, ArrayList menuList) {
        int userInputItem = 0;
        int userInputOrderType = 0;
        int itemQuant = 0;
        int orderQuant = 0;
        int userInputCustom = 0;
        String customInstruct = "";
        Double totalPrice = 0.0;
        OrderType orderType = OrderType.DINEIN; // default

        ArrayList orderLinesList = new ArrayList<>();
        ArrayList ordersList = new ArrayList<>();

        System.out.print("Choose: (1) Takeaway, (2) Dine In: ");
        userInputOrderType = IO.userInputInt();
        if (userInputOrderType == 1)
            orderType = OrderType.DINEIN;
        else if (userInputOrderType == 2)
            orderType = OrderType.TAKEAWAY;

        while (userInputItem != -1) {
            System.out.print("Enter the item number (-1 to end ordering): ");
            userInputItem = IO.userInputInt();
            if (userInputItem == -1)
                break;
            FoodItem food = new FoodItem();
            food = (FoodItem) menuList.get(userInputItem - 1);

            System.out.print("Enter the item quanity: ");

            itemQuant = IO.userInputInt();
            totalPrice += food.getFoodItemPrice() * itemQuant;
            orderQuant += itemQuant;

            System.out.print("Enter the item customisation:  (1) Yes (2) No: ");
            userInputCustom = IO.userInputInt();

            // get custom
            if (userInputCustom == 1) {
                System.out.println("Please enter your instructions");
                customInstruct = IO.userInpuString();
            } else {
                customInstruct = "No Customisation.";
            }

            // OrderLine orderLineObj = new OrderLine(food.getFoodItemName(), itemQuant,
            // food.getFoodItemType(), customInstruct);
            OrderLine orderLineObj = new OrderLine(food, itemQuant, customInstruct);

            System.out.println("\nItem added to cart!");
            orderLineObj.printOrderLine(orderLineObj);
            System.out.println("Number of items in cart: " + orderQuant);
            System.out.printf("Total price: %.2f", totalPrice);
            IO.printNewLine(2);

            orderLinesList.add(orderLineObj);
        }

        Order orderObj = new Order(OrderStatus.ORDERPLACED, orderType, totalPrice, orderLinesList, orderQuant, branch);
        displayOrder(orderObj);
        
        int isConfirm = confirmOrder();
        if (isConfirm == 1) {
            // proceed to payment
            ordersList.add(orderObj);
        } else if (isConfirm == 2) {
            // update order
            ordersList.add(updateOrder(orderObj));

        } else if (isConfirm == 3) {
            // delete order
            System.out.println("Deleting order... proceeding back to customer page");
            return;
        }
        

        TextDBOrder.writeSerializedObject("OrderRepo.txt", ordersList);
        /*List orderObjL = TextDBOrder.readSerializedObject("OrderRepo.txt");
        Order obj = (Order) orderObjL.get(0);
        System.out.println("NEW PRICE "+ obj.getTotalPrice());*/

    }

    public static int confirmOrder() {
        int userInput = 1;
        while (userInput >= 1 && userInput <= 3) {
            System.out.println("Please confirm the above order: (1) Confirm Order (2) Update Order (3) Delete Order");
            userInput = IO.userInputInt();
            if (userInput >= 1 && userInput <= 3)
                break;
            System.out.println("Wrong input, please enter (1-3).");
        }
        return userInput;
    }

    public static Order updateOrder(Order orderObj) {
        // update order
        ArrayList orderLineList = orderObj.getOrderLine();
        int userInput = 0;
        int updateInput = 1;
        displayOrderLineList(orderLineList);
        while (userInput != -1) 
        {
            //choose item to update 
            System.out.println("Which item would you like to update? Quit: (-1)");
            userInput = IO.userInputInt();
            if(userInput == -1) break;
            updateInput = 1;
            if (userInput > orderLineList.size() || userInput < 1) //check if item exists 
                System.out.println("Item does not exist! Please try again.");

            else //carry on
            {
                OrderLine orderL = (OrderLine) orderLineList.get(userInput - 1);
                while (updateInput >= 1 && updateInput <= 3) 
                {
                    //update menu
                    System.out.println("Update " + orderL.getItem().getFoodItemName());
                    System.out.println("1) Delete item");
                    System.out.println("2) Update Quantity");
                    System.out.println("3) Update Customisation");
                    System.out.println("[-1] Quit");
                    updateInput = IO.userInputInt();
                    if(updateInput == 1)
                    {
                        orderLineList.remove(userInput-1);
                    }
                    else if(updateInput == 2)
                    {
                        //update quantity
                        System.out.println("Enter new quantity: ");
                        int newQuant = IO.userInputInt();
                        orderL.setItemQuantity(newQuant);
                        orderObj.setTotalPrice(calcNewTotal(orderLineList));
                    }
                    else if(updateInput == 3)
                    {
                        //update customisation
                        System.out.println("Enter new customisation: ");
                        String newCust = IO.userInpuString();
                        orderL.setCustomisation(newCust);
                    }
                }
            }
        }
        displayOrder(orderObj);
        return orderObj;
    }

    public static double calcNewTotal(ArrayList orderLineList)
    {
        double sum = 0;
        for(int i = 0; i < orderLineList.size(); i++)
        {
            OrderLine orderL = (OrderLine) orderLineList.get(i);
            sum += orderL.getItemTotPrice();
        }
        return sum;
    }


    public static void displayOrderLineList(ArrayList orderLineList) {
        for (int i = 0; i < orderLineList.size(); i++) {
            OrderLine orderL = (OrderLine) orderLineList.get(i);
            System.out.print("\nNo.: " + (i + 1) + " | Item: " + orderL.getItem().getFoodItemName());
            System.out.print(" | Quantity" + orderL.getItemQuantity() + " | Price: ");
            System.out.format("$%.2f", orderL.getItemQuantity() * orderL.getItem().getFoodItemPrice());
            System.out.println("\n" + orderL.getCustomisation());
        }
    }

    public static void displayOrder(Order orderObj) {
        IO.printNewLine(2);
        IO.displayDivider();
        System.out.format("%15s", "YOUR ORDER");
        System.out.println("");
        IO.displayDivider();

        System.out.format("%-15s", "Order ID:");
        System.out.println("Branch:");

        System.out.format("%-15s", orderObj.getOrderID());
        System.out.println(orderObj.getBranchName());

        System.out.format("%-15s", "Total Price:");
        System.out.println("No. of Items:");

        System.out.format("$%-14.2f", orderObj.getTotalPrice());
        System.out.println(orderObj.getOrderQuantity());

        System.out.format("%-15s", "Order Type:");
        System.out.println("Order Status");

        System.out.format("%-15s", orderObj.getOrderType());
        System.out.println(orderObj.getOrderStatus());
        System.out.println("----------------------------------------");
        ArrayList orderLineList = orderObj.getOrderLine();
        for (int i = 0; i < orderLineList.size(); i++) {
            OrderLine orderL = (OrderLine) orderLineList.get(i);
            System.out.format("%-15s", "Item:");
            System.out.println("No.:");

            System.out.format("%-15s", orderL.getItem().getFoodItemName());
            System.out.println(orderL.getItemQuantity());

            System.out.format("%-10s", "Price:");
            System.out.format("$%.2f", orderL.getItemQuantity() * orderL.getItem().getFoodItemPrice());
            System.out.println("\n" + orderL.getCustomisation());

        }
        IO.displayDivider();
        IO.printNewLine(1);

    }

    /*
     * public void updateOrder(OrderLine updatedOrderLine){
     * //int orderID = myOrder.getOrderID();
     * int orderID = updatedOrderLine.getOrderId();
     * Order orderUpdate = Orders.get(orderID);
     * if (orderUpdate != null) {
     * // Get the first OrderLine item
     * List<OrderLine> orderLines = orderUpdate.getOrderLine();
     * if (orderID >= 0 && orderID < orderLines.size()) {
     * OrderLine orderLineToBeUpdated = orderLines.get(orderID);
     * orderLineToBeUpdated.setItem(updatedOrderLine.getItem());
     * orderLineToBeUpdated.setItemQuantity(updatedOrderLine.getItemQuantity());
     * double newTotalPrice = calculateTotalPrice(orderLines);
     * orderUpdate.setTotalPrice(newTotalPrice);
     * } else {
     * return;
     * }
     * } else {
     * return;
     * }
     * }
     * 
     * private double calculateTotalPrice(List<OrderLine> orderLines) {
     * double total = 0.0;
     * for (OrderLine line : orderLines) {
     * total += line.getItem().getFoodItemPrice() * line.getItemQuantity();
     * }
     * return total;
     * }
     * 
     * public void cancelOrder(OrderLine cancel) {
     * int orderID = cancel.getOrderId();
     * Order OrderCancel = Orders.get(orderID);
     * if (OrderCancel != null) {
     * Orders.remove(orderID);
     * } else {
     * return;
     * }
     * }
     * 
     * public void displayOrder(int orderID) {
     * Order displayOrderID = Orders.get(orderID);
     * if (displayOrderID != null) {
     * displayOrderID.getOrderID();
     * displayOrderID.getBranchName();
     * displayOrderID.getOrderStatus();
     * displayOrderID.getOrderType();
     * displayOrderID.getTotalPrice();
     * displayOrderID.getOrderQuantity();
     * 
     * List<OrderLine> orderLines = displayOrderID.getOrderLine();
     * for (OrderLine line : orderLines) {
     * line.getItem().getName();
     * line.getItem().getFoodItemPrice();
     * line.getItemQuantity();
     * }
     * } else {
     * return;
     * }
     * }
     * 
     * public void customizeOrder(int orderID, String customisation) {
     * Order order = Orders.get(orderID); // Retrieve the order using the order ID
     * if (order != null) {
     * List<OrderLine> orderLines = order.getOrderLine();
     * if (orderID >= 0 && orderID < orderLines.size()) {
     * OrderLine orderLine = orderLines.get(orderID); // Get the specific order line
     * orderLine.setCustomisation(customisation); // Set the new customization for
     * the order line
     * } else {
     * return;
     * }
     * } else {
     * return;
     * }
     * }
     * 
     * public Map<Integer, Order> getOrders() {
     * return this.Orders;
     * }
     * 
     * public Order getOrder(int orderID) {
     * return Orders.get(orderID);
     * }
     */

}
