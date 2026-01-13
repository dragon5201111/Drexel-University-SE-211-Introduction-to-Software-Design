import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements AutoCloseable {
    public static final String CLIENT_QUIT = "quit";
    private static final String DEFAULT_ADDRESS = "127.0.0.1";
    private final Socket clientSocket;
    private final PrintWriter printWriter;
    private final BufferedReader bufferedReader;

    public Client() throws IOException {
        this(Client.DEFAULT_ADDRESS, Server.DEFAULT_PORT);
    }

    public Client(String address, int port) throws IOException {
        this.clientSocket = new Socket(address, port);
        this.printWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException {
        String line;
        do{
            System.out.print("> ");
            line = this.bufferedReader.readLine();
            writeToServer(line);
        }while (!line.equals(Client.CLIENT_QUIT));
    }

    public void writeToServer(String message) throws IOException {
        this.printWriter.println(message);
    }

    @Override
    public void close() throws Exception {
        this.clientSocket.close();
        this.printWriter.close();
    }

    public static void main(String[] args) {
        try (Client client = new Client()){
            System.out.println("Connecting to server.");
            client.start();
        }catch (Exception e){
            System.out.println("Unable to start client.");
        }
    }
}
