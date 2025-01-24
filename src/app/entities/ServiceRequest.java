	package app.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class ServiceRequest 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
    private Long id;

	
	@Column
	@NotNull(message = "name cannot be null")
    private String name;
	
	@Column
	@NotNull(message = "service description cannot be null")
    private String service_description;
	
	@Column
	@NotNull(message = "cost cannot be null")
    private double cost;
	
	@Column
	@NotNull
	@Pattern(regexp = "^(pending|in progress|completed|cancelled)$", message = "Status must be pending, in progress, completed, cancelled")
	private String status;
	
	@Column
    @Pattern(regexp = "^(repair|maintenance)$", message = "Type must be repair, maintenance")
    private String service_category;
	
    @NotNull
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
    
    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "inventory_item_id", nullable = false)
    private Item inventoryItem;

	public Item getInventoryItem() {
		return inventoryItem;
	}
	public void setInventoryItem(Item inventoryItem) {
		this.inventoryItem = inventoryItem;
	}
	@Column
	private LocalDate requestDate; // Date when the request was made
	@Column
    private LocalDate completionDate; // Date when the repair was completed
	@Column
    private LocalDate scheduledDate; // Date when the repair is scheduled
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
	@Override
	public String toString() {
		return "ServiceRequest [id=" + id + ", name=" + name + ", service_description=" + service_description
				+ ", cost=" + cost + ", status=" + status + ", service_category=" + service_category + ", property="
				+ property + ", inventoryItem=" + inventoryItem + ", requestDate=" + requestDate + ", completionDate="
				+ completionDate + ", scheduledDate=" + scheduledDate + "]";
	}


	
	
	

	
	
}
