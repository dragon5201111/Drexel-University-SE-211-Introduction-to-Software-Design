public class VoiceCallNotificationStrategy extends NotificationStrategy {
    public VoiceCallNotificationStrategy(String destination) {
        super(destination);
    }

    @Override
    void sendNotification(String message) {
        System.out.println("Sending voice call message to " + this.destination + ": " + message);
    }
}
