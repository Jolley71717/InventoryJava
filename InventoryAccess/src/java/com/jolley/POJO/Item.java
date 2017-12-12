package com.jolley.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;

public class Item{

    //only used when reading from the json
    @JsonProperty("timestamp") public Timestamp timestamp;

    //used when reading from the database
    private Integer triggerLvl = null;
    private Integer triggered = null;

    //used both when reading from the database and when reading from the json
    @JsonProperty("Description")public  String description;
    @JsonProperty("ItemID") public Integer itemID;
    @JsonProperty("Quantity") public Integer quantity;

    public Item(){}

    public Item(String description, Integer itemID, Integer quantity, Integer triggerLvl, Integer triggered){
        this.description = description;
        this.itemID = itemID;
        this.quantity = quantity;
        this.triggered = triggered;
        this.triggerLvl = triggerLvl;
    }

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


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

}
