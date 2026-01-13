import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements AutoCloseable {
    private final ServerSocket serverSocket;
    private boolean alive;
    private final ExecutorService executor;
    private final List<ClientHandler> clientHandlers = new CopyOnWriteArrayList<>();

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.alive = false;
        this.executor = Executors.newCachedThreadPool();
    }

    public void start() {
        this.setAlive();
        try {
            while (this.isAlive()) {
                Socket clientSocket = this.serverSocket.accept();
                ClientHandler handler = new ClientHandler(this, clientSocket);
                this.clientHandlers.add(handler);
                this.executor.execute(handler);
            }
        } catch (IOException e) {
            System.out.println("Server has been shut down.");
        }
    }

    public synchronized void shutdown() throws IOException {
        this.alive = false;
        for (ClientHandler handler : clientHandlers) {
            handler.close();
        }
        this.serverSocket.close();
        this.executor.shutdownNow();
    }

    private synchronized void setAlive() { this.alive = true; }

    public synchronized boolean isAlive() { return this.alive; }

    @Override
    public void close() throws Exception {
        if (this.isAlive()) this.shutdown();
    }

    public static void main(String[] args) {
        try (Server server = new Server(Util.getPortFromStdin())) {
            System.out.println("Server has been started.");
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}