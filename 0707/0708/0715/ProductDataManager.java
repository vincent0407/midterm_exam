import java.util.Scanner;

public class ProductDataManager {

    private static final int MAX_SIZE = 100;
    
    // Separated parallel arrays to store parsed data
    private static String[] names = new String[MAX_SIZE];
    private static double[] prices = new double[MAX_SIZE];
    private static int[] stocks = new int[MAX_SIZE];
    private static int productCount = 0;

    public static void main(String[] args) {
        String[] initialRecords = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        for (String record : initialRecords) {
            parseAndAddRecord(record, false); 
        }

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 7) {
            System.out.println("\n=== Product Text Data Manager ===");
            System.out.println("1. Display Parsed Product Table");
            System.out.println("2. Exact Product Name Search");
            System.out.println("3. Partial Product Name Search");
            System.out.println("4. Display Low Stock Products");
            System.out.println("5. Display Total Inventory Value");
            System.out.println("6. Add New Product Record (CSV Format)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Invalid option! Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    displayProductTable();
                    break;
                case 2:
                    System.out.print("Enter exact product name: ");
                    String exactName = scanner.nextLine();
                    searchExact(exactName);
                    break;
                case 3:
                    System.out.print("Enter keyword for partial search: ");
                    String partialKey = scanner.nextLine();
                    searchPartial(partialKey);
                    break;
                case 4:
                    System.out.print("Enter low-stock threshold limit: ");
                    if (scanner.hasNextInt()) {
                        int threshold = scanner.nextInt();
                        scanner.nextLine();
                        displayLowStock(threshold);
                    } else {
                        System.out.println("Error: Threshold must be a valid integer.");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    displayTotalValue();
                    break;
                case 6:
                    System.out.print("Enter CSV string (Format: Name,Price,Stock): ");
                    String csvInput = scanner.nextLine();
                    parseAndAddRecord(csvInput, true); // true enables full console error reports
                    break;
                case 7:
                    System.out.println("System exited. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection. Please choose options 1 to 7.");
            }
        }
        scanner.close();
    }

    public static void parseAndAddRecord(String record, boolean printSuccess) {
        if (productCount >= MAX_SIZE) {
            System.out.println("Error: Database storage limit reached. Cannot add more items.");
            return;
        }

        if (record == null || record.trim().isEmpty()) {
            System.out.println("Error: Input string cannot be empty.");
            return;
        }

        String[] tokens = record.split(",");

        if (tokens.length != 3) {
            System.out.println("Error: Invalid format structure inside \"" + record + "\". Expected 3 fields separated by commas (Name,Price,Stock).");
            return;
        }

        try {
            String name = tokens[0].trim();
            if (name.isEmpty()) {
                System.out.println("Error: Product name field cannot be empty blank text.");
                return;
            }

            double price = Double.parseDouble(tokens[1].trim());
            int stock = Integer.parseInt(tokens[2].trim());

            if (price < 0 || stock < 0) {
                System.out.println("Error: Price or stock values cannot be negative numbers.");
                return;
            }

            names[productCount] = name;
            prices[productCount] = price;
            stocks[productCount] = stock;
            productCount++;

            if (printSuccess) {
                System.out.println("Success: Product record added successfully!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Number conversion failed in \"" + record + "\". Price must be a decimal value and Stock must be an integer.");
        }
    }

    public static void displayProductTable() {
        System.out.println("\n-----------------------------------------------------");
        System.out.printf("%-5s | %-20s | %-10s | %-8s\n", "ID", "Product Name", "Price", "Stock");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("#%-4d | %-20s | %-10.2f | %-8d\n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println("-----------------------------------------------------");
    }

    public static void searchExact(String target) {
        System.out.println("\n--- Exact Match Search Matches ---");
        boolean matched = false;
        String cleanTarget = target.trim();

        for (int i = 0; i < productCount; i++) {
            if (names[i].equalsIgnoreCase(cleanTarget)) {
                System.out.printf("Found! ID: #%d | Name: %s | Price: %.2f | Stock: %d\n", (i + 1), names[i], prices[i], stocks[i]);
                matched = true;
            }
        }
        if (!matched) {
            System.out.println("No matching product found with that exact name.");
        }
    }

    public static void searchPartial(String keyword) {
        System.out.println("\n--- Partial Search Matches ---");
        boolean matched = false;
        String cleanKeyword = keyword.trim().toLowerCase();

        for (int i = 0; i < productCount; i++) {
            if (names[i].toLowerCase().contains(cleanKeyword)) {
                System.out.printf("Match: %-20s | Price: %-10.2f | Stock: %-5d\n", names[i], prices[i], stocks[i]);
                matched = true;
            }
        }
        if (!matched) {
            System.out.println("No products contain the specified search term.");
        }
    }

    public static void displayLowStock(int threshold) {
        System.out.println("\n--- Low Stock Alerts (Stock <= " + threshold + ") ---");
        boolean alertRaised = false;

        for (int i = 0; i < productCount; i++) {
            if (stocks[i] <= threshold) {
                System.out.printf("Warning: %-20s | Stock Level remaining: %d\n", names[i], stocks[i]);
                alertRaised = true;
            }
        }
        if (!alertRaised) {
            System.out.println("All system products have safe capacity margins.");
        }
    }

    public static void displayTotalValue() {
        System.out.println("\n--- Financial Valuation Reports ---");
        double cumulativeSum = 0.0;

        for (int i = 0; i < productCount; i++) {
            double valuation = prices[i] * stocks[i];
            cumulativeSum += valuation;
        }
        System.out.printf("Total Inventory Asset Value: $%.2f\n", cumulativeSum);
    }
}

