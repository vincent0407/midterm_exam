public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("=== 測試 1：尾端新增歌曲 ===");
        playlist.addLast("S001", "Song A");
        playlist.addLast("S002", "Song B");
        playlist.addLast("S003", "Song C");
        playlist.addLast("S004", "Song D");
        playlist.printPlayList();

        System.out.println("\n=== 測試 2：新增重複歌曲代碼 ===");
        playlist.addLast("S002", "Duplicate Song B"); 

        System.out.println("\n=== 測試 3：搜尋歌曲 ===");
        PlaylistNode target = playlist.searchById("S003");
        if (target != null) {
            System.out.println("搜尋成功 -> [" + target.id + "] " + target.title);
        } else {
            System.out.println("搜尋失敗！");
        }

        System.out.println("\n=== 測試 4：刪除第一首 (S001) ===");
        playlist.removeById("S001");
        playlist.printPlayList();

        System.out.println("\n=== 測試 5：刪除最後一首 (S004) ===");
        playlist.removeById("S004");
        playlist.printPlayList();

        System.out.println("\n=== 測試 6：刪除不存在的代碼 (S999) ===");
        playlist.removeById("S999");

        System.out.println("\n=== 測試 7：清空串列並測試空串列操作 ===");
        playlist.removeById("S002");
        playlist.removeById("S003");
        playlist.printPlayList();
        playlist.removeById("S001"); 
    }
}
