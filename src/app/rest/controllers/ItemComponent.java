package app.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import app.entities.Item;
import app.entities.Property;
import app.repositories.ItemRepository;
import app.repositories.PropertyRepository;
import app.repositories.ServiceRepository;
import app.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemComponent {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public Item saveItem(ItemCreationDTO itemDTO) {
        // Find or throw an exception if the property does not exist
        Property property = propertyRepository.findById(itemDTO.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Create and set up the Item entity using itemDTO data
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getitem_Description());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());
        item.setCategory(itemDTO.getCategory());
        item.setProperty(property);  // Associate the Item with the Property

        // Save and return the new Item
        return itemRepository.save(item);
    }

	public List<Item> getAllItems() {
		return itemRepository.findAll();
		
	}

    public List<Item> findByProperty(Property property) {
        return itemRepository.findByProperty(property);
    }

    @Transactional
    public Item updateItem(Long id, ItemDTO itemDTO) {
        try {
            Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

            // Update basic fields from DTO
            existingItem.setName(itemDTO.getName());
            existingItem.setDescription(itemDTO.getItem_description());
            existingItem.setPrice(itemDTO.getPrice());
            existingItem.setQuantity(itemDTO.getQuantity());
            existingItem.setCategory(itemDTO.getCategory());

            // Handle property
            Property property = itemDTO.getPropertyId();
            if (property != null && property.getId() != null) {
                Property existingProperty = propertyRepository.findById(property.getId())
                    .orElseThrow(() -> new RuntimeException("Property not found with id: " + property.getId()));
                existingItem.setProperty(existingProperty);
            } else {
                throw new RuntimeException("Property is required");
            }

            return itemRepository.save(existingItem);
        } catch (Exception e) {
            System.err.println("Error updating item: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to update item: " + e.getMessage());
        }
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public void deleteItem(Long id) {
        try {
            // Find the item
            Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
            
            // First, delete associated service requests
            serviceRepository.deleteByInventoryItemId(id);
            
            // Then delete the item
            itemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete item: " + e.getMessage());
        }
    }
    

}


