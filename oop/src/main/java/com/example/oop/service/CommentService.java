package com.example.oop.service;

import com.example.oop.model.Comment;
import com.example.oop.model.CommentDTO;

import java.util.Optional;

public interface CommentService {
    String createComment(String commentBody, int postID, int userID);

    CommentDTO viewComment(int commentID);

    String editComment(int commentID, String commentBody);

    String deleteComment(int commentID);
}
