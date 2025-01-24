package app.rest.controllers;

import java.time.LocalDate;

import javax.persistence.Column;

import app.entities.Item;
import app.entities.Property;

public class ServiceRequestDTO {


	private Long id;
    private String name;

    private String service_description;

    private double cost;

    private String status;

    private String service_category;

    private Property property;

    private Item inventoryItem;
	private LocalDate requestDate; // Date when the request was made
    private LocalDate completionDate; // Date when the repair was completed
    private LocalDate scheduledDate;

    // No-argument constructor
    public ServiceRequestDTO() {}

    // All-argument constructor
    public ServiceRequestDTO(Long id, String name, String service_description, double cost, String status, String service_category, LocalDate requestDate, LocalDate completionDate, LocalDate scheduledDate, Property property, Item inventoryItem) {
        this.name = name;
        this.id = id;
        this.service_description = service_description;
        this.cost = cost;
        this.status = status;
        this.service_category = service_category;
        this.property = property;
        this.inventoryItem = inventoryItem;
        this.requestDate = requestDate;
        this.completionDate = completionDate;
        this.scheduledDate = scheduledDate;
    }

    // Getters and Setters
    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    
    public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public LocalDate getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService_description() {
        return service_description;
    }

    public void setService_description(String service_description) {
        this.service_description = service_description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getService_category() {
        return service_category;
    }

    public void setService_category(String service_category) {
        this.service_category = service_category;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Item getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(Item inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    @Override
	public String toString() {
		return "ServiceRequestDTO [id=" + id + "name=" + name + ", service_description=" + service_description + ", cost=" + cost
				+ ", status=" + status + ", service_category=" + service_category + ", property=" + property
				+ ", inventoryItem=" + inventoryItem + ", requestDate=" + requestDate + ", completionDate="
				+ completionDate + ", scheduledDate=" + scheduledDate + "]";
	}
}

