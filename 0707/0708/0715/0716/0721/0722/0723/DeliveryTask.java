public class DeliveryTask {
    private String taskId;    
    private String destination;
    private String recipient;  

    public DeliveryTask(String taskId, String destination, String recipient) {
        this.taskId = taskId;
        this.destination = destination;
        this.recipient = recipient;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDestination() {
        return destination;
    }

    public String getRecipient() {
        return recipient;
    }

    @Override
    public String toString() {
        return "任務[" + taskId + "] 目的地: " + destination + " (收件人: " + recipient + ")";
    }
}
