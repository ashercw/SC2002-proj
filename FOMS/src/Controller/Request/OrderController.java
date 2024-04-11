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
