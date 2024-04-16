package Others;

import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;


/**
 * This class has functions to implement the saving of entities into their respective repositories
 * for the purpose of data persistence. The repositories come in the form of text files which can be read 
 * from and written to using the readXX and saveXX functions, where XX represents the entity.
 * 
 * The code for this class was adapted from the code provided on NTULearn.
 * @author Saffron Lim
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class TextDB {
    
    public static final String SEPARATOR = "|";

    /**
     * This function writes fixed content to a given file. It is called by saveEmployee()
     * @param FILENAME is the String directory of the file to write to.
     * @param data is a List of the data to write to the file.
     * @throws IOException
     * 
     */
    public static void write(String FILENAME, List data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(FILENAME));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }

    /**
     * This function reads the contents of the given file and is called by readEmployee().
     * @param FILENAME is the String directory of the file to read from.
     * @return a List of String content.
     * @throws IOException
     * 
     */
    
    public static List read(String FILENAME) throws IOException {
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(FILENAME));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

}
