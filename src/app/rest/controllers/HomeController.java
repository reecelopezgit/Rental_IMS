package app.rest.controllers;

	import javax.inject.Inject;
	import javax.ws.rs.*;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import app.repositories.ItemRepository;
import app.repositories.PropertyRepository;
import app.repositories.ServiceRepository;

import java.util.HashMap;
	import java.util.Map;

	@Component
	@Path("/home")

	public class HomeController {

	    @Inject
	    private PropertyRepository propertyRepository;

	    @Inject
	    private ItemRepository itemRepository;

	    @Inject
	    private ServiceRepository serviceRequestRepository;

	    // Get summary for the home screen
	    @GET
		@Path("/summary")
		@Produces(MediaType.APPLICATION_JSON)
		
	    public Response getHomeSummary() {
	        Map<String, Object> summary = new HashMap<>();
	        summary.put("totalProperties", propertyRepository.findAll().size());
	        summary.put("totalItems", itemRepository.findAll().size());
	        summary.put("pendingServiceRequests", serviceRequestRepository.findAll().stream()
	                .filter(sr -> "pending".equals(sr.getStatus()))
	                .count());

	        return Response.ok(summary).build();
	    }
	}
