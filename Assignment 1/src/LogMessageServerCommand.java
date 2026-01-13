public class LogMessageServerCommand implements ServerCommand{
    @Override
    public void execute(Server server, Object arguments) {
        Logger.getInstance().logLine((String) arguments);
    }
}
