package com.example.oop.model;

public class CommentDTO {
    private int commentID;
    private String commentBody;
    private CommentCreator commentCreator;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(CommentCreator commentCreator) {
        this.commentCreator = commentCreator;
    }
}
