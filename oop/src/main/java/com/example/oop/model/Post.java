package com.example.oop.model;
import com.example.oop.model.User;
import com.example.oop.repository.UserRepository;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private int postID;

    @Column(name = "postBody")
    private String postBody;


    @Column(name = "userID")
    private int userID;

    @Column(name = "Date")
    private LocalDate date;

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getUserID() {
        return userID;
    }


    public void setUserID(int userId, UserRepository userRepository) {

        this.userID = userId;
    }


    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public Post() {
        this.date = LocalDate.now(); // Set the date to the current date
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
