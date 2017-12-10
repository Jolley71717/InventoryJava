package com.Jolley.java;

import java.sql.Timestamp;

public class Item {

    private  String description;
    private Integer itemID;
    private Integer quantity;
    private Timestamp timestamp;


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





}
