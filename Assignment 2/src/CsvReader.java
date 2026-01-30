import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader extends CsvIo implements AutoCloseable {
    private final int HEADER_UNSET_SIZE = -1;
    private enum ReaderState{
        FIELD_START,
        IN_UNQUOTE,
        IN_QUOTE,
        IN_QUOTE_AFTER
    }

    private boolean strict;
    private final BufferedReader reader;
    private int headerLength;

    public CsvReader(Reader reader) {
        this(reader, true);
    }

    public CsvReader(Reader reader, boolean strict) {
        super();
        this.reader = new BufferedReader(reader);
        this.headerLength = HEADER_UNSET_SIZE;
        this.strict = strict;
    }

    public List<List<String>> readRecords() throws IOException {
        List<List<String>> records = new ArrayList<>();

        List<String> currentRecord;
        while (!(currentRecord = readRecord()).isEmpty()){
            records.add(currentRecord);
        }
        return records;
    }

    public List<String> readRecord() throws IOException {
        String nextLine = this.nextLine();
        if (nextLine.isEmpty()){
            return List.of();
        }

        if (this.strict){
            return this.parseFieldsStrict(nextLine);
        }

        return this.parseFieldsUnstrict(nextLine);
    }

    // Parses fields compliant to RFC 4180
    private List<String> parseFieldsStrict(String nextLine) throws IOException {
        ReaderState currentState = ReaderState.FIELD_START;
        List<String> fields = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        int lineLength = nextLine.length();
        for (int i = 0; i < lineLength; i++) {
            char currentChar = nextLine.charAt(i);
            switch (currentState){
                case FIELD_START:
                    if (currentChar == this.delimiter){
                        addField(fields, stringBuilder);
                    }else if (currentChar == this.quoteChar){
                        currentState = ReaderState.IN_QUOTE;
                    }else{
                        stringBuilder.append(currentChar);
                        currentState = ReaderState.IN_UNQUOTE;
                    }
                    break;
                case IN_UNQUOTE:
                    if (currentChar == this.delimiter){
                        addField(fields, stringBuilder);
                        currentState = ReaderState.FIELD_START;
                    }else if (currentChar == this.quoteChar){
                        throw new IOException("Fields with quotes cannot begin without a quote.");
                    }else{
                        stringBuilder.append(currentChar);
                    }
                    break;
                case IN_QUOTE:
                    if (currentChar == this.quoteChar){
                        currentState = ReaderState.IN_QUOTE_AFTER;
                    }else{
                        if (i + 1 >= lineLength){
                            throw new IOException("Unterminated quote.");
                        }

                        stringBuilder.append(currentChar);
                    }
                    break;
                case IN_QUOTE_AFTER:
                    if (currentChar == this.delimiter){
                        addField(fields, stringBuilder);
                        currentState = ReaderState.FIELD_START;
                    }else if (currentChar == this.quoteChar){
                        stringBuilder.append(currentChar);
                        currentState = ReaderState.IN_QUOTE;
                    }else{
                        throw new IOException("Quoted fields may only be followed by a delimiter.");
                    }
                    break;
            }
        }
        fields.add(stringBuilder.toString());

        int fieldsSize = fields.size();
        if (this.headerLength != HEADER_UNSET_SIZE && fieldsSize != this.headerLength){
            throw new IOException("Invalid number of fields in this record. Must match header size.");
        }else{
            this.headerLength = fieldsSize;
        }
        return fields;
    }

    // Parses fields by ignoring CRFL, quotes, and only paying attention to delimiters
    private List<String> parseFieldsUnstrict(String nextLine) {
        List<String> fields = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        int lineLength = nextLine.length();
        for (int i = 0; i < lineLength; i++) {
            char currentChar = nextLine.charAt(i);
            if (currentChar == this.delimiter){
                addField(fields, stringBuilder);
            }else{
                stringBuilder.append(currentChar);
            }
        }

        fields.add(stringBuilder.toString());
        return fields;
    }

    private String nextLine() throws IOException {
        String nextLine = this.reader.readLine();
        return nextLine == null ? "" : nextLine;
    }

    private void addField(List<String> fields, StringBuilder stringBuilder) {
        fields.add(stringBuilder.toString());
        stringBuilder.setLength(0);
    }

    public void setStrict(boolean strict){
        this.strict = strict;
    }

    @Override
    public void close() throws Exception {
        this.reader.close();
    }
}
