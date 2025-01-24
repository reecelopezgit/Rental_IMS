package app.rest.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import app.entities.Item;
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
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component

@Path("/api/items")
public class ItemController {

    @Inject
    private ItemComponent itemComponent;

    @Inject
    private PropertyComponent propertyComponent;

    private static final Logger logger = Logger.getLogger(ItemController.class.getName());

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> getAllItems() {
    	List<Item> items = itemComponent.getAllItems();
        List<ItemDTO> itemDTOs = items.stream()
                .map(item -> new ItemDTO(
                		item.getId(),
                        item.getName(),
                        item.getDescription(),
                        item.getPrice(),
                        item.getQuantity(),
                        item.getCategory(),
                        item.getProperty()
                      
                ))
                .collect(Collectors.toList());
        return itemDTOs;
    }
    
    
    
    // Endpoint to create a new item
    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(ItemCreationDTO itemDTO) {
        try {
        	logger.info("Received itemDTO: " + itemDTO);
            // Process the DTO to create and save a new Item
            Item savedItem = itemComponent.saveItem(itemDTO);
            return Response.status(Response.Status.CREATED)
                           .entity(savedItem) // Return the saved Item object as JSON
                           .build();
        } catch (Exception e) {
        	e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error processing item: " + e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/property/{propertyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getItemsByPropertyId(@PathParam("propertyId") Long propertyId) {
        Property property = propertyComponent.getPropertyById(propertyId)
            .orElseThrow(() -> new RuntimeException("Property not found"));
        return itemComponent.findByProperty(property);
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id") Long id, ItemDTO itemDTO) {
        try {
            System.out.println("Received ItemDTO for update: " + itemDTO);
            Item updatedItem = itemComponent.updateItem(id, itemDTO);
            return Response.ok(updatedItem).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error updating item: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") Long id) {
        try {
            Optional<Item> itemOpt = itemComponent.getItemById(id);
            if (itemOpt.isPresent()) {
                Item item = itemOpt.get();
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setId(item.getId());
                itemDTO.setName(item.getName());
                itemDTO.setItem_description(item.getDescription());
                itemDTO.setPrice(item.getPrice());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setCategory(item.getCategory());
                itemDTO.setPropertyId(item.getProperty());  // Make sure property is being set
                return Response.ok(itemDTO).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                         .entity("Error retrieving item: " + e.getMessage())
                         .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteItem(@PathParam("id") Long id) {
        try {
            itemComponent.deleteItem(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error deleting item: " + e.getMessage())
                .build();
    }
}
}
