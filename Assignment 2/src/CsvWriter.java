import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CsvWriter extends CsvIo implements AutoCloseable, Flushable {
    private final Writer writer;
    private final Map<String, Pattern> patternCache = new HashMap<>();

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
                .map(this::formatField)
                .collect(Collectors.joining(String.valueOf(this.delimiter
                )));
        this.writer.write(line + System.lineSeparator());
    }

    private String formatField(String field){
        if (field.isEmpty() && this.quoting == QuotingOption.QUOTE_NONEMPTY){
            return "";
        }

        if (this.quoting == QuotingOption.QUOTE_NONNUMERIC || this.quoting == QuotingOption.QUOTE_STRINGS) {
            return isNumeric(field) ? field : applyQuotes(field);
        }

        if (this.quoting == QuotingOption.QUOTE_ALL || this.quoting == QuotingOption.QUOTE_NONEMPTY) {
            return applyQuotes(field);
        }

        if (this.quoting == QuotingOption.QUOTE_MINIMAL && this.getReplacementPattern().matcher(field).find()){
            return applyQuotes(field);
        }

        if (this.quoting == QuotingOption.QUOTE_NONE){
            if (escapeChar == DEFAULT_ESCAPE_CHAR){
                throw new RuntimeException("Escape char must be set.");
            }

            return prefixEscapeChar(field);
        }

        return field;
    }

    private boolean isNumeric(String field){
        try {
            Double.parseDouble(field);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String applyQuotes(String field){
        String quoteString = String.valueOf(this.quoteChar);
        return this.quoteChar +  field.replace(quoteString, quoteString + quoteString) + this.quoteChar;
    }

    private String prefixEscapeChar(String field){
        Matcher matcher = this.getReplacementPattern().matcher(field);
        StringBuilder stringBuilder = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(stringBuilder, Matcher.quoteReplacement(this.escapeChar + matcher.group()));
        }

        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }

    private Pattern getReplacementPattern(){
        // Delimiter and QuoteChar can change, cache old patterns
        return this.patternCache
                .computeIfAbsent(this.delimiter + "" + this.quoteChar, key ->
                        Pattern.compile("[" +  Pattern.quote(String.valueOf(this.delimiter)) +  Pattern.quote(String.valueOf(this.quoteChar)) +  "\n\r]"));
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
        Writer writer = new BufferedWriter(new FileWriter(".\\Assignment 2\\test.csv", true));
        try (CsvWriter csvWriter = new CsvWriter(writer)) {
            List<List<String>> records = List.of(
              List.of("foo:,", "::b,ar\"", "jar"),
              List.of("1", "2", "4")
            );
            csvWriter.writeRecords(records);
            csvWriter.setDelimiter(',');
            csvWriter.setQuoteChar(',');
            csvWriter.writeRecords(records);
        }catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }
}
