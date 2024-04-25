package Others;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TextDBPayment extends TextDB {

    private static final String SEPARATOR = ",";

    /**
     * Reads payment methods from a text file and optionally creates a mapping of payment method names
     * to their corresponding class names.
     * 
     * @param filename The path of the text file to read from.
     * @param createMap Determines whether to return a list of names or a map of names to class names.
     * @return Either a List of strings (payment method names) or a Map of names to class names.
     * @throws IOException If an error occurs during file reading.
     */
    public static List<String[]> readPaymentMethods(String filename, boolean createMap) throws IOException {
        List<String> stringArray = read(filename);
        List<String[]> alr = new ArrayList<>();

        for (String line : stringArray) {
            StringTokenizer star = new StringTokenizer(line, SEPARATOR);
            String name = star.nextToken().trim();  // first token
            String className = star.nextToken().trim();  // second token
            alr.add(new String[]{name, className});
        }
        return alr;
    }

    /**
     * Saves the data of payment method mappings to a text file.
     * 
     * @param FILENAME The path of the file to write to.
     * @param al A List of string arrays, each containing a payment method name and class name.
     * @throws IOException If an error occurs during file writing.
     */
    public static void savePaymentMethods(String FILENAME, List<String[]> al) throws IOException {
        List<String> alw = new ArrayList<>();  // to store payment method data as strings

        for (String[] data : al) {
            String st = data[0].trim() + SEPARATOR + data[1].trim();
            alw.add(st);
        }
        write(FILENAME, alw);
    }
}

