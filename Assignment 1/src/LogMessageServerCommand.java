import java.util.List;

public class LogMessageServerCommand implements ServerCommand{
    @Override
    public void execute(Server server,  List<String> arguments) {
        try{
            Logger.getInstance().logLine(String.join(" ", arguments));
        }catch (Exception e){
            System.out.println("Unable to log line.");
        }
    }
}
