public class Order {
    private String orderId;
    private String customerName;
    private double amount;

    public Order(String orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("Order[ID=%s, Customer=%s, Amount=%.2f]", orderId, customerName, amount);
    }
}
