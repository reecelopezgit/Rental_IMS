package app.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import app.entities.Item;
import app.entities.Property;
import app.entities.ServiceRequest;
import app.repositories.ItemRepository;
import app.repositories.PropertyRepository;
import app.repositories.ServiceRepository;



import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ServiceComponent {

    @Autowired
    private ServiceRepository serviceRequestRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ServiceRequest saveServiceRequest(ServiceRequestCreationDTO serviceRequestCreationDTO) {
        // Find the Property entity by ID, or throw an exception if it doesn't exist
        Property property = propertyRepository.findById(serviceRequestCreationDTO.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Find the Item entity by ID, or throw an exception if it doesn't exist
        Item inventoryItem = itemRepository.findById(serviceRequestCreationDTO.getInventoryItemId())
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        // Create and set up the ServiceRequest entity using data from serviceRequestDTO
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setName(serviceRequestCreationDTO.getName());
        serviceRequest.setService_description(serviceRequestCreationDTO.getServiceDescription());
        serviceRequest.setCost(serviceRequestCreationDTO.getCost());
        serviceRequest.setStatus(serviceRequestCreationDTO.getStatus());
        serviceRequest.setService_category(serviceRequestCreationDTO.getServiceCategory());
        serviceRequest.setProperty(property);         // Associate the ServiceRequest with the Property
        serviceRequest.setInventoryItem(inventoryItem); // Associate with the Item
        serviceRequest.setRequestDate(serviceRequestCreationDTO.getRequestDate());
        serviceRequest.setCompletionDate(serviceRequestCreationDTO.getCompletionDate());
        serviceRequest.setScheduledDate(serviceRequestCreationDTO.getScheduledDate());
        // Save and return the new ServiceRequest
        return serviceRequestRepository.save(serviceRequest);
    }

    
    public ServiceRequest updateServiceRequest(Long id, ServiceRequestDTO serviceRequestDTO) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Service request not found"));
        
        serviceRequest.setName(serviceRequestDTO.getName());
        serviceRequest.setService_description(serviceRequestDTO.getService_description());
        serviceRequest.setCost(serviceRequestDTO.getCost());
        serviceRequest.setStatus(serviceRequestDTO.getStatus());
        serviceRequest.setService_category(serviceRequestDTO.getService_category());
        serviceRequest.setRequestDate(serviceRequestDTO.getRequestDate());
        serviceRequest.setScheduledDate(serviceRequestDTO.getScheduledDate());
        serviceRequest.setCompletionDate(serviceRequestDTO.getCompletionDate());
        
        // Only update these if they're provided in the DTO
        if (serviceRequestDTO.getProperty() != null) {
            Property property = propertyRepository.findById(serviceRequestDTO.getProperty().getId())
                .orElseThrow(() -> new RuntimeException("Property not found"));
            serviceRequest.setProperty(property);
        }
        
        if (serviceRequestDTO.getInventoryItem() != null) {
            Item item = itemRepository.findById(serviceRequestDTO.getInventoryItem().getId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
            serviceRequest.setInventoryItem(item);
        }
        
        return serviceRequestRepository.save(serviceRequest);
    }
    
	public List<ServiceRequest> getAllServiceRequests() {
		return serviceRequestRepository.findAll();
	}

    public Optional<ServiceRequest> getServiceRequestById(Long id) {
        return serviceRequestRepository.findById(id);
    }

    @Transactional
    public void deleteServiceRequest(Long id) {
        ServiceRequest serviceRequest = serviceRequestRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Service request not found with id: " + id));
        
        serviceRequestRepository.delete(serviceRequest);
}
}