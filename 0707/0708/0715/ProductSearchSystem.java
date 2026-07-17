import java.util.Scanner;

public class ProductSearchSystem {

    private static String[] products = {
        "Apple iPhone", 
        "Sony Headphones", 
        "Logitech Mechanical Keyboard", 
        "Asus Zenfone", 
        "Nintendo Switch", 
        "Dell Monitor"
    };
    private static double[] prices = {32000, 8500, 3500, 19900, 8980, 5500};
    private static int[] stocks = {15, 8, 25, 12, 40, 6};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println("\n=== Product Search System ===");
            System.out.println("1. Display all products");
            System.out.println("2. Exact match search (case-insensitive & trim spaces)");
            System.out.println("3. Partial match search (multiple results)");
            System.out.println("4. Display product with the longest name");
            System.out.println("5. Display first occurrence index of a keyword");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    showAllProducts();
                    break;
                case 2:
                    System.out.print("Enter exact product name to search: ");
                    String exactKey = scanner.nextLine();
                    searchExact(exactKey);
                    break;
                case 3:
                    System.out.print("Enter keyword for partial search: ");
                    String partialKey = scanner.nextLine();
                    searchPartial(partialKey);
                    break;
                case 4:
                    showLongestProduct();
                    break;
                case 5:
                    System.out.print("Enter character or keyword to locate: ");
                    String locateKey = scanner.nextLine();
                    showKeywordLocation(locateKey);
                    break;
                case 6:
                    System.out.println("System exited. Thank you!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void showAllProducts() {
        System.out.println("\n--- Product List ---");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("Name: %-30s | Price: %-7.0f | Stock: %d\n", products[i], prices[i], stocks[i]);
        }
    }

    public static void searchExact(String key) {
        System.out.println("\n--- Exact Search Result ---");
        String cleanKey = key.trim().toLowerCase(); 
        boolean found = false;

        for (int i = 0; i < products.length; i++) {
            if (products[i].toLowerCase().equals(cleanKey)) {
                System.out.printf("Product Found! Name: %s | Price: %.0f | Stock: %d\n", products[i], prices[i], stocks[i]);
                found = true;
                break; 
            }
        }
        if (!found) {
            System.out.println("Product not found. Please check the spelling.");
        }
    }

    public static void searchPartial(String key) {
        System.out.println("\n--- Partial Search Result ---");
        String cleanKey = key.toLowerCase();
        boolean found = false;

        for (int i = 0; i < products.length; i++) {
            if (products[i].toLowerCase().contains(cleanKey)) {
                System.out.printf("Name: %-30s | Price: %-7.0f | Stock: %d\n", products[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found containing this keyword.");
        }
    }

    public static void showLongestProduct() {
        System.out.println("\n--- Product with Longest Name ---");
        if (products.length == 0) return;

        int longestIndex = 0;
        for (int i = 1; i < products.length; i++) {
            if (products[i].length() > products[longestIndex].length()) {
                longestIndex = i;
            }
        }
        System.out.printf("Longest Name: %s (%d chars)\n", products[longestIndex], products[longestIndex].length());
        System.out.printf("Price: %.0f | Stock: %d\n", prices[longestIndex], stocks[longestIndex]);
    }

    public static void showKeywordLocation(String key) {
        System.out.println("\n--- First Occurrence Location (Case-Insensitive) ---");
        if (key.isEmpty()) {
            System.out.println("Keyword cannot be empty!");
            return;
        }
        
        String cleanKey = key.toLowerCase();
        for (int i = 0; i < products.length; i++) {
            int index = products[i].toLowerCase().indexOf(cleanKey);
            if (index != -1) {
                System.out.printf("Product: %-30s -> First found at index [%d]\n", products[i], index);
            } else {
                System.out.printf("Product: %-30s -> Keyword not found\n", products[i]);
            }
        }
    }
}
