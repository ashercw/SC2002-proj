package Others;

import java.util.StringTokenizer;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

import Entity.Food.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TextDBFood extends TextDB{

    /**
     * This function reads the food items from a given .txt file and returns them as an ArrayList of objects.
     * @param FILENAME String representing the filepath of the .txt file to read from.
     * @return an ArrayList of objects.
     * @throws IOException
     * 
     */
    public static ArrayList readFoodList(String FILENAME) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) read(FILENAME);
        ArrayList alr = new ArrayList();// to store Professors data

        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
                                                                       // using delimiter ","

            String name = star.nextToken().trim(); // first token
            double price = Double.parseDouble(star.nextToken().trim()); // second token
            String branch = star.nextToken().trim(); // third token
            String type = star.nextToken().trim();
            ItemType itemType = ItemType.BURGER; //temp

            if(type.equals("side"))  itemType = ItemType.SIDE;
            else if(type.equals("drink"))  itemType = ItemType.DRINK;
            else if(type.equals("set meal"))  itemType = ItemType.SETMEAL;

            //create FoodItem object from file data
            if(star.hasMoreTokens())
            {
                String desc = star.nextToken().trim();
                FoodItem food = new FoodItem(name, price, branch, itemType, desc);
                // add to FoodItem list
                alr.add(food);
            }
            else
            {
                FoodItem food = new FoodItem(name, price, branch, itemType);
                // add to FoodItem list
                alr.add(food);
            }
            
        
        }
        return alr;
    }


    /**
     * This function saves the data of FoodItem objects in a given txt file.
     * @param FILENAME is the String directory of the file to write to.
     * @param al is a List of FoodItem objects whose content will be saved in the txt file.
     * @throws IOException
     * 
     */
    public static void saveFood(String FILENAME, List al) throws IOException {
        List alw = new ArrayList();// to store Professors data

        for (int i = 0; i < al.size(); i++) {
            //get object
            FoodItem foodItemObj = (FoodItem) al.get(i);
            StringBuilder st = new StringBuilder();
            //get attributes
            st.append(foodItemObj.getFoodItemName().trim());
            st.append(SEPARATOR);
            st.append(foodItemObj.getFoodItemPrice());
            st.append(SEPARATOR);
            st.append(foodItemObj.getFoodItemBranch().trim());
            st.append(SEPARATOR);
            st.append(foodItemObj.getFoodItemType());
            st.append(SEPARATOR);
            st.append(foodItemObj.getFoodItemDesc());
            
            alw.add(st.toString());

        }
        write(FILENAME, alw);
    }

}
