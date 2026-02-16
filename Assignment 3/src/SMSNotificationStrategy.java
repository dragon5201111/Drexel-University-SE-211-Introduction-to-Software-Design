public class SMSNotificationStrategy extends NotificationStrategy {
    public SMSNotificationStrategy(String destination) {
        super(destination);
    }
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS message to " + this.destination + ": " + message);
    }
}
