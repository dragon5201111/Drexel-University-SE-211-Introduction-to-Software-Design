public class EmailNotificationStrategy extends NotificationStrategy {

    public EmailNotificationStrategy(String destination) {
        super(destination);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email to " + this.destination + ": " + message);
    }
}
