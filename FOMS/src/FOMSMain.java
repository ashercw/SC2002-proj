import Boundary.UIEntry;

/**
 * The FOMSMain class is the entry point for the Fastfood Ordering and Management 
 * System (FOMS). It starts up the first page of FOMS through calling the start() method
 * from the UIEntry class.
 * @author Saffron Lim, Reuben Farrel
 */

public class FOMSMain {

    /**
     * Calls the start() method from the UIEntry class to start up the first page of FOMS.
     * @param args Command line arguments passed to the program.
     * 
     */
    public static void main(String[] args) {
        UIEntry.UIstart();

    }

}
