public class ContestRankingSystem {

    public static void insertionSortContestants(Contestant[] list) {
        for (int i = 1; i < list.length; i++) {
            Contestant key = list[i];
            int j = i - 1;

            while (j >= 0 && shouldSwap(list[j], key)) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
    }

    private static boolean shouldSwap(Contestant current, Contestant key) {
        if (current.getScore() < key.getScore()) {
            return true;
        } else if (current.getScore() == key.getScore()) {
            return current.getSeconds() > key.getSeconds();
        }
        return false;
    }

    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C001", "Alice", 85, 120.5),
            new Contestant("C002", "Bob", 92, 110.0),
            new Contestant("C003", "Charlie", 85, 115.2), 
            new Contestant("C004", "David", 95, 105.8),
            new Contestant("C005", "Eve", 92, 108.4)     
        };

        System.out.println("=== 排序前名冊 ===");
        for (Contestant c : contestants) {
            System.out.println(c);
        }

        insertionSortContestants(contestants);

        System.out.println("\n=== 最終排行榜 ===");
        for (int i = 0; i < contestants.length; i++) {
            System.out.printf("第 %d 名 -> %s\n", (i + 1), contestants[i]);
        }
    }
}
