public abstract class Recipient implements Observer {
    private final NotificationStrategy notificationStrategy;

    public Recipient(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    @Override
    public void update(String message) {
        this.notificationStrategy.sendNotification(message);
    }
}
