public class InfoMessageDecorator extends MessageDecorator{
    public InfoMessageDecorator(Message message) {
        super(message);
    }

    @Override
    public String getContent() {
        return "[INFO] " + this.message.getContent();
    }
}
