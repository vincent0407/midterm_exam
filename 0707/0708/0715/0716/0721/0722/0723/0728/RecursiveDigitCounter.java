public class RecursiveDigitCounter {

    public static int countDigit(int number, int target) {
        if (target < 0 || target > 9) {
            throw new IllegalArgumentException("target 必須介於 0 到 9 之間");
        }

        if (number < 0) {
            number = Math.abs(number);
        }

        if (number < 10) {
            return (number == target) ? 1 : 0;
        }

        int lastDigit = number % 10;
        int match = (lastDigit == target) ? 1 : 0;

        return match + countDigit(number / 10, target);
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {73323, 3},  
            {12345, 9}, 
            {0, 0},      
            {88888, 8},  
            {102030, 0},
            {54321, 5}   
        };

        System.out.println("=== 課後作業一測試結果 ===");
        for (int[] test : testCases) {
            int num = test[0];
            int target = test[1];
            int count = countDigit(num, target);
            System.out.printf("數字: %-8d | 目標數字: %d | 出現次數: %d\n", num, target, count);
        }
    }
}
