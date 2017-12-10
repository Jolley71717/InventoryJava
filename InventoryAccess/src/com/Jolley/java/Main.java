package com.Jolley.java;

import com.oracle.javafx.jmx.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.read
        String jsonData = readUrl("http://localhost:8080/output/YYVAOe8Qr8F4MreoQVYWUaqK6AA.json");

        System.out.println(jsonData);
        Inventory lib = mapper.readValue(jsonData, Inventory.class);
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

    try {
        JSONObject json = new JSONObject(readUrl("..."));

        String title = (String) json.get("title");


    } catch (JSONException e) {
        e.printStackTrace();
    }
}


