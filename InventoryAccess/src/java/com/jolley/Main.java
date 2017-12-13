package com.jolley;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jolley.POJO.Item;
import com.jolley.POJO.ItemComparator;
import com.jolley.databaseclass.SQLiteJDBCConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args)  {

        //set up a while loop here so that this thing constantly goes
        String stringURL = "http://localhost:8080/output/YYVAOe8Qr8F4MreoQVYWUaqK6AA.json";
        String jsonData ="";

        try{
             jsonData = readUrl(stringURL);
        }catch (Exception e){
            System.out.println(e);
        }

        //If it's null, you shouldn't be printing stuff out
        if (jsonData != ""){
            SQLiteJDBCConnection sqLConnection = new SQLiteJDBCConnection();
            try {

                sqLConnection.connect();

                List<Item> items = getItems(jsonData);
                for (Item f : items){
                    System.out.println(f.description + " at time " + f.getTimestamp() + " with quantity:" + f.quantity);
                    updateQuantity(f.itemID,f.quantity,sqLConnection);

                    //get the connection afterwards
                    //This is where the sqlite piece takes place


                    //After successful insert, remove the information from phant
                    PhantProcedures phantProcedures = new PhantProcedures();
                    phantProcedures.deleteInventoryTrackerStream();


                    //If there was an update, delete from phant site 2 and then re-add the the new inventory and numbers
                    //----->Create second phant here
                    //both sending and receiving phants will only need itemID, description, and quantity (plus timestamp)

                    //Create another loop that monitors the triggers and sends out a loop based on that.

                    //set a wait/sleep so that it doesn't burn it out
                }

                //close the connection
                sqLConnection.closeConnection();

            } catch (Exception ex){
                //close the connection if an exception occured
                if(sqLConnection != null){
                    sqLConnection.closeConnection();
                }
            } finally {
                // Make certain that the connection is closed
                if(sqLConnection != null){
                    sqLConnection.closeConnection();
                }
            }



        }
    }

    private static List<Item> getItems(String jsonInfo){
        ObjectMapper mapper = new ObjectMapper();
        try{
                mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);

                List<Item> items = mapper.readValue(jsonInfo,new TypeReference<List<Item>>(){});
                Collections.sort(items, new ItemComparator());

                return items;

        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    private static void updateQuantity(Integer inventoryID, Integer quantity, SQLiteJDBCConnection conn){
        //update the sqlite database item quantity
        conn.update(inventoryID, quantity);
    }


}


