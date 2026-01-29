import java.util.List;

public class CsvWriter {

    void writeHeader(List<String> header){

    }

    void writeRecords(List<List<String>> records){
        for (List<String> record : records) {
            writeRecord(record);
        }
    }

    void writeRecord(List<String> record){

    }

}
