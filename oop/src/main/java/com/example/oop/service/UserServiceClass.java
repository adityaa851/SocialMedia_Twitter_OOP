package com.example.oop.service;

import com.example.oop.model.SeeUser;
import com.example.oop.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oop.repository.*;

@Service
public class UserServiceClass implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "User does not exist");
            return errorJson.toString();
        }
        if (!user.getPassword().equals(password)) {
            // return "Username/Password Incorrect";
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "Username/Password Incorrect");
            return errorJson.toString();

        }
        return "Login Successful";
    }

    @Override
    public String signup(String email, String password, String name) {
        if (userRepository.findByEmail(email) != null) {
            // return "Forbidden, Account already exists";
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "Forbidden, Account already exists");
            return errorJson.toString();
        }

        else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPassword(password);
            userRepository.save(newUser);
            return "Account creation Successful";
        }
    }

    @Override
    public SeeUser getUserDetails(int userID) {
        User user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            return null;
        }

        else {
            SeeUser userDTO = new SeeUser();
            userDTO.setName(user.getName());
            userDTO.setUserID(user.getUserID());
            userDTO.setEmail(user.getEmail());
            return userDTO;
        }
    }


}
