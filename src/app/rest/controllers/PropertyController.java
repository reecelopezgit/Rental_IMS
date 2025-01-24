package app.rest.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import app.entities.Property;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Component
@Path("/api/properties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyController {

    @Inject
    private PropertyComponent propertyComponent;

    private static final Logger logger = Logger.getLogger(ItemController.class.getName());

    
    // Endpoint to get all properties
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PropertyDTO> getAllProperties() {
    	List<Property> properties = propertyComponent.getAllProperties();
        List<PropertyDTO> propertyDTOs = properties.stream()
                .map(property -> new PropertyDTO(
                		property.getId(),
                        property.getProperty_name(),
                        property.getAddress(),
                        property.getType()
                ))
                .collect(Collectors.toList());
        return propertyDTOs;
    }

    // Endpoint to get a property by ID
    @GET
    @Path("/{id}")
    public Optional<Property> getPropertyById(@PathParam("id") Long id) {
        return propertyComponent.getPropertyById(id);
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteProperty(@PathParam("id") Long id) {
        try {
            propertyComponent.deleteProperty(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error deleting property: " + e.getMessage())
                    .build();}
        }

    // Endpoint to add a new property
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProperty(PropertyCreationDTO propertyCreationDTO) {
        try {
        	logger.info("Received itemDTO: " + propertyCreationDTO);
            Property savedProperty = propertyComponent.saveProperty(propertyCreationDTO);
            return Response.status(Response.Status.CREATED)
                           .entity(savedProperty)
                           .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error adding property: " + e.getMessage())
                           .build();
        }
    }

    // Endpoint to update an existing property
    @PUT
    @Path("/update/{id}")
    public Response updateProperty(@PathParam("id") Long id, Property updatedProperty) {
        try {
            Property savedProperty = propertyComponent.updateProperty(id, updatedProperty);
            return Response.ok(savedProperty).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error updating property: " + e.getMessage())
                           .build();
        }
    }
}
