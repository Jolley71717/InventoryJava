package com.jolley.databaseclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    /**
     *
     * @author Luke Dutton
     */
    public class SQLiteJDBCConnection {

        public static void SQLiteJDBCConnection(){}

        /**
         * Connect to database on computer
         */
        private static Connection conn = null;

        public static void connect() {

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

        public static void closeConnection(){
            try{
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        public void update(int ItemID, int quantity) {
            String sql = "UPDATE inventory SET Quantity = ?  "
                    + "WHERE ItemID = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                // set the corresponding param
                pstmt.setInt(1, quantity);
                pstmt.setInt(2, ItemID);
                // update
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

