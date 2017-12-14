package com.jolley;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jolley.POJO.Item;
import com.jolley.POJO.ItemComparator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class PhantProcedures {

    private String photonInventoryJsonData;
    private String piInventoryJsonData;


    public void deleteRPiTrackerStream()  {

            String streamURL = "http://192.168.1.107:8080/streams/4Wegvpk190IlE1ErBAKOC2eYKX6/delete/yQlPV2novLIPvgveO3JRsxOrYgp";
        deleteTrackerStream(streamURL);
    }
    public void deletePhotonTrackerStream()  {

            String streamURL = "http://192.168.1.107:8080/streams/4l4DVeWZm8UlE1ErBAKOC2eYKX6/delete/yEB4glQX69IPvgveO3JRsxOrYgp";
        deleteTrackerStream(streamURL);
    }

    private void deleteTrackerStream(String streamUrl){
        try{
            URL url = new URL(streamUrl);
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

    public void getRaspberryPiInventoryTrackerStream(){
        //set up a while loop here so that this thing constantly goes
        String stringURL = "http://192.168.1.107:8080/output/4Wegvpk190IlE1ErBAKOC2eYKX6.json";

        try{
            piInventoryJsonData = readUrl(stringURL);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void getPhotonInventoryTrackerStream(){
        //set up a while loop here so that this thing constantly goes
        String stringURL = "http://192.168.1.107:8080/output/4l4DVeWZm8UlE1ErBAKOC2eYKX6.json";

        try{
            photonInventoryJsonData = readUrl(stringURL);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private String readUrl(String urlString) throws Exception {
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

    public List<Item> getPhotonItems(){
        return getItems(photonInventoryJsonData);
    }

    public List<Item> getRPiItems(){

        return getItems(piInventoryJsonData);
    }

    public List<Item> getItems(String jsonData){
        ObjectMapper mapper = new ObjectMapper();
        try{
            mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY,true);

            List<Item> items = mapper.readValue(jsonData ,new TypeReference<List<Item>>(){});
            Collections.sort(items, new ItemComparator());

            return items;

        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    //push updates to the listening phant


    public void inputPhant(Integer itemID, String description, Integer quantity){
        String phantInputURL = "http://192.168.1.107:8080/input/4Wegvpk190IlE1ErBAKOC2eYKX6?" +
                "private_key=q5jX32a7LeCl3736PGpwCwARXjQ";
        phantInputURL = phantInputURL + "&ItemID=" + itemID.toString() + "&Description="
                + description + "&Quantity=" + quantity.toString();
        String result = "";
        try{
            result = readUrl(phantInputURL);
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println(result);

    }

    public String getPhotonInventoryJsonData() {
        return photonInventoryJsonData;
    }

    public void setPhotonInventoryJsonData(String photonInventoryJsonData) {
        this.photonInventoryJsonData = photonInventoryJsonData;
    }

    public String getPiInventoryJsonData() {
        return piInventoryJsonData;
    }

    public void setPiInventoryJsonData(String piInventoryJsonData) {
        this.piInventoryJsonData = piInventoryJsonData;
    }


}
