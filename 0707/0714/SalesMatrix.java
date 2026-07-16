import java.util.Scanner;

public class SalesMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] sales = new int[3][4];

        inputSales(sc, sales);

        System.out.println("\n--- 銷售矩陣報表 ---");
        displaySalesTable(sales);

        int[] productTotals = calculateProductTotals(sales);
        System.out.println("\n各商品總銷售量：");
        for (int i = 0; i < productTotals.length; i++) {
            System.out.println("商品 " + (i + 1) + ": " + productTotals[i]);
        }

        int[] dailyTotals = calculateDailyTotals(sales);
        System.out.println("\n每日總銷售量：");
        for (int j = 0; j < dailyTotals.length; j++) {
            System.out.println("第 " + (j + 1) + " 天: " + dailyTotals[j]);
        }

        // 5. 找出總銷售量最高的商品
        int bestProductIndex = findBestProduct(productTotals);
        System.out.println("\n總銷售量最高的商品是: 商品 " + (bestProductIndex + 1) + " (銷售量: " + productTotals[bestProductIndex] + ")");

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        System.out.println("請輸入 3 項商品在 4 天內的銷售量：");
        for (int i = 0; i < sales.length; i++) { 
            for (int j = 0; j < sales[i].length; j++) { 
                while (true) {
                    System.out.print("請輸入 商品 " + (i + 1) + " 在 第 " + (j + 1) + " 天的銷售量: ");
                    if (sc.hasNextInt()) {
                        int val = sc.nextInt();
                        if (val >= 0) {
                            sales[i][j] = val;
                            break;
                        }
                    } else {
                        sc.next(); 
                    }
                    System.out.println("輸入錯誤！銷售量不能小於 0，請重新輸入。");
                }
            }
        }
    }

    public static void displaySalesTable(int[][] sales) {
        System.out.println("\t\t第1天\t第2天\t第3天\t第4天");
        for (int i = 0; i < sales.length; i++) {
            System.out.print("商品 " + (i + 1) + "\t");
            for (int j = 0; j < sales[i].length; j++) {
                System.out.print(sales[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[] calculateProductTotals(int[][] sales) {
        int[] productTotals = new int[sales.length];
        for (int i = 0; i < sales.length; i++) {
            int sum = 0;
            for (int j = 0; j < sales[i].length; j++) {
                sum += sales[i][j];
            }
            productTotals[i] = sum;
        }
        return productTotals;
    }

    public static int[] calculateDailyTotals(int[][] sales) {
        int daysCount = sales[0].length;
        int[] dailyTotals = new int[daysCount];
        for (int j = 0; j < daysCount; j++) {
            int sum = 0;
            for (int i = 0; i < sales.length; i++) {
                sum += sales[i][j];
            }
            dailyTotals[j] = sum;
        }
        return dailyTotals;
    }

    public static int findBestProduct(int[] productTotals) {
        int maxIndex = 0;
        int maxVal = productTotals[0];
        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > maxVal) {
                maxVal = productTotals[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
