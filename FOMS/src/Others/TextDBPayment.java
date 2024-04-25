package Others;

import java.util.StringTokenizer;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

import Entity.Branch;
import Entity.Order.Payment;

@SuppressWarnings({ "rawtypes", "unchecked" })

public class TextDBPayment extends TextDB {

    /**
     * This function is meant to create various Branch objects or a list of branch
     * names from a txt file.
     * It first reads String from a given text file by calling the read() function.
     * If createObj = true, it then creates the objects based on the read data and
     * returns a list of the
     * different objects.
     * If createObj = false, it returns an ArrayList of branch names in String.
     * 
     * @param filename  is the path of the txt file to read from.
     * @param createObj is a boolean variable that determines the operation of the
     *                  function.
     * @return an ArrayList of Branch objects or an ArrayList of Strings (branch
     *         names).
     * @throws IOException
     * 
     */
    public static ArrayList readPayment(String filename, Boolean createObj) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(filename);
        ArrayList alr = new ArrayList();// to store Professors data

        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
                                                                       // using delimiter ","

            String name = star.nextToken().trim(); // first token
            if (createObj == false) // only get the branch names
            {
                alr.add(name);
            } else {
                String location = star.nextToken().trim(); // second token
                int quota = Integer.parseInt(star.nextToken().trim()); // third token
                // create Branch object from file data
                Payment paymentObj = new Payment(name, location, quota);

                // add to Branch list
                alr.add(paymentObj);
            }

        }
        return alr;
    }

    /**
     * This function saves the data of Branch objects in a given txt file.
     * 
     * @param FILENAME is the String directory of the file to write to.
     * @param al       is a List of Branch objects whose content will be saved in
     *                 the txt file.
     * @throws IOException
     * 
     */
    public static void savePayment(String FILENAME, List al) throws IOException {
        List alw = new ArrayList();// to store Professors data

        for (int i = 0; i < al.size(); i++) {
            // get object
            Payment paymentObj = (Payment) al.get(i);
            StringBuilder st = new StringBuilder();
            // get attributes
            st.append(paymentObj.getBranchName().trim());
            st.append(SEPARATOR);
            st.append(paymentObj.getLocation().trim());
            st.append(SEPARATOR);
            st.append(paymentObj.getQuota());

            alw.add(st.toString());

        }
        write(FILENAME, alw);
    }
}
