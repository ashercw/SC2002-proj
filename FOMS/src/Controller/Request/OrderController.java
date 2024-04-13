package Controller.Request;
import java.util.ArrayList;
import java.util.List;

import Entity.Order.OrderLine;
import Entity.Order.OrderType;

//Why is there an OrderNew Class? Just have a funciton in OrderController that places the order 
// and adds to a repo or smth

public class OrderController {
    private List<OrderNew> orders = new ArrayList<>();

    public OrderNew placeOrder(List<OrderLine> orderLines, OrderType orderType) {
        OrderNew newOrder = new OrderNew(orderType);
        for (OrderLine line : orderLines) {
            newOrder.addOrderLine(line.getItem(), line.getQuantity(), line.getCustomisation());
        }
        orders.add(newOrder); // Simulate persisting the order
        return newOrder;
    }
}
