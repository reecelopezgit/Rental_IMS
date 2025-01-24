package app.rest.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import app.entities.Item;
import app.entities.Property;
import app.entities.User;
import app.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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


@Path("/api/users")
public class UserController {

    @Inject
    private UserComponent userComponent;

    @Inject
    private UserRepository userRepository;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(UserDTO userDTO) {
        boolean isUserValid = userComponent.validateUser(userDTO);
        if (isUserValid) {
            String token = UUID.randomUUID().toString();

        // Retrieve the user and set the token
            User user = userRepository.findByUsername(userDTO.getUsername());
            user.setToken(token);
            userRepository.save(user);


                    Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("token", token);
        return Response.ok(response).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"message\": \"Invalid credentials\"}")
                    .build();
        }
    }

    @GET
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(@HeaderParam("Authorization") String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            Map<String, Boolean> response = new HashMap<>();
            response.put("authenticated", true);
            return Response.ok(response).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(UserDTO userDTO) {
        try {
            boolean isRegistered = userComponent.registerUser(userDTO);
            if (isRegistered) {
                return Response.status(Response.Status.CREATED).entity("User registered successfully").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Username already exists").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error registering user: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam("Authorization") String token) {
        if (token == null || token.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Token is required").build();
        }

        User user = userRepository.findByToken(token);
        if (user != null) {
            // Invalidate the session
            user.setToken(null);
            userRepository.save(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Logout successful");
            return Response.ok(response).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build();
    }

    @GET
    @Path("/validate-token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateToken(@HeaderParam("Authorization") String token) {
        if (token == null || token.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token is missing").build();
        }

        User user = userRepository.findByToken(token);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build();
        }

        return Response.ok().entity("Token is valid").build();
    }


}