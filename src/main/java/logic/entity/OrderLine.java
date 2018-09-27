package logic.entity;

public class OrderLine {
CupCake cupcake;
double totalPrice;
int qty;

    public CupCake getCupcake() {
        return cupcake;
    }

    public double getTotalPrice() {
        return cupcake.getPrice() * qty;
    }

    public int getQty() {
        return qty;
    }

    public OrderLine(CupCake cupcake, double totalPrice, int qty) {
        this.cupcake = cupcake;
        this.totalPrice = totalPrice;
        this.qty = qty;
    }
    
}
