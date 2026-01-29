public abstract class CsvIo {
    static char DEFAULT_DELIMITER = ',';
    static char DEFAULT_QUOTE_CHAR = '"';
    static char DEFAULT_ESCAPE_CHAR = '\0';

    protected QuotingOption quoting;
    protected char delimiter;
    protected char quoteChar;
    protected char escapeChar;

    public CsvIo(QuotingOption quoting, char delimiter, char quoteChar, char escapeChar) {
        this.quoting = quoting;
        this.delimiter = delimiter;
        this.quoteChar = quoteChar;
        this.escapeChar = escapeChar;
    }

    public void setQuoting(QuotingOption quoting) {
        this.quoting = quoting;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public void setQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
    }

    public void setEscapeChar(char escapeChar) {
        this.escapeChar = escapeChar;
    }

    public CsvIo() {
        this(QuotingOption.QUOTE_MINIMAL, DEFAULT_DELIMITER, DEFAULT_QUOTE_CHAR, DEFAULT_ESCAPE_CHAR);
    }
}
