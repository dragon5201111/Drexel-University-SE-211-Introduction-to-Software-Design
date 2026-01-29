import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Quoting Options Interpretation:
 * QUOTE_ALL ->  Fields enclosed in quotes preserve any delimiters or quotes
 * QUOTE_MINIMAL ->
 * QUOTE_NONE -> If a field contains a quote character it will be included in the field
 * Ignored:
 * QUOTE_NONEMPTY ->
 * QUOTE_NONNUMERIC ->
 * QUOTE_STRINGS ->
 * */

public class CsvReader extends CsvIo implements AutoCloseable {
    private boolean strict;
    private final BufferedReader reader;

    public CsvReader(Reader reader, QuotingOption quoting, char delimiter, char quoteChar, char escapeChar, boolean strict) {
        super(quoting, delimiter, quoteChar, escapeChar);
        this.reader = new BufferedReader(reader);
        this.strict = strict;
    }

    public CsvReader(Reader reader) {
        super();
        this.reader = new BufferedReader(reader);
        this.strict = false;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }


    public List<String> readRecord() throws IOException {
        boolean inQuotes = false;
        String line = this.readLine();

        StringBuilder currentField = new StringBuilder();
        List<String> fields = new ArrayList<>();

        int lineLength = line.length();
        for (int i = 0; i < lineLength; i++) {
            char currentChar = line.charAt(i);

            if (currentChar == this.delimiter) {
                fields.add(currentField.toString());
                currentField.setLength(0);
            }else {
                currentField.append(currentChar);
            }
        }
        fields.add(currentField.toString());
        return fields;
    }

    private String readLine() throws IOException {
        String nextLine = this.reader.readLine();
        return nextLine == null ? "" : nextLine;
    }

    @Override
    public void close() throws Exception {
        this.reader.close();
    }

    public static void main(String[] args) {
        try (CsvReader csvReader = new CsvReader(new FileReader(".\\Assignment 2\\test.csv"))){
            List<String> record = csvReader.readRecord();
            System.out.println(record.toString() + record.size());
            System.out.println(csvReader.readRecord());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
