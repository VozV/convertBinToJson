package Types;

public class OrderItem {
    private String name;
    private long price;
    private double quantity;
    private long sum;

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public long getSum() {
        return sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

}
