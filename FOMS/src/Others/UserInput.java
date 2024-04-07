package Others;
import java.util.Scanner;

/** 
 * The UserInput class will provide functions that gets various user inputs (i.e., int, String, etc)
 * from the command line terminal.
 */

public class UserInput {

    static Scanner scInt = new Scanner(System.in);

    /**
     * Gets integer user input.
     * @return an integer
     */

    public static int userInputInt()
    {
        try {
            int userInput = scInt.nextInt();
            return userInput;
        } catch (Exception e) {
            System.out.println("Please enter an integer.");
            return userInputInt();
        }
    }

}
