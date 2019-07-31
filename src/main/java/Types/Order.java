package Types;

import java.util.ArrayList;

public class Order {
    private long dataTime;
    private long orderNumber;
    private String customerName;
    private ArrayList<OrderItem> item = new ArrayList<OrderItem>();

    private int itemSize = -1;

    public long getDataTime() {
        return dataTime;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void addItem() {
        this.itemSize++;
        this.item.add(new OrderItem());
    }

    public void setItemName(String name){
        this.item.get(this.itemSize).setName(name);
    }

    public void setItemPrice(long price){
        this.item.get(this.itemSize).setPrice(price);
    }

    public void setItemQuantity( double quantity){
        this.item.get(this.itemSize).setQuantity(quantity);
    }

    public void setItemSum(long sum){
        this.item.get(this.itemSize).setSum(sum);
    }

    public ArrayList<OrderItem>  getItems(){
        return this.item;
    }
}
