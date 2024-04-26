package Controller.Account.Password;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class designed to obscure password input on the console with asterisks (*)
 * to enhance security during user input.
 */


public class PasswordMasker implements Runnable{
     /**
     * Default constructor for PasswordMasker.
     */
    public PasswordMasker()
    {}
    /**
     * Overloaded constructor that prints a message.
     * @param p The message to print.
     */
    public PasswordMasker(String p) {
        System.out.println(p);
    }
    private boolean stop;

    /**
     * The method that runs in a separate thread to mask the password input.
     * It prints an asterisk for each keystroke.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        stop = true;
        while (stop) {
            System.out.print("\010*");
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
    /**
     * Stops the masking process by terminating the loop in the {@link #run()} method.
     */
    public void stopMasking() {
        this.stop = false;
    }

    /**
     * Static method to read a password from the console while masking it.
     * It starts a thread to mask the input and reads the password input from the user.
     * 
     * @return The password as entered by the user.
     */
    public static String hideString() 
    {
        PasswordMasker et = new PasswordMasker();
        Thread mask = new Thread(et);
        mask.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            password = in.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // stop masking
        et.stopMasking();
        // return the password entered by the user
        return password;
    }


    
}
