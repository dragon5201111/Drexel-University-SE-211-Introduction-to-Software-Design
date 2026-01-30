public abstract class CsvIo {
    static char DEFAULT_DELIMITER = ',';
    static char DEFAULT_QUOTE_CHAR = '"';

    protected QuotingOption quoting;
    protected char delimiter;
    protected char quoteChar;

    public CsvIo(char delimiter, char quoteChar) {
        this.delimiter = delimiter;
        this.quoteChar = quoteChar;
    }

    public CsvIo setDelimiter(char delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public CsvIo setQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
        return this;
    }

    public char getDelimiter() { return delimiter; }
    public char getQuoteChar() { return quoteChar; }

    public CsvIo() {
        this(CsvIo.DEFAULT_DELIMITER, CsvIo.DEFAULT_QUOTE_CHAR);
    }
}
