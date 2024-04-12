package Others;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

import Entity.Branch;
import Entity.User.*;

/**
 * This class has functions to implement the saving of entities into their respective repositories
 * for the purpose of data persistence. The repositories come in the form of text files which can be read 
 * from and written to using the readXX and saveXX functions, where XX represents the entity.
 * 
 * The code fpr this class was adapted from the code provided on NTULearn.
 * @author Saffron Lim
 */

public class TextDB {
    public static final String SEPARATOR = "|";

    /**
     * This function is meant to create various entity objects (i.e., Staff objects, Admin objects, 
     * and Manager objects) from a txt file. It first reads String from a given text file by calling 
     * the read() function. It then creates the objects based on the read data and returns a list of the
     * different objects.
     * @param FILENAME is the path of the txt file to read from
     * @return ArrayList of Entity objects (i.e., Staff objects, Admin objects, and Manager objects)
     * @throws IOException
     * 
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
            String gender = star.nextToken().trim();
            String age = star.nextToken().trim();
            
            // Create respective entity
            if(role.equals("S"))
            {
                String branch = star.nextToken().trim();
                String password = star.nextToken().trim();
                Staff userEmp = new Staff(name, userID, EmployeeType.S, gender, age, branch, password);
                // add to list
                alr.add(userEmp);
            }
            else if(role.equals("M"))
            {
                String branch = star.nextToken().trim();
                String password = star.nextToken().trim();
                Manager userEmp = new Manager(name, userID, EmployeeType.M, gender, age, branch, password);
                // add to list
                alr.add(userEmp);
            }
            else if(role.equals("A"))
            {
                String password = star.nextToken().trim();
                Admin userEmp = new Admin(name, userID, EmployeeType.S, gender, age, password);
                // add to list
                alr.add(userEmp);
            }
            
        }
        return alr;
    }

    public static ArrayList readMenu(String filename, Boolean isA) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(filename);
        ArrayList alr = new ArrayList();// to store Professors data

        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
                                                                       // using delimiter ","

            String name = star.nextToken().trim(); // first token
            String email = star.nextToken().trim(); // second token
            int contact = Integer.parseInt(star.nextToken().trim()); // third token
            // create Professor object from file data
            //Professor prof = new Professor(name, email, contact);
            
            // add to Professors list
            //alr.add(prof);
        }
        return alr;
    }

    public static ArrayList readBranch(String filename, Boolean createObj) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(filename);
        ArrayList alr = new ArrayList();// to store Professors data
        
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
                                                                       // using delimiter ","

            String name = star.nextToken().trim(); // first token
            if(createObj == false) //only get the branch names
            {
                alr.add(name);
            }
            else
            {
                String location = star.nextToken().trim(); // second token
                int quota = Integer.parseInt(star.nextToken().trim()); // third token
                // create Branch object from file data
                Branch branchObj = new Branch(name, location, quota);
            
                // add to Branch list
                alr.add(branchObj);
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


    public static void saveBranch(String FILENAME, List al) throws IOException {
        List alw = new ArrayList();// to store Professors data

        for (int i = 0; i < al.size(); i++) {
            //get object
            Branch branchObj = (Branch) al.get(i);
            StringBuilder st = new StringBuilder();
            //get attributes
            st.append(branchObj.getBranchName().trim());
            st.append(SEPARATOR);
            st.append(branchObj.getLocation().trim());
            st.append(SEPARATOR);
            st.append(branchObj.getQuota());
            
            alw.add(st.toString());

        }
        write(FILENAME, alw);
    }

    
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
