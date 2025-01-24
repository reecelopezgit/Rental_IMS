package app.rest.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyCreationDTO {
	@JsonProperty("property_name")
    private String property_name;
    private String address;
    private String type;

    public PropertyCreationDTO(String property_name, String address, String type) {
    	this.property_name = property_name;
        this.address = address;
        this.type = type;
    }

    public PropertyCreationDTO() {}
	// Getters and Setters
    public String getProperty_Name() {
        return property_name;
    }

    public void setProperty_Name(String property_name) {
        this.property_name = property_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "PropertyCreationDTO{" +
               "name='" + property_name + '\'' +
               ", address='" + address + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}
