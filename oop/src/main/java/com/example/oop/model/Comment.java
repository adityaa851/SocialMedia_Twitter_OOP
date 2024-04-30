package com.example.oop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    @Column(name = "commentBody")
    private String commentBody;

    @Column(name = "postID")
    private int postID;

    @Column(name = "userID")
    private int userID;


    public Comment() {
    }

    public Comment(String commentBody, int postID, int userID) {
        this.commentBody = commentBody;
        this.postID = postID;
        this.userID = userID;
    }

    // Getters and setters
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int id) {
        this.commentID = id;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
