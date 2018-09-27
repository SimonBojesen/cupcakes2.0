package logic.entity;

public class CupCake {
    Bottom bottom;
    Topping topping;
    
    public CupCake(Bottom bottom, Topping topping) {
        this.bottom = bottom;
        this.topping = topping;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public Topping getTopping() {
        return topping;
    }
    
    public double getPrice(){
       return this.bottom.getBprice() + this.topping.getTprice();   
    }
}
