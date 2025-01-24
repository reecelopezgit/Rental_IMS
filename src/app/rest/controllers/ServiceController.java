package app.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app.entities.ServiceRequest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/api/services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceController {

    @Inject
    private ServiceComponent serviceRequestComponent;  // Inject the component handling business logic

    private static final Logger logger = Logger.getLogger(ServiceController.class.getName());

    
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServiceRequestDTO> getAllServiceRequests() {
    List<ServiceRequest> serviceRequests = serviceRequestComponent.getAllServiceRequests();
    
    List<ServiceRequestDTO> serviceRequestDTOs = serviceRequests.stream()
        .map(request -> new ServiceRequestDTO(
            request.getId(),
            request.getName(),
            request.getService_description(),
            request.getCost(),
            request.getStatus(),
            request.getService_category(),
            request.getRequestDate(),
            request.getScheduledDate(),
            request.getCompletionDate(),
            request.getProperty(),
            request.getInventoryItem()
        ))
        .collect(Collectors.toList());
    
    return serviceRequestDTOs;
}
    
    
    // Endpoint to create a new service request
    @POST
    @Path("/new")
    public Response createServiceRequest(ServiceRequestCreationDTO serviceRequestCreationDTO) {
        try {
            logger.info("Received serviceRequestDTO: " + serviceRequestCreationDTO);
            
            // Process the DTO to create and save a new ServiceRequest
            ServiceRequest savedServiceRequest = serviceRequestComponent.saveServiceRequest(serviceRequestCreationDTO);
            
            return Response.status(Response.Status.CREATED)
                           .entity(savedServiceRequest) // Return the saved ServiceRequest object as JSON
                           .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error processing service request: " + e.getMessage())
                           .build();
        }
    }
    
    	
    @PUT
    @Path("/update/{id}")
    public Response updateServiceRequest(@PathParam("id") Long id, ServiceRequestDTO serviceRequestDTO) {
        try {
            ServiceRequest updatedRequest = serviceRequestComponent.updateServiceRequest(id, serviceRequestDTO);
            return Response.ok(updatedRequest).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating service request: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<ServiceRequest> getServiceRequestById(@PathParam("id") Long id) {
        return serviceRequestComponent.getServiceRequestById(id);
    }

    @DELETE
    @Path("/delete/{id}")
public Response deleteServiceRequest(@PathParam("id") Long id) {
    try {
        serviceRequestComponent.deleteServiceRequest(id);
        return Response.ok().build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error deleting service request: " + e.getMessage())
                .build();
    }
}
}

