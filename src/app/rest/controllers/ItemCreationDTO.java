package app.rest.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemCreationDTO {

    private String name;
    @JsonProperty("item_description")
    private String item_description;
    private double price;
    private int quantity;
    private String category;
    
    private Long propertyId;  // ID of the associated property

    
    public ItemCreationDTO() {}

    // All-argument constructor
    public ItemCreationDTO(String name, String item_description, double price, int quantity, String category, Long propertyId) {
        this.name = name;
        this.item_description = item_description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.propertyId = propertyId;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getitem_Description() {
        return item_description;
    }

    public void setDescription(String description) {
        this.item_description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
   
