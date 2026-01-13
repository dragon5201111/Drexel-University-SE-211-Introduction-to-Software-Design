public class QuitServerCommand implements ServerCommand{
    @Override
    public void execute(Server server, Object arguments) {
        try{
            server.shutdown();
        }catch(Exception e){
            System.out.println("Unable to shutdown server.");
        }
    }
}
