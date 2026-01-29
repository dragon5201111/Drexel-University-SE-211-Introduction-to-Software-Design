import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CsvReader extends CsvIo implements AutoCloseable, Iterator<List<String>> {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<List<String>> readRecords() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void close() throws Exception {
        this.reader.close();
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> next() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
