public abstract class CsvIo {
    public static char DEFAULT_DELIMITER = ',';
    public static char DEFAULT_QUOTE_CHAR = '"';

    protected char delimiter;
    protected char quoteChar;

    public CsvIo(char delimiter, char quoteChar) {
        this.delimiter = delimiter;
        this.quoteChar = quoteChar;
    }

    public CsvIo() {
        this(CsvIo.DEFAULT_DELIMITER, CsvIo.DEFAULT_QUOTE_CHAR);
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public void setQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public char getQuoteChar() {
        return quoteChar;
    }
}
