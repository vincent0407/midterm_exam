# 系統資料結構與演算法選擇說明

本文件針對系統中關鍵功能的資料結構與演算法選型進行分析與比較。

---

## 機能與技術對照表

### 1. 報名資料的動態儲存與順序維護
* **使用的資料結構/演算法**：`ArrayList`
* **對應檔案與 Method**：`EventRegistrationSystem.java` - `mainRegistrations` / `register()`
* **選擇原因**：需要動態擴增容量，且支援 $O(1)$ 的隨機存取，便於後續進行排序與查詢。
* **未採用其他方法的原因**：未採用一般原生陣列（`Array`）是因為其長度固定，無法處理動態增加的報名人數。

### 2. 候補名單的先進先出管理
* **使用的資料結構/演算法**：`Queue` (`LinkedList` 實作)
* **對應檔案與 Method**：`EventRegistrationSystem.java` - `waitlistQueue` / `offer()`, `poll()`
* **選擇原因**：候補遵循先來後到的公平原則，`Queue` 提供直觀且 $O(1)$ 的 `offer` 與 `poll` 操作。
* **未採用其他方法的原因**：未採用 `ArrayList` 作為候補佇列是因為在頭部移除元素時會造成 $O(N)$ 的資料搬移開銷。

### 3. 取消紀錄與復原機制 (Undo)
* **使用的資料結構/演算法**：`Stack`
* **對應檔案與 Method**：`EventRegistrationSystem.java` - `cancelStack` / `push()`, `pop()`
* **選擇原因**：取消或復原操作需要後進先出（LIFO）特性，取回最近一次的操作紀錄。
* **未採用其他方法的原因**：未採用 `Queue` 是因為 Queue 會拿到最早的操作，不符合「復原最後動作」的需求。

### 4. 高效且穩定的報名清單排序
* **使用的資料結構/演算法**：`Merge Sort`
* **對應檔案與 Method**：`RegistrationAlgorithms.java` - `sortById()`
* **選擇原因**：時間複雜度最壞與平均皆為 $O(N \log N)$，效能穩定且具備穩定排序（Stable Sort）特性。
* **未採用其他方法的原因**：未採用 Quick Sort 是因為 Quick Sort 最壞情況可能退化至 $O(N^2)$ 且為不穩定排序。

### 5. 已排序資料的快速檢索
* **使用的資料結構/演算法**：`Binary Search`
* **對應檔案與 Method**：`RegistrationAlgorithms.java` - `binarySearchById()`
* **選擇原因**：對於已排序的資料，二分搜尋法能在 $O(\log N)$ 時間內完成精準查詢。
* **未採用其他方法的原因**：未採用 `Sequential Search` 是因為其時間複雜度為 $O(N)$，在大資料量時效率低落。

### 6. 非鍵值（非主鍵）資料的模糊/全域查詢
* **使用的資料結構/演算法**：`Sequential Search`
* **對應檔案與 Method**：`RegistrationAlgorithms.java` - `sequentialSearchByName()`
* **選擇原因**：姓名非排序鍵值且可能有重複值，必須走訪全域清單以確保不遺漏任何匹配項。
* **未採用其他方法的原因**：無法採用 `Binary Search`，因為清單並非依據姓名欄位進行排序。

---

## 資料結構比較分析

| 資料結構 / 演算法 | 主要優勢 | 主要劣勢 | 適用情境 |
| :--- | :--- | :--- | :--- |
| **ArrayList** | 隨機存取快 $O(1)$ | 中間插入與刪除開銷大 $O(N)$ | 頻繁讀取、尾端新增的清單 |
| **Queue** | 符合 FIFO 邏輯，隊列操作 $O(1)$ | 無法直接進行隨機存取 | 排隊、候補、任務佇列 |
| **Stack** | 符合 LIFO 邏輯，頂端操作 $O(1)$ | 無法直接存取底部元素 | 復原 (Undo)、歷史紀錄 |
| **Sequential Search** | 實作簡單，不需預先排序 | 時間複雜度 $O(N)$ 較慢 | 未排序資料或全域掃描 |
| **Binary Search** | 檢索極快 $O(\log N)$ | 資料必須預先排序 | 已排序資料的精準查詢 |
| **Merge Sort** | 穩定性佳，最壞時間 $O(N \log N)$ | 需要額外 $O(N)$ 記憶體空間 | 追求穩定度與時間保障的排序 |
