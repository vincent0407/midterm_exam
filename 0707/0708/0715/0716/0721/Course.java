public class Course {
    private String id;             
    private String name;           
    private int capacity;          
    private int currentEnrolled;    
    public Course(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.currentEnrolled = 0;
    }

    public Course(String id, String name) {
        this(id, name, 50);
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return id; 
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.id = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentEnrolled() {
        return currentEnrolled;
    }

    public boolean isFull() {
        return currentEnrolled >= capacity;
    }

    public void enroll() {
        if (isFull()) {
            System.out.println("❌ 課程 " + name + " 已額滿！");
        } else {
            currentEnrolled++;
            System.out.println("✅ 成功加選課程：" + name);
        }
    }

    public boolean drop() {
        if (currentEnrolled > 0) {
            currentEnrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String status = isFull() ? "[已額滿]" : "[可加選]";
        return String.format("代碼: %-6s | 名稱: %-12s | 人數: %2d/%-2d %s",
                id, name, currentEnrolled, capacity, status);
    }
}
