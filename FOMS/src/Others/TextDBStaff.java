package Others;

import java.util.StringTokenizer;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

import Entity.User.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TextDBStaff extends TextDB {
    public static final String SEPARATOR = "|";

    /**
     * This class has functions to implement the saving of entities into their
     * respective repositories
     * for the purpose of data persistence. The repositories come in the form of
     * text files which can be read
     * from and written to using the readXX and saveXX functions, where XX
     * represents the entity.
     * 
     * The code for this class was adapted from the code provided on NTULearn.
     * 
     * @author Saffron Lim
     */
    public static ArrayList readEmployee(String FILENAME) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(FILENAME);
        ArrayList alr = new ArrayList();// to store Employee data

        for (int i = 0; i < stringArray.size(); i++) {

            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
                                                                       // using delimiter ","

            String name = star.nextToken().trim(); // first token
            String userID = star.nextToken().trim(); // second token
            String role = star.nextToken().trim();
            String gender = star.nextToken();
            String age = star.nextToken();

            // Create respective entity
            if (role.equals("S")) {
                String branch = star.nextToken().trim();
                String password = star.nextToken().trim();
                Staff userEmp = new Staff(name, userID, EmployeeType.S, gender, age, branch, password);
                // add to list
                alr.add(userEmp);
            } else if (role.equals("M")) {
                String branch = star.nextToken().trim();
                String password = star.nextToken().trim();
                Manager userEmp = new Manager(name, userID, EmployeeType.M, gender, age, branch, password);
                // add to list
                alr.add(userEmp);
            } else if (role.equals("A")) {
                String password = star.nextToken().trim();
                Admin userEmp = new Admin(name, userID, EmployeeType.A, gender, age, password);
                // add to list
                alr.add(userEmp);
            }

        }
        return alr;
    }

     /**
     * This function saves the data from various Entity objects (i.e., Staff objects, 
     * Admin objects, and Manager objects) and saves them in a given txt file.
     * @param FILENAME is the String directory of the file to write to.
     * @param al is a List of various Entity objects (i.e., Staff objects, 
     * Admin objects, and Manager objects) whose content will be saved in the txt file.
     * @throws IOException
     * 
     */
    public static void saveEmployee(String FILENAME, List al) throws IOException {
        List alw = new ArrayList();// to store Professors data

        for (int i = 0; i < al.size(); i++) {
            //get object
            User emp = (User) al.get(i);
            StringBuilder st = new StringBuilder();
            //get attributes
            st.append(emp.getEmployeeName().trim());
            st.append(SEPARATOR);
            st.append(emp.getLoginID().trim());
            st.append(SEPARATOR);
            st.append(emp.getEmployeeType());
            st.append(SEPARATOR);
            st.append(emp.getGender());
            st.append(SEPARATOR);
            st.append(emp.getAge());
            st.append(SEPARATOR);
            if(emp.getEmployeeType() == EmployeeType.A)
            {
                Admin ad = (Admin) al.get(i);
                st.append(ad.getPassword());
                alw.add(st.toString());
            }
            else if(emp.getEmployeeType() == EmployeeType.S)
            {
                Staff staff = (Staff) al.get(i);
                st.append(staff.getBranch());
                st.append(SEPARATOR);
                st.append(staff.getPassword());
                alw.add(st.toString());
            }
            else if(emp.getEmployeeType() == EmployeeType.M)
            {
                Manager man = (Manager) al.get(i);
                st.append(man.getBranch());
                st.append(SEPARATOR);
                st.append(man.getPassword());
                alw.add(st.toString());
            }

        }
        write(FILENAME, alw);
    }

}
