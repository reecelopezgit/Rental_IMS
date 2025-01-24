package app.rest.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.entities.Property;

public class ItemDTO {

    private String name;
    @JsonProperty("item_description")
    private String item_description;
    private double price;
    private int quantity;
    private String category;
    private Long id;
    
    private Property propertyId;  // ID of the associated property

    
    public ItemDTO() {}

    // All-argument constructor
    public ItemDTO(Long id, String name, String item_description, double price, int quantity, String category, Property propertyId) {
        this.name = name;
        this.id = id;
        this.item_description = item_description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.propertyId = propertyId;
    }
    
    // Getters and Setters
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
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

    public Property getPropertyId() {
        return propertyId;
    }

    @Override
    public String toString() {
        return "ItemDTO [name=" + name + ", item_description=" + item_description + ", price=" + price + ", quantity="
                + quantity + ", category=" + category + ", id=" + id + ", propertyId=" + propertyId + "]";
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }
}
   
