package com.jolley.databaseclass;

import com.jolley.POJO.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
     *
     * @author Luke Dutton
     */
    public class SQLiteJDBCConnection {

        public  void SQLiteJDBCConnection(){}

        /**
         * Connect to database on computer
         */
        private static Connection conn = null;

        public  void connect() {

            try {
                // db parameters, db should go somewhere accessable on the rpi
                String url = "jdbc:sqlite:/Users/luke/Documents/iot/InventoryJava/InventoryAccess/src/resources/database/inventory.db";

                // create a connection to the database
                conn = DriverManager.getConnection(url);

                System.out.println("Connection to SQLite has been established.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public  void closeConnection(){
            try{
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        public List<Item> getItemQuantities(){
            List<Item> photonItems = selectMin();


            return  photonItems;
        }

        public List<Item> getAllItems(){
            return selectAll();
        }

        private List<Item> selectMin(){
            List<Item> listOfItems = new ArrayList<>();
            String sql = "SELECT ItemID, Description, Quantity FROM INVENTORY";

            try (Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){

                // loop through the result set
                while (rs.next()) {
                    Item currentItem = new Item(rs.getInt("ItemID"),rs.getString("Description")
                            ,rs.getInt("Quantity"));
                    listOfItems.add(currentItem);

                }

                return listOfItems;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        private List<Item> selectAll(){
            List<Item> listOfItems = new ArrayList<>();
            String sql = "SELECT ItemID, Description, Quantity,TriggerLvl, Triggered FROM INVENTORY";

            try (Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sql)){

                // loop through the result set
                while (rs.next()) {
                    Item currentItem = new Item(rs.getInt("ItemID"),rs.getString("Description")
                            ,rs.getInt("Quantity"),rs.getInt("TriggerLvl"), rs.getInt("Triggered"));
                    listOfItems.add(currentItem);

                }

                return listOfItems;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

        private void update(int ItemID, int quantity) {
            String sql = "UPDATE inventory SET Quantity = ?  "
                    + "WHERE ItemID = ?";

            try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // set the corresponding param
                pstmt.setInt(1, quantity);
                pstmt.setInt(2, ItemID);
                // update
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void updateQuantity(Integer inventoryID, Integer quantity){
            //update the sqlite database item quantity
            update(inventoryID, quantity);
        }

    }

