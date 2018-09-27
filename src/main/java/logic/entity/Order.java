package logic.entity;

public class Order {
    int orderId, userId;
    boolean isPaid;
    String orderDate;

    public Order(int orderId, int userId, boolean isPaid, String orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.isPaid = isPaid;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
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
