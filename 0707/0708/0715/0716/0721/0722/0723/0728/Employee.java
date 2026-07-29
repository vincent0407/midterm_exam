public class Employee {
    private String id;        
    private String name;     
    private String department;
    private String ext;       

    public Employee(String id, String name, String department, String ext) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.ext = ext;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("員工編號: %s | 姓名: %s | 部門: %s | 分機: %s", id, name, department, ext);
    }
}
