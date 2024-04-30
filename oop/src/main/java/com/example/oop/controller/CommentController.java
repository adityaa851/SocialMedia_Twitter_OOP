package com.example.oop.controller;

import com.example.oop.model.Comment;
import com.example.oop.model.CommentDTO;
import com.example.oop.model.Post;
import com.example.oop.service.CommentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public String createComment(@RequestBody Comment comment) {
        String commentBody = comment.getCommentBody();
        int postID = comment.getPostID();
        int userID = comment.getUserID();
        return commentService.createComment(commentBody, postID, userID);
    }

    @GetMapping("/comment")
    public ResponseEntity<Object> viewComment(@RequestParam int commentID) {
        CommentDTO commentDTO = commentService.viewComment(commentID);
        if (commentDTO == null) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("Error", "Comment does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse.toString());
        } else {
            return ResponseEntity.ok(commentDTO);
        }
    }

    @PatchMapping("/comment")
    public String editComment(@RequestBody Comment comment) {
        String commentBody = comment.getCommentBody();
        int commentID = comment.getCommentID();
        return commentService.editComment(commentID, commentBody);
    }

    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam int commentID) {
        return commentService.deleteComment(commentID);
    }

}
