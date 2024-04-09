package Others;

import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The UserInput class will provide functions that gets various user inputs
 * (i.e., int, String, etc)
 * from the command line terminal.
 */

public class IO {

    

    /**
     * Gets integer user input.
     * @return an integer
     */

    public static int userInputInt() {
        Scanner sc = new Scanner(System.in);
        try {
            int userInput = sc.nextInt();
            return userInput;
        } catch (Exception e) {
            System.out.println("Please enter an integer.");
            return userInputInt();
        }
    }

    /**
     * Gets String user input.
     * @return a String.
     */
    public static String userInpuString()
    {
        Scanner sc = new Scanner(System.in);
        try {
            String userInput = sc.nextLine();
            return userInput;
        } catch (Exception e) {
            System.out.println("Please enter an integer.");
            return userInpuString();
        }
    }

    /**
     * Reads a .CSV file and returns its contents for further processing.
     * 
     * @param FilePath The filepath of the .csv file
     * @return List<List<String>>. Each item in List<List<String>> is a row that is
     *         represented as a List<String>.
     */

    public static List<List<String>> readCSV(String FilePath) {
        String line = "";
        List<List<String>> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(FilePath));
            br.readLine();
            while ((line = br.readLine()) != null) {
                List<String> row = new ArrayList<>();
                String[] values = new String[100];
                values = line.split(",");

                for (String tempStr : values) {
                    tempStr = tempStr.trim();
                    row.add(tempStr);
                }
                list.add(row);

            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * Displays a string that acts as a divider in menus.
     */
    public static void displayDivider() 
    {
        System.out.println("||||||||||||||||||||||||||||||||||||||||");
    }
}
