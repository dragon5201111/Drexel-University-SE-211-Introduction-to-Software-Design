package lib;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryTest {
    public static String getPrompt(Scanner scanner,String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public static boolean promptEquals(Scanner scanner,String prompt, String expected) {
        return LibraryTest.getPrompt(scanner, prompt).equals(expected);
    }

    public static boolean addRecords(Scanner scanner) {
        return LibraryTest.promptEquals(scanner, "Do you want to keep adding records (y/n)", "y");
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting CSV Library test.");
        System.out.println("This test will ask you to build records for a people.csv file and then will output the results to you.");
        System.out.println("To begin:");

        String delimiter = LibraryTest.getPrompt(scanner, "Enter a delimiter (single character)");
        String quote = LibraryTest.getPrompt(scanner, "Enter a quote (single character)");

        if (delimiter.length() != 1 || quote.length() != 1) {
            System.out.println("Invalid delimiter or quote. Using , and \" for the delimiter and quote character, respectively.");
            delimiter = ",";
            quote = "\"";
        }

        System.out.println("Now prompting for records:");

        try (CsvWriter csvWriter = new CsvWriter(new FileWriter("people.csv"))) {
            csvWriter.setDelimiter(delimiter.charAt(0));
            csvWriter.setQuoteChar(quote.charAt(0));

            List<List<String>> records = new ArrayList<>();

            while (LibraryTest.addRecords(scanner)) {
                List<String> record = new ArrayList<>();
                record.add(LibraryTest.getPrompt(scanner, "Enter a name"));
                record.add(LibraryTest.getPrompt(scanner, "Enter an age (0-100)"));
                record.add(LibraryTest.getPrompt(scanner, "Enter a DOB (mm/dd/yyyy)"));
                record.add(LibraryTest.getPrompt(scanner, "Enter a favorite activity"));
                records.add(record);
            }

            csvWriter.writeRecords(records);
        }catch (IOException e) {
            System.out.println("Unable to write to people.csv.");
            return;
        }

        System.out.println("Successfully added records to people.csv.");

        boolean strict = LibraryTest.promptEquals(scanner, "Do you want strict csv file reading (i.e., RFC 4180 compliant) (y/n)", "y");
        try (CsvReader csvReader = new CsvReader(new FileReader("people.csv"), strict)) {
            csvReader.setDelimiter(delimiter.charAt(0));
            csvReader.setQuoteChar(quote.charAt(0));

            System.out.println("Outputting people.csv:");
            for (List<String> record : csvReader.readRecords()) {
                System.out.println(record);
            }
        }catch (IOException e) {
            System.out.println("Unable to read people.csv");
            return;
        }

        System.out.println("End of CSV Library test.");
    }
}
