public class SMSNotificationStrategy implements NotificationStrategy {
    private final String phoneNumber;

    public SMSNotificationStrategy(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS message to " + this.phoneNumber + ": " + message);
    }
}
