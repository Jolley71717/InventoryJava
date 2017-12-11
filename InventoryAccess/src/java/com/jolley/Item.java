package com.jolley;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;

public class Item{
//    public class Item {

    @JsonProperty("Description")public  String description;
    @JsonProperty("ItemID") public Integer itemID;
    @JsonProperty("Quantity") public Integer quantity;
    @JsonProperty("timestamp") public Timestamp timestamp;

//    public int compareTo(Item o) {
//        Timestamp compareDate = ((Item) o).getTimestamp();
//
//        //descending order
//        return compareDate - this.timestamp;
//    }


    public Item(){}

    public Item(String description, Integer itemID, Integer quantity, Timestamp timestamp){
    this.description = description;
    this.itemID = itemID;
    this.quantity = quantity;
    this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

//
//    public int compareTo(Item o1, Item o2) {
//        long t1 = o1.getTimestamp().getTime();
//        long t2 = o2.getTimestamp().getTime();
//        return t1 == t2 ? 0 : t1 < t2 ? -1 : 1;
//    }
}
