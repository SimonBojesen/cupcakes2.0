package logic.entity;

public class Topping {
    String tname;
    double tprice;

    public String getTname() {
        return tname;
    }

    public double getTprice() {
        return tprice;
    }

    public Topping(String tname, double tprice) {
        this.tname = tname;
        this.tprice = tprice;
    }
}
