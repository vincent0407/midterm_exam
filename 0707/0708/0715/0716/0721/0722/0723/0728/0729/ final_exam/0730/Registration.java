public class Registration {
    private String regId;  
    private String name;    

    public Registration(String regId, String name) {
        this.regId = regId;
        this.name = name;
    }

    public String getRegId() { return regId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return String.format("Registration[ID=%s, Name=%s]", regId, name);
    }
}

