package Controller.Account.Password;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordMasker implements Runnable{
    public PasswordMasker()
    {}
    public PasswordMasker(String p) {
        System.out.println(p);
    }
    private boolean stop;

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
    public void stopMasking() {
        this.stop = false;
    }

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
