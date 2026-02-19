public class NotificationFactory {
    NotificationStrategy createNotification(String type, String destination){
        if (type.equalsIgnoreCase("sms")){
            return new SMSNotificationStrategy(destination);
        }else if (type.equalsIgnoreCase("email")){
            return new EmailNotificationStrategy(destination);
        }else if (type.equalsIgnoreCase("voicecall")){
            return new VoiceCallNotificationStrategy(destination);
        }
        throw new IllegalArgumentException("Invalid notification type");
    }
}
