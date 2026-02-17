public abstract class Notifiable implements Observer {
    protected NotificationStrategy notificationStrategy;

    Notifiable setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
        return this;
    }

    NotificationStrategy getNotificationStrategy() {
        return this.notificationStrategy;
    }

    @Override
    public void update(Message message) {
        this.notificationStrategy.sendNotification(message);
    }
}
