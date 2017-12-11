package com.jolley;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Inventory {
    @JsonProperty("myInventory")
    public List<Item> items;
}
