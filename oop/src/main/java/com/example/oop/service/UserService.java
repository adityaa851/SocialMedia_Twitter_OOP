package com.example.oop.service;

import com.example.oop.model.SeeUser;

public interface UserService {
    String login(String email, String password);
    String signup(String email, String password, String name);

    SeeUser getUserDetails(int userID);
}
