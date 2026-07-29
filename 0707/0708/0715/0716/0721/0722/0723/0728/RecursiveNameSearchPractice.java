public class RecursiveNameSearchPractice {

    public static int search(String[] names, String target, int index) {
       
        if (index >= names.length) {
            return -1;
        }
        if (names[index] != null && names[index].equals(target)) {
            return index;
        }
    
        return search(names, target, index + 1);
    }

    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eve"};

        System.out.println("=== 測試案例 ===");
        
        System.out.println("搜尋 'Alice' (第一筆): " + search(names, "Alice", 0));
        
        System.out.println("搜尋 'Eve' (最後一筆): " + search(names, "Eve", 0));
        
        System.out.println("搜尋 'Frank' (不存在): " + search(names, "Frank", 0));
        
        String[] emptyArray = {};
        System.out.println("搜尋空陣列: " + search(emptyArray, "Alice", 0));
    }
}
