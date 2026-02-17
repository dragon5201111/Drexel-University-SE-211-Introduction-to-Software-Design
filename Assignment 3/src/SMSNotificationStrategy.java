public class SMSNotificationStrategy extends NotificationStrategy {
    public SMSNotificationStrategy(String destination) {
        super(destination);
    }
    @Override
    public void sendNotification(Message message) {
        System.out.println("Sending SMS message to " + this.destination + ": " + message.getContent());
    }
}
