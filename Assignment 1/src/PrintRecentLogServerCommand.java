import java.util.List;

public class PrintRecentLogServerCommand implements ServerCommand{
    @Override
    public void execute(Server server, List<String> arguments) {
        try{
            Logger.getInstance().printRecentLog();
        }catch (Exception e){
            System.out.println("Unable to print recent log.");
        }
    }
}
