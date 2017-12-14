package com.jolley;

import com.jolley.POJO.Item;
import com.jolley.databaseclass.SQLiteJDBCConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args)  {

        PhantProcedures phantProcedures = new PhantProcedures();
        //get the stream of information
        phantProcedures.getRaspberryPiInventoryTrackerStream();

        //If the inventory update is null, you shouldn't be printing stuff out

        if (phantProcedures.getPiInventoryJsonData() != ""){

            SQLiteJDBCConnection sqLConnection = new SQLiteJDBCConnection();
            try {

                sqLConnection.connect();

                //gets inventory items sent from the photon to the raspberry pi
                List<Item> items = phantProcedures.getRPiItems();
                for (Item f : items){
                    System.out.println(f.description + " at time " + f.getTimestamp() + " with quantity:" + f.quantity);
                    sqLConnection.updateQuantity(f.itemID,f.quantity);
                }

                // after successful insert, remove the information from the pi phant
                phantProcedures.deleteRPiTrackerStream();

                // Now we need to add the new items to the phant
                List<Item> newQuantities = sqLConnection.getItemQuantities();
                for(Item q : newQuantities){
                    phantProcedures.inputPhant(q.itemID,q.description,q.quantity);
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

        // Monitor the level of the trigger
        try{
            SQLiteJDBCConnection sqLiteJDBCConnection = new SQLiteJDBCConnection();

            List<Item> inventoryItems = sqLiteJDBCConnection.getAllItems();
            for(Item a : inventoryItems){
                //check to see if the quantity is at or below the trigger. If it is, run the nova commands
            }
        }catch (Exception ex){
            System.out.println(ex);
        }




        //set a wait/sleep so that it doesn't burn it out

        // delays the process so it doesn't run every second
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

}


