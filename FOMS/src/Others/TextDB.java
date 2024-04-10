package Others;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import Entity.User.*;

public class TextDB {
    public static final String SEPARATOR = "|";

    // an example of reading
    public static ArrayList readEmployee(String filename, Boolean isA) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(filename);
        ArrayList alr = new ArrayList();// to store Professors data

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
            
            // create Professor object from file data
            if(role.equals("S"))
            {
                String branch = star.nextToken().trim();
                String password = star.nextToken().trim();
                Staff userEmp = new Staff(name, userID, EmployeeType.S, gender, age, branch, password);
                // add to Users list
                alr.add(userEmp);
            }
            else if(role.equals("M"))
            {
                String branch = star.nextToken().trim();
                String password = star.nextToken().trim();
                Manager userEmp = new Manager(name, userID, EmployeeType.M, gender, age, branch, password);
                // add to Users list
                alr.add(userEmp);
            }
            else if(role.equals("A"))
            {
                String password = star.nextToken().trim();
                Admin userEmp = new Admin(name, userID, EmployeeType.S, gender, age, password);
                // add to Users list
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

    public static ArrayList readBranch(String filename, Boolean isA) throws IOException {
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

    // an example of saving
    public static void saveEmployee(String filename, List al) throws IOException {
        List alw = new ArrayList();// to store Professors data

        for (int i = 0; i < al.size(); i++) {
            User emp = (User) al.get(i);
            StringBuilder st = new StringBuilder();
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
        write(filename, alw);
    }

    /** Write fixed content to the given file. */
    public static void write(String fileName, List data) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }

    /** Read the contents of the given file. */
    public static List read(String fileName) throws IOException {
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
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
