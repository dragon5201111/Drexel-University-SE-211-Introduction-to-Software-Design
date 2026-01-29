import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CsvWriter extends CsvIo implements AutoCloseable, Flushable {
    private final Writer writer;
    private final String replacementRegex =
            "[" +
            Pattern.quote(String.valueOf(delimiter)) +
            Pattern.quote(String.valueOf(quoteChar)) +
            "\n\r]";

    public CsvWriter(Writer writer, QuotingOption quoting, char delimiter, char quoteChar, char escapeChar) {
        super(quoting, delimiter, quoteChar, escapeChar);
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
                .map(this::applyQuoting)
                .collect(Collectors.joining(String.valueOf(this.delimiter
                )));
        this.writer.write(line + System.lineSeparator());
    }

    private String applyQuoting(String field){
        if (field.isEmpty()){
            return field;
        }

        if (quoting == QuotingOption.QUOTE_ALL){
            String quoteString = String.valueOf(quoteChar);
            return quoteChar +  field.replace(quoteString, quoteString + quoteString) + quoteChar;
        }

        if (quoting == QuotingOption.QUOTE_NONE){
            if (escapeChar == DEFAULT_ESCAPE_CHAR){
                throw new RuntimeException("Escape char must be set.");
            }

            Matcher matcher = Pattern.compile(replacementRegex).matcher(field);
            StringBuilder stringBuilder = new StringBuilder();

            while (matcher.find()) {
                matcher.appendReplacement(stringBuilder, Matcher.quoteReplacement(escapeChar + matcher.group()));
            }

            matcher.appendTail(stringBuilder);
            return stringBuilder.toString();
        }

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

    public static void main(String[] args) throws IOException {
        Writer writer = new BufferedWriter(new FileWriter(".\\Assignment 2\\test.csv"));
        try (CsvWriter csvWriter = new CsvWriter(writer, QuotingOption.QUOTE_ALL, ',', '\"', '\\')) {
            csvWriter.writeRecord(List.of("fooo\"fooo\"ff", "COE", "2", "9.0"));
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }
}
