public class ServerCommandFactory {
    public static ServerCommand getServerCommand(String command) {
        String commandLower = command.toLowerCase();
        switch (commandLower){
            case "logmessage":
                return new LogMessageServerCommand();
            case "printlog":
                return new PrintLogServerCommand();
            case "printrecent":
                return new PrintRecentLogServerCommand();
            case "printtext":
                return new PrintMatchLogServerCommand();
            case Client.CLIENT_QUIT:
                return new QuitServerCommand();
        }

        return new InvalidServerCommand();
    }
}
