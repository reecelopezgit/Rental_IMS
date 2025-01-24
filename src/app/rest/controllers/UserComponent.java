package app.rest.controllers;

import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import app.entities.User;
import app.repositories.ItemRepository;
import app.repositories.UserRepository;


@Component
public class UserComponent {

    @Autowired
    private UserRepository userRepository;


   public String hashPassword(String plainPassword) {
       return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
   }

   /**
    * Validates the user by comparing the provided credentials
    * with the stored credentials in the database.
    *
    * @param userDTO the user credentials provided for validation
    * @return true if the credentials match, false otherwise
    */
    public boolean validateUser(UserDTO userDTO) {


        
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {

            return false;
        }
    
    
        // Check if the password matches
        boolean isPasswordMatch = BCrypt.checkpw(userDTO.getPassword(), user.getPassword());
   
    
        return isPasswordMatch;
    }

   /**
    * Simulates fetching a user from the database.
    * Replace this with actual database queries.
    *
    * @param username the username to search for
    * @return the User object if found, or null if not found
    */
    public User getUserFromDatabase(String username) {
        User user = userRepository.findByUsername(username);
        return user != null ? user : null; // Explicitly check for null
    }

   /**
    * Registers a new user by hashing their password and saving them to the database.
    * Replace the placeholder logic with actual database integration.
    *
    * @param userDTO the new user details
    * @return true if the user was successfully registered, false otherwise
    */
   public boolean registerUser(UserDTO userDTO) {
       // Hash the password before saving
       User existingUser = userRepository.findByUsername(userDTO.getUsername());
       if (existingUser !=null) {
           return false; // Username already exists
       }

       // Create a new user entity
       User newUser = new User();
       newUser.setUsername(userDTO.getUsername());
       newUser.setPassword(hashPassword(userDTO.getPassword())); // Hash the password

       // Save the new user to the database
       userRepository.save(newUser);
       return true;
   }

   /**
    * Simulates saving a user to the database.
    * Replace this with actual database integration.
    *
    * @param userDTO the user details to save
    * @return true if the user was saved successfully, false otherwise
    */
   private boolean saveUserToDatabase(UserDTO userDTO) {
       // Placeholder logic for saving the user
       System.out.println("User saved: " + userDTO.getUsername());
       return true;
   }

}
