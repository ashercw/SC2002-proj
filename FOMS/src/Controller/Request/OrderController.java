package Controller.Request;

import Controller.Account.Password.PasswordMasker;
import java.lang.Math;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Food.FoodItem;
import Entity.Order.Order;
import Entity.Order.OrderLine;
import Entity.Order.OrderStatus;
import Entity.Order.OrderType;
import Others.IO;
import Others.TextDBOrder;
import Others.TextDBPayment;

/***
 * @author Elbert Gunawan, Saffron Lim and Christian Asher Widjaja
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class OrderController {

    /**
     * Allows the customer to place an order, which is then saved into OrderRepo.txt.
     * @param branch String representing the customer's branch
     * @param menuList ArrayList of FoodItems
     * 
     */
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

        System.out.print("Choose: (1) Takeaway, (2) Dine In: ");
        userInputOrderType = IO.userInputInt();
        if (userInputOrderType == 1)
            orderType = OrderType.TAKEAWAY;
        else if (userInputOrderType == 2)
            orderType = OrderType.DINEIN;

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
            if (processPayment()) {

                TextDBOrder.writeSerializedObject("OrderRepo.txt", orderObj);
                System.out.println("Order Created!");
            } else {
                return;
            }
        } else if (isConfirm == 2) {
            // update order
            TextDBOrder.writeSerializedObject("OrderRepo.txt", updateOrder(orderObj));
            if (processPayment()) {

                TextDBOrder.writeSerializedObject("OrderRepo.txt", orderObj);
                System.out.println("Order Created!");
            } else
                return;

        } else if (isConfirm == 3) {
            // delete order
            System.out.println("Deleting order... proceeding back to customer page");
            return;
        }

    }

    /**
     * Allows the customer to choose the next option: (1) Confirm Order (2) Update Order (3) Delete Order
     * @return an integer value of the customer's response.
     * 
     */
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

    /**
     * Update an order's quantity, customisation instructions, or delete the order
     * @param orderObj the order to be modified
     * @return an Order object
     * 
     */
    public static Order updateOrder(Order orderObj) {
        // update order
        ArrayList orderLineList = orderObj.getOrderLine();
        int userInput = 0;
        int updateInput = 1;
        displayOrderLineList(orderLineList);
        while (userInput != -1) {
            // choose item to update
            System.out.println("Which item would you like to update? Quit: (-1)");
            userInput = IO.userInputInt();
            if (userInput == -1)
                break;
            updateInput = 1;
            if (userInput > orderLineList.size() || userInput < 1) // check if item exists
                System.out.println("Item does not exist! Please try again.");

            else // carry on
            {
                OrderLine orderL = (OrderLine) orderLineList.get(userInput - 1);
                while (updateInput >= 1 && updateInput <= 3) {
                    // update menu
                    System.out.println("Update " + orderL.getItem().getFoodItemName());
                    System.out.println("1) Delete item");
                    System.out.println("2) Update Quantity");
                    System.out.println("3) Update Customisation");
                    System.out.println("[-1] Quit");
                    updateInput = IO.userInputInt();
                    if (updateInput == 1) {
                        orderLineList.remove(userInput - 1);
                    } else if (updateInput == 2) {
                        // update quantity
                        System.out.println("Enter new quantity: ");
                        int oldOLQuant = orderL.getItemQuantity();
                        int newQuant = IO.userInputInt();
                        orderL.setItemQuantity(newQuant);
                        int totalQuantOld = orderObj.getOrderQuantity();
                        orderObj.setTotalPrice(calcNewTotal(orderLineList));
                        orderObj.setOrderQuant(totalQuantOld + (Math.abs(newQuant - oldOLQuant)));
                    } else if (updateInput == 3) {
                        // update customisation
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

    /**
     * Calculates the total price of an order
     * @param orderLineList ArrayList of orderLineList
     * @return double representing the total price of an order
     */
    public static double calcNewTotal(ArrayList orderLineList) {
        double sum = 0;
        for (int i = 0; i < orderLineList.size(); i++) {
            OrderLine orderL = (OrderLine) orderLineList.get(i);
            sum += orderL.getItemTotPrice();
        }
        return sum;
    }

    /**
     * Displays an ArrayList of orderLine objects
     * @param orderLineList ArrayList of orderLine objects
     * 
     */
    public static void displayOrderLineList(ArrayList orderLineList) {
        for (int i = 0; i < orderLineList.size(); i++) {
            OrderLine orderL = (OrderLine) orderLineList.get(i);
            System.out.print("\nNo.: " + (i + 1) + " | Item: " + orderL.getItem().getFoodItemName());
            System.out.print(" | Quantity" + orderL.getItemQuantity() + " | Price: ");
            System.out.format("$%.2f", orderL.getItemQuantity() * orderL.getItem().getFoodItemPrice());
            System.out.println("\n" + orderL.getCustomisation());
        }
    }

    /**
     * Displays a specific order.
     * @param orderObj the order to be displayed.
     * 
     */
    public static void displayOrder(Order orderObj) {
        IO.printNewLine(2);
        IO.displayDivider();
        System.out.format("%30s", "YOUR ORDER");
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

    /**
     * Prints all the orders in the OrderRepo.txt file
     */
    public static void printAllOrders() {
        List fullOrderL = TextDBOrder.readSerializedObject("OrderRepo.txt");

        for (int i = 0; i < fullOrderL.size(); i++) {
            // List currOrder = (List)fullOrderL.get(i);
            Order currOrder2 = (Order) fullOrderL.get(i);
            // Order orderObj = (Order) fullOrderL.get(i);
            displayOrder(currOrder2);
        }
    }

    /**
     * Check if an order has been discarded.
     * @param obj the order that is being checked.
     */
    public static void checkifDiscarded(Order obj) {
        boolean isExpired = obj.hasExpired();
        if (isExpired) {
            TextDBOrder.writeSerializedObject("OrderRepo.txt", obj);
        }
    }

    /**
     * Allows the customer to collect their order.
     */
    public static void collectOrder() {
        printAllOrders();
        int orderConfirm = 0;
        System.out.println("Please enter your Order ID: ");
        int userOrderID = IO.userInputInt();
        Order custOrder = null;

        List orders = TextDBOrder.readSerializedObject("OrderRepo.txt");
        for (int i = 0; i < orders.size(); i++) {
            custOrder = (Order) orders.get(i);
            if (custOrder.getOrderID() == userOrderID) {
                System.out.println("Order " + userOrderID + " found!");
                break;
            }
        }

        // custOrder = getOrderById(userOrderID);
        if (custOrder == null)
            return; // search failed

        displayOrder(custOrder);

        // user confirmation
        System.out.println("Is this your order? (1) Yes (2) No");
        orderConfirm = IO.userInputInt();
        if (orderConfirm == 2)
            return; // not the correct order

        if (custOrder.getOrderStatus() == OrderStatus.READY) {
            System.out.println("Your order is ready for collection! Collect order? (1) Yes (2) No");
            int collectOrder = IO.userInputInt();
            if (collectOrder < 1 && collectOrder > 2)
                System.out.println("Wrong input!");

            else if (collectOrder == 1) {
                for (int i = 0; i < orders.size(); i++) {
                    custOrder = (Order) orders.get(i);
                    if (custOrder.getOrderID() == userOrderID) {
                        System.out.println("Order " + userOrderID + " found!");
                        custOrder.setOrderStatus(OrderStatus.COLLECTED);
                        TextDBOrder.writeSerializedObject("OrderRepo.txt", custOrder);
                        System.out.println("Thank you for collecting your order! Enjoy your meal!");
                        return;
                    }
                }
                System.out.println("Order cannot be collected as it does not exist!");

                // updateOrderStatus(userOrderID, OrderStatus.COLLECTED);

            }
        } else if (custOrder.getOrderStatus() == OrderStatus.DISCARDED) {
            System.out.println(
                    "Apologies. You have exceeded the waiting time for collection.\nYour order has been discarded. Please come again soon!");
            return;
        } else {
            System.out.println("Enter -1 to go back: ");
            if (IO.userInputInt() == -1)
                return;
        }
    }

    /**
     * Update the status of an order.
     * @param orderId the orderId of the order to be updated. 
     * @param newStatus the new order status of the order.
     * 
     */
    public static void updateOrderStatus(int orderId, OrderStatus newStatus) {
        // Get the order by its ID
        Order order = getOrderById(orderId);

        if (order != null) {
            // Update the order status
            order.setOrderStatus(newStatus);

            // Save the updated order to the database
            TextDBOrder.writeSerializedObject("OrderRepo.txt", order);

            System.out.println("Order status updated successfully.");
        } else {
            System.out.println("Order not found with ID: " + orderId);
        }
    }

    /**
     * Find an order in the OrderRepo according to its orderID.
     * @param orderId the orderId of the order to be found.
     * @return an Order object.
     * 
     */
    public static Order getOrderById(int orderId) {

        List orders = TextDBOrder.readSerializedObject("OrderRepo.txt");

        if (orderId < 1 || orderId > orders.size()) {
            System.out.println("Order does not exist!");
            return null; // Return null if no order matches the given ID
        }
        if (orders != null) {
            for (int i = 0; i < orders.size(); i++) {
                Order currOrder2 = (Order) orders.get(0);
                if (currOrder2.getOrderID() == orderId) {
                    return currOrder2;
                }
            }
        }
        return null; // Return null if no order matches the given ID
    }

    /**
     * Allows the customer to make payment. Uses PasswordMasker.hideString() to hide the
     * payment details.
     * @return boolean, true if payment is successful and false otherwise.
     * 
     */
    public static boolean processPayment() {
        int userChoice = 0;
        IO.printNewLine(5);
        IO.displayDivider();
        System.out.println("\t\tPROCESS PAYMENT");
        IO.displayDivider();
        IO.printNewLine(1);
        try {
            List<String> payMethodL = TextDBPayment.readPaymentMethods("PaymentRepo.txt");
            while (userChoice != -1) {
                System.out.println("Payment Methods:");
                for (int i = 0; i < payMethodL.size(); i++) {
                    System.out.println((i + 1) + ". " + payMethodL.get(i));
                }
                System.out.println("Please choose a payment method: ");
                userChoice = IO.userInputInt();
                if (userChoice < 1 || userChoice > payMethodL.size()) {
                    System.out.println("Payment method does not exist!");
                } else {
                    System.out.println("Please enter payment details for " + payMethodL.get(userChoice - 1) + ":");
                    // String userString = IO.userInpuString();
                    String userString = PasswordMasker.hideString();
                    if (userString != null) {
                        System.out.println("Payment success!");
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Payment failed!");
        return false;
    }

    

}
