package app.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
    private Long id;

	
	@Column
	@NotNull(message = "item name cannot be null")
    private String name;
	
	@Column
	@NotNull(message = "item description cannot be null")
    private String item_description;
	
	@Column
	@NotNull(message = "item name cannot be null")
    private double price;
	
	@Column
	@NotNull(message = "item quantity cannot be null")
    private Integer quantity;
	
	@Column
    @Pattern(regexp = "^(Electrical|Plumbing|HVAC|Hardware|Construction|Paint|Cleaning|Miscellaneous)$", message = "Type must be Electrical, Plumbing, HVAC, Hardware, Construction, Paint, Cleaning, Miscellaneous")
    private String category;
	@JsonIgnore
	@ManyToOne // Many InventoryItems can belong to one Property
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

	@OneToMany(mappedBy = "inventoryItem", cascade = CascadeType.REMOVE)
	private List<ServiceRequest> serviceRequests;

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

	public String getDescription() {
		return item_description;
	}

	public void setDescription(String description) {
		this.item_description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double d) {
		this.price = d;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + item_description + ", price=" + price + ", quantity="
				+ quantity + ", category=" + category + ", property=" + property + "]";
	}

	
	
	

	
	
}
