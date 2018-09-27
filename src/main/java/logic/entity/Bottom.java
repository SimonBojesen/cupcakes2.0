package logic.entity;

public class Bottom {

    String bname;
    double bprice;

    public String getBname() {
        return bname;
    }

    public double getBprice() {
        return bprice;
    }

    public Bottom(String bname, double bprice) {
        this.bname = bname;
        this.bprice = bprice;
    }

    @Override
    public String toString() {
        return "Bottom{" + "bname=" + bname + ", bprice=" + bprice + '}';
    }
}
