import java.util.List;

public interface ServerCommand {
    void execute(Server server, List<String> arguments);
}
