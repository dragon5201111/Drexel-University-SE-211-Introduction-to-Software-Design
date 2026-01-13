import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private final Server server;
    private final Socket clientSocket;
    private final BufferedReader bufferedReader;

    public ClientHandler(Server server, Socket clientSocket) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        while (this.server.isAlive()) {
            try {
               this.processCommand();
            } catch (IOException e) {
                System.out.println("Unable to read from client socket.");
                throw new RuntimeException(e);
            }
        }

        try {
            this.close();
        }catch (IOException e) {
            System.out.println("Unable to close client socket and buffered reader.");
        }

        System.out.println("Client disconnected.");
    }

    private void processCommand() throws IOException {
        String line = this.bufferedReader.readLine();
        List<String> lineItems = List.of(line.split(" "));
        ServerCommand serverCommand = ServerCommandFactory.getServerCommand(lineItems.get(0));
        serverCommand.execute(this.server, lineItems.subList(1, lineItems.size()));
    }

    public void close() throws IOException {
        this.clientSocket.close();
        this.bufferedReader.close();
    }
}
