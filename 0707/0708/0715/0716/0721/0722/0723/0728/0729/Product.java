public class Product {
    private String id;
    private String name;
    private int price;
    private int stock;

    public Product(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("編號: %-5s | 名稱: %-10s | 價格: %4d 元 | 庫存: %3d", id, name, price, stock);
    }
}
