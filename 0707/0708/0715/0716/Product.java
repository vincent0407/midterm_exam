public class Product {
    private String name;
    private int price;
    private int stock;

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public boolean setPrice(int price) {
        if (price >= 0) {
            this.price = price;
            return true; 
        }
        return false; 
    }

    public boolean restock(int amount) {
        if (amount > 0) {
            this.stock += amount;
            return true; 
        }
        return false;
    }

    public boolean sell(int amount) {
        if (amount > 0 && this.stock >= amount) {
            this.stock -= amount;
            return true; 
        }
        return false; 
    }

    public boolean isLowStock() {
        return this.stock <= 5;
    }

    public int getInventoryValue() {
        return this.price * this.stock;
    }

    @Override
    public String toString() {
        return "商品: " + name + ", 價格: " + price + " 元, 庫存: " + stock;
    }
}
