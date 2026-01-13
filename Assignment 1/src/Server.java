import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements AutoCloseable{
    public static final int DEFAULT_PORT = 1027;
    private final ServerSocket serverSocket;
    private boolean alive;
    private final ExecutorService executor;

    public Server() throws IOException {
        this(Server.DEFAULT_PORT);
    }

    public Server(int port) throws IOException {
        this(port, 0);
    }

    public Server(int port, int backlog) throws IOException {
        this.serverSocket = new ServerSocket(port, backlog);
        this.alive = false;
        this.executor = Executors.newCachedThreadPool();
    }

    public void start() {
        this.setAlive();
        try{
            while (this.isAlive()) {
                Socket clientSocket = this.serverSocket.accept();
                this.executor.execute(new ClientHandler(this, clientSocket));
            }
        }catch(IOException e){
            System.out.println("Server has been shut down.");
        }
    }

    private synchronized void setAlive(){
        this.alive = true;
    }

    @Override
    public void close() throws Exception {
        if (this.isAlive()){
            this.shutdown();
        }
    }

    public synchronized void shutdown() throws IOException {
        this.alive = false;
        this.serverSocket.close();
        this.executor.shutdownNow();
    }

    public synchronized boolean isAlive(){
        return this.alive;
    }

    public static void main(String[] args) {
        try (Server server = new Server()){
            System.out.println("Server has been started.");
            server.start();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
