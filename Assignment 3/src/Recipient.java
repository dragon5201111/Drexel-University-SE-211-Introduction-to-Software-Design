public abstract class Recipient implements Observer {
    private NotificationStrategy notificationStrategy;

    public Recipient(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    Recipient setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
        return this;
    }

    NotificationStrategy getNotificationStrategy() {
        return this.notificationStrategy;
    }

    @Override
    public void update(String message) {
        this.notificationStrategy.sendNotification(message);
    }
}
