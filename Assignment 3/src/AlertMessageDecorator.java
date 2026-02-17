public class AlertMessageDecorator extends MessageDecorator{
    public AlertMessageDecorator(Message message) {
        super(message);
    }

    @Override
    public String getContent() {
        return "[ALERT] " + this.message.getContent();
    }
}
