package com.example.oop.controller;

import com.example.oop.model.SeeUser;
import com.example.oop.model.User;
import com.example.oop.repository.UserRepository;
import com.example.oop.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        return userService.login(email, password);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        return userService.signup(email, password, name);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam int userID) {
        SeeUser userObj = userService.getUserDetails(userID);
        if (userObj == null) {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("Error", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse.toString());

        } else {
            return ResponseEntity.ok(userObj);
        }
    }

    @GetMapping("/users")
    public List<SeeUser> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private SeeUser mapToDTO(User user) {
        SeeUser userDTO = new SeeUser();
        userDTO.setUserID(user.getUserID());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        return userDTO;
    }


}
