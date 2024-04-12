package Entity.Order;

import java.util.HashMap;
import java.util.Map;

import Entity.BranchType;
import Entity.Food.FoodItem;

public class OrderNew {
    private OrderStatus orderStatus;
 private OrderType orderType;
 private double totalPrice;
 private int orderID;
 private static int totalOrder = 0;
    private Map<FoodItem,Integer> items;
    private BranchType branchName;
 
 
 //constructor
 public OrderNew(OrderStatus _os, OrderType _ot, double _tp, BranchType _bt)
 {
  this.orderStatus = _os;
  this.orderType = _ot;
  this.totalPrice = _tp;
  this.orderID = ++totalOrder;
        items=new HashMap<>();
        this.branchName = _bt;
 }

    public void addItem(FoodItem menuItem, int quantity) {
        items.put(menuItem, items.getOrDefault(menuItem, 0) + quantity);
    }
    
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
 
 //Accessors and mutators
 public OrderStatus getOrderStatus()
 {
  return this.orderStatus;
 }
 
 public OrderType getOrderType()
 {
  return this.orderType;
 }
 
 public double getTotalPrice()
 {
  return this.totalPrice;
 }
 
 public int getOrderID()
 {
  return this.orderID;
 }

    public Map<FoodItem, Integer> getItems(){
        return items;
    }
}