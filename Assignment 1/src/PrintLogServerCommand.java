public class PrintLogServerCommand implements ServerCommand{
    @Override
    public void execute(Server server, Object arguments) {
        try{
            Logger.getInstance().printLog();
        }catch(Exception e){
            System.out.println("Unable to print log.");
        }
    }
}
