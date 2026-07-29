public class ProductSortPractice {

    public static void insertionSortProducts(Product[] products) {
        for (int i = 1; i < products.length; i++) {
            Product key = products[i];
            int j = i - 1;

            while (j >= 0 && products[j].getPrice() > key.getPrice()) {
                products[j + 1] = products[j];
                j--;
            }
            products[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "滑鼠", 500, 20),
            new Product("P002", "鍵盤", 1200, 15),
            new Product("P003", "耳機", 500, 10),
            new Product("P004", "螢幕", 4500, 5),
            new Product("P005", "喇叭", 1200, 8),
            new Product("P006", "滑鼠墊", 300, 50),
            new Product("P007", "麥克風", 1200, 12),
            new Product("P008", "網路攝影機", 800, 18)
        };

        System.out.println("=== 排序前商品清單 ===");
        for (Product p : products) {
            System.out.println(p);
        }

        insertionSortProducts(products);

        System.out.println("\n=== 依價格升冪排序後商品清單 ===");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}

