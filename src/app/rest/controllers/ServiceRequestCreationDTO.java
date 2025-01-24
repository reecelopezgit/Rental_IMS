package app.rest.controllers;

import java.time.LocalDate;

import javax.persistence.Column;

public class ServiceRequestCreationDTO {



    private String name;

    private String serviceDescription;

    private double cost;

    private String status;

    private String serviceCategory;

    private Long propertyId;

    private Long inventoryItemId;
	private LocalDate requestDate; // Date when the request was made
    private LocalDate completionDate; // Date when the repair was completed
    private LocalDate scheduledDate;

    // No-argument constructor
    public ServiceRequestCreationDTO() {}

    // All-argument constructor
    public ServiceRequestCreationDTO( String name, String serviceDescription, double cost, String status, String serviceCategory, Long propertyId, Long inventoryItemId, LocalDate requestDate, LocalDate completionDate, LocalDate scheduledDate) {
        this.name = name;
        this.serviceDescription = serviceDescription;
        this.cost = cost;
        this.status = status;
        this.serviceCategory = serviceCategory;
        this.propertyId = propertyId;
        this.inventoryItemId = inventoryItemId;
        this.requestDate = requestDate;
        this.completionDate = completionDate;
        this.scheduledDate = scheduledDate;
    }

    // Getters and Setters

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

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
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

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Long inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    @Override
	public String toString() {
		return "ServiceRequestDTO [name=" + name + ", serviceDescription=" + serviceDescription + ", cost=" + cost
				+ ", status=" + status + ", serviceCategory=" + serviceCategory + ", propertyId=" + propertyId
				+ ", inventoryItemId=" + inventoryItemId + ", requestDate=" + requestDate + ", completionDate="
				+ completionDate + ", scheduledDate=" + scheduledDate + "]";
	}
}

