public abstract class NotificationStrategy {
    protected String destination;

    public NotificationStrategy(String destination) {
        this.destination = destination;
    }

    abstract void sendNotification(String message);
}
