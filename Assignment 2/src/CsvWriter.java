import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class CsvWriter extends CsvIo implements AutoCloseable, Flushable {
    private final Writer writer;

    public CsvWriter(Writer writer, char delimiter, char quote) {
        super(delimiter, quote);
        this.writer = writer;
    }

    public CsvWriter(Writer writer) {
        super();
        this.writer = writer;
    }

    public void writeRecords(List<List<String>> records) throws IOException {
        for (List<String> record : records) {
            writeRecord(record);
        }
    }

    public void writeRecord(List<String> record) throws IOException {
        String line = record.stream()
                .map(this::to_valid_field)
                .collect(Collectors.joining(String.valueOf(this.delimiter
                )));
        this.writer.write(line + System.lineSeparator());
    }

    private String to_valid_field(String field) {
        System.out.println("Not implemented yet, finish.");
        return field;
    }

    @Override
    public void close() throws Exception {
        this.writer.close();
    }

    @Override
    public void flush() throws IOException {
        this.writer.flush();
    }

    public static void main(String[] args) {
        try (CsvWriter csvWriter = new CsvWriter(new BufferedWriter(new FileWriter(".\\Assignment 2\\test.csv")))){
            csvWriter.writeRecord(List.of("a", "b", "c", "d"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
