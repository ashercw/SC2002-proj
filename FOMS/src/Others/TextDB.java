package Others;

import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
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


    /**
     * This function modifies a specific string in a .txt file.
     * @param filePath String with the file path.
     * @param oldString String in the .txt file that needs to be replaced.
     * @param newString String to replace oldString with.
     * 
     */
    public static void modifyFile(String filePath, String oldString, String newString, String userID) {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        String newLine = "";
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            // Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) {
                if(line.indexOf(userID)!=-1) {
                    //string found
                    newLine = line.replaceAll(oldString, newString);
                }
                //check if string was amended
                if(newLine != "") 
                {
                    oldContent = oldContent + newLine + System.lineSeparator();
                    newLine = "";
                }
                else oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();

                if(line == null) break;
            }
            // Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(oldContent);
        } catch (NoSuchElementException e) {
        } 
        catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                // Closing the resources
                reader.close();
                if(writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This function checks whether a file is empty or does not exist.
     * @param filePath String path of file to check.
     * @return boolean, true if file is not empty, false if file is empty or does not exist.
     * 
     */
    public static boolean isEmptyFile(String filePath) {

        File file = new File(filePath);
        if (file.exists()) {

            if (file.length() == 0) {
                // File empty
                //System.out.println("File is empty");
                return false;
            }
            //System.out.println("File not is empty");
            return true;
        }
        //else System.out.println("File DNE");
        return false;
        
    }

    


}
