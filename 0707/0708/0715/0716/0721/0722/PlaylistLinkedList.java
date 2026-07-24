public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        this.head = null;
    }

    public boolean containsId(String id) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean addLast(String id, String title) {
        if (containsId(id)) {
            System.out.println("【新增失敗】歌曲代碼已存在: " + id);
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(id, title);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("成功新增歌曲: [" + id + "] " + title);
        return true;
    }

    public PlaylistNode searchById(String id) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean removeById(String id) {
        if (head == null) {
            System.out.println("【刪除失敗】播放清單為空！");
            return false;
        }

        if (head.id.equals(id)) {
            System.out.println("成功刪除歌曲 (第一首): [" + head.id + "] " + head.title);
            head = head.next;
            return true;
        }

        PlaylistNode current = head;
        while (current.next != null && !current.next.id.equals(id)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("【刪除失敗】找不到歌曲代碼: " + id);
            return false;
        }


        System.out.println("成功刪除歌曲: [" + current.next.id + "] " + current.next.title);
        current.next = current.next.next;
        return true;
    }

    public void printPlayList() {
        if (head == null) {
            System.out.println("播放清單內容：[ 播放清單為空 ]");
            return;
        }

        System.out.println("--- 完整播放順序 ---");
        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". 代碼: " + current.id + " | 歌名: " + current.title);
            current = current.next;
            index++;
        }
        System.out.println("--------------------");
    }
}
