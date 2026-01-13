import java.util.List;

public class InvalidServerCommand implements ServerCommand{

    @Override
    public void execute(Server server,  List<String> arguments) {
        System.out.println("Invalid Command.");
    }
}
