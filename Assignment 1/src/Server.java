import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements AutoCloseable{
    public static final int DEFAULT_PORT = 1027;
    private final ServerSocket serverSocket;
    private boolean alive;

    public Server() throws IOException {
        this(Server.DEFAULT_PORT);
    }

    public Server(int port) throws IOException {
        this(port, 0);
    }

    public Server(int port, int backlog) throws IOException {
        this.serverSocket = new ServerSocket(port, backlog);
        this.alive = false;
    }

    public void start() throws IOException {
        this.alive = true;
        while (this.alive){
            Socket clientSocket = this.serverSocket.accept();
            // TODO: Finish here.
        }
    }

    @Override
    public void close() throws Exception {
        this.alive = false;
        this.serverSocket.close();
    }

    public static void main(String[] args) {
        try (Server server = new Server()){
            server.start();
        }catch (Exception e){
            System.out.println("Error, server startup failed.");
            throw new RuntimeException(e);
        }
    }
}
