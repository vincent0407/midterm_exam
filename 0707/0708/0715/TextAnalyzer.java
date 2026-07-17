import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = getInput(scanner);

        System.out.println("原始字元數: " + input.length());

        String trimmedInput = input.trim();
        System.out.println("trim() 後有效字元數: " + trimmedInput.length());

        String[] words = splitWords(trimmedInput);
        
        System.out.println("單字數量: " + words.length);

        int vowelCount = countVowels(trimmedInput);
        System.out.println("英文字母母音總數: " + vowelCount);

        String longestWord = findLongestWord(words);
        System.out.println("最長單字: " + longestWord);

        System.out.print("請輸入要搜尋的關鍵字: ");
        String keyword = scanner.next();
        int keywordCount = countKeyword(words, keyword);
        System.out.println("關鍵字 [" + keyword + "] 出現次數 (忽略大小寫): " + keywordCount);

        scanner.close();
    }

    public static String getInput(Scanner scanner) {
        String input = "";
        while (true) {
            System.out.print("請輸入一行非空白文字: ");
            input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("錯誤：不能輸入空字串或全空白，請重新輸入！");
            } else {
                break;
            }
        }
        return input;
    }

    public static String[] splitWords(String text) {

        if (text.isEmpty()) {
            return new String[0];
        }
        return text.split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static int countKeyword(String[] words, String keyword) {
        int count = 0;
        for (String word : words) {
            // 使用 equalsIgnoreCase 忽略大小寫比較
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }
}
