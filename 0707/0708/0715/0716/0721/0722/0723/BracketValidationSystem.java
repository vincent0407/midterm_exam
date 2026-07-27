import java.util.Stack;

public class BracketValidationSystem {

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } 
            else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // 檢查是否對應 match
                if (c == ')' && top != '(') return false;
                if (c == ']' && top != '[') return false;
                if (c == '}' && top != '{') return false;
            }
        
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        String[] testCases = {
            "a * (b + c) - [d / {e}]", 
            "(a + b] * c",            
            "((a + b)",               
            "a + b)",                
            "{[ ( ] )}"               
        };

        System.out.println("=== 括號驗證測試結果 ===");
        for (String test : testCases) {
            boolean result = isValid(test);
            System.out.printf("測試字串: %-25s -> 驗證結果: %s\n", "\"" + test + "\"", result ? "合法" : "不合法");
        }
    }
}
