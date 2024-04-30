package com.example.oop.model;

import java.time.LocalDate;
import java.util.List;

public class PostShow {
    private int postID;
    private String postBody;
    private LocalDate date;
    private List<CommentDTO> comments;


    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public LocalDate getDate() {
        return date;
    }
}
