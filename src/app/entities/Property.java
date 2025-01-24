package app.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Property 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
    private Long id;

	
	@Column
	@NotNull(message = "property name cannot be null")
    private String property_name;
	
	@Column
	@NotNull(message = "address cannot be null")
    private String address;
	
	@Column
    @Pattern(regexp = "^(apartment|condominium|house|lot)$", message = "Type must be 'apartment', 'condominium','house','lot'")
    private String type; 
	
	@JsonIgnore
    @OneToMany(mappedBy = "property", cascade = CascadeType.REMOVE)
    private List<Item> inventoryItems;
	@JsonIgnore
    @OneToMany(mappedBy = "property", cascade = CascadeType.REMOVE)
    private List<ServiceRequest> repairRequests;
	
	

	
	public List<Item> getInventoryItems() {
		return inventoryItems;
	}







	public void setInventoryItems(List<Item> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}







	public List<ServiceRequest> getRepairRequests() {
		return repairRequests;
	}







	public void setRepairRequests(List<ServiceRequest> repairRequests) {
		this.repairRequests = repairRequests;
	}







	public Long getId() {
		return id;
	}







	public void setId(Long id) {
		this.id = id;
	}







	public String getProperty_name() {
		return property_name;
	}







	public void setProperty_name(String property_name) {
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
		return "Property [id=" + id + ", property_name=" + property_name + ", address=" + address + ", type=" + type
				+ "]";
	}
	
	
	
	
}
