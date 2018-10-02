package logic.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    int orderId, userId;
    boolean isPaid;
    
    List<OrderLine> allOrders = new ArrayList();

    public Order(int userId, boolean isPaid) {
        this.userId = userId;
        this.isPaid = isPaid;
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

}
