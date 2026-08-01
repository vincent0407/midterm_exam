public class RepairTask {
    private String taskId;      
    private String deviceName;   
    private int priority;       
    private int registerOrder;   

    public RepairTask(String taskId, String deviceName, int priority, int registerOrder) {
        this.taskId = taskId;
        this.deviceName = deviceName;
        this.priority = priority;
        this.registerOrder = registerOrder;
    }

    public String getTaskId() { return taskId; }
    public String getDeviceName() { return deviceName; }
    public int getPriority() { return priority; }
    public int getRegisterOrder() { return registerOrder; }

    @Override
    public String toString() {
        return String.format("Task[ID=%s, Device=%s, Priority=%d, Order=%d]",
                taskId, deviceName, priority, registerOrder);
    }
}
