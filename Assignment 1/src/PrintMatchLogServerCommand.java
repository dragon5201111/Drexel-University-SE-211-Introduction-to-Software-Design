import java.util.List;

public class PrintMatchLogServerCommand implements ServerCommand{
    @Override
    public void execute(Server server,  List<String> arguments) {
        try {
            Logger.getInstance().printMatchingLines(arguments.getFirst());
        }catch (Exception e){
            System.out.println("Unable to print matching lines from log.");
        }
    }
}
