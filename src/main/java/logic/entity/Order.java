package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    int orderId, userId;
    boolean isPaid;
    String orderDate;
    List<OrderLine> allOrders = new ArrayList();

    public Order(int orderId, int userId, boolean isPaid, String orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.isPaid = isPaid;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<OrderLine> getAllOrders() {
        return allOrders;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
