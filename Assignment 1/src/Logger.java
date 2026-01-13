import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {
    public static final String DEFAULT_LOG_FILE = "log.txt";
    private static Logger logger;

    private final Path logFilePath;

    private Logger() throws IOException {
        this.logFilePath = Paths.get(Logger.DEFAULT_LOG_FILE);
        this.initializeLogFile();
    }

    private synchronized void initializeLogFile() throws IOException {
        if (Files.exists(this.logFilePath)) {
            return;
        }

        Files.createFile(this.logFilePath);
    }

    public static synchronized Logger getInstance() throws IOException {
        if (Logger.logger == null) {
            Logger.logger = new Logger();
        }
        return Logger.logger;
    }

    public synchronized void logLine(String message) {
        try (PrintWriter printWriter = new PrintWriter(
                Files.newBufferedWriter(
                        this.logFilePath,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND))) {
            printWriter.println(message);
        } catch (Exception e) {
            System.out.println("Error logging to log file: " + e.getMessage());
        }
    }

    public synchronized void printLog() throws IOException {
        List<String> lines = Files.readAllLines(this.logFilePath);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public synchronized void printRecentLog() throws IOException {
        List<String> lines = Files.readAllLines(this.logFilePath);
        if (lines.isEmpty()) {
            return;
        }

        System.out.println(lines.get(lines.size() - 1));
    }

    public synchronized void printMatchingLines(String targetLine) throws IOException {
        List<String> lines = Files.readAllLines(this.logFilePath)
                .stream()
                .filter(line -> line.contains(targetLine))
                .collect(Collectors.toList());
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
