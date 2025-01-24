package app.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.entities.Property;
import app.repositories.PropertyRepository;
import app.repositories.ServiceRepository;
import app.repositories.ItemRepository;
import app.repositories.ServiceRepository;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PropertyComponent {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceRepository serviceRequestRepository;

    // Retrieve all properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Retrieve a property by ID
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    // Save a new property using PropertyDTO
    public Property saveProperty(PropertyCreationDTO propertyCreationDTO) {
        Property property = new Property();
        property.setProperty_name(propertyCreationDTO.getProperty_Name());
        property.setAddress(propertyCreationDTO.getAddress());
        property.setType(propertyCreationDTO.getType());

        // Save and return the new property
        return propertyRepository.save(property);
    }

    // Update an existing property
    public Property updateProperty(Long id, Property updatedProperty) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new RuntimeException("Property not found"));
        property.setProperty_name(updatedProperty.getProperty_name());
        property.setAddress(updatedProperty.getAddress());
        property.setType(updatedProperty.getType());
        return propertyRepository.save(property);
    }

    @Transactional
    public void deleteProperty(Long id) {
        try {
            // Find the property
            Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found with id: " + id));
            
            // First, delete all associated service requests
            serviceRepository.deleteByPropertyId(id);
            
            // Then, delete all associated items
            itemRepository.deleteByPropertyId(id);
            
            // Finally, delete the property
            propertyRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete property: " + e.getMessage());
        }
    }
}
