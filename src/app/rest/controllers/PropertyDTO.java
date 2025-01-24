package app.rest.controllers;
public class PropertyDTO {

    private String property_name;
    private String address;
    private String type;
    private Long id;

    public PropertyDTO(Long id,String property_name, String address, String type) {
        this.id = id;
    	this.property_name = property_name;
        this.address = address;
        this.type = type;
    }


    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
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
}
