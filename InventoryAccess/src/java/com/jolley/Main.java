package com.jolley;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jolley.databaseclass.SQLiteJDBCConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args)  {
        String stringURL = "http://localhost:8080/output/YYVAOe8Qr8F4MreoQVYWUaqK6AA.json";
        String jsonData ="";

        try{
             jsonData = readUrl(stringURL);
        }catch (Exception e){
            System.out.println(e);
        }

        //If it's null, you shouldn't be printing stuff out
        if (jsonData != ""){
            List<Item> items = getItems(jsonData);
            for (Item f : items){
                System.out.println(f.description + " at time " + f.getTimestamp());

                //get the connection afterwards
                SQLiteJDBCConnection.connect();

                //This is where the sqlite piece takes place


                //After successful insert, remove the information from phant
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


}


