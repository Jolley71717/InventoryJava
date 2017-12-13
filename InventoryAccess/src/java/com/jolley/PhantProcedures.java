package com.jolley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

public class PhantProcedures {

    public void deleteInventoryTrackerStream()  {
        try{
            URL url = new URL("http://localhost:8080/streams/YYVAOe8Qr8F4MreoQVYWUaqK6AA/delete/AnGY98Bl6BC9dbv4y7xpflGnzyy");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println(content);
            in.close();
            con.disconnect();

        }catch (Exception ex){
            System.out.println(ex);
        }

    }
}
