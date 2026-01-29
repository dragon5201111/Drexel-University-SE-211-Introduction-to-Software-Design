public class CsvIo {
    static char DEFAULT_DELIMITER = ',';
    static char DEFAULT_QUOTE = '"';

    protected char delimiter;
    protected char quote;

    public CsvIo(char delimiter, char quote) {
        this.delimiter = delimiter;
        this.quote = quote;
    }

    public CsvIo() {
        this(DEFAULT_DELIMITER, DEFAULT_QUOTE);
    }
}
