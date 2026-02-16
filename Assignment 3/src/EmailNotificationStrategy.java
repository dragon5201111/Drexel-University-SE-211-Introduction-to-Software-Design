public class EmailNotificationStrategy implements NotificationStrategy {
    private final String email;

    public EmailNotificationStrategy(String email) {
        this.email = email;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email to " + this.email + ": " + message);
    }
}
