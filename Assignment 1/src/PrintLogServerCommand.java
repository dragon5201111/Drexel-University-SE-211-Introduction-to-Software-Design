import java.util.List;

public class PrintLogServerCommand implements ServerCommand{
    @Override
    public void execute(Server server,  List<String> arguments) {
        try{
            Logger.getInstance().printLog();
        }catch(Exception e){
            System.out.println("Unable to print log.");
        }
    }
}
