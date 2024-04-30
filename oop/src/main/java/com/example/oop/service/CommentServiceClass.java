package com.example.oop.service;

import com.example.oop.model.*;
import com.example.oop.repository.CommentRepository;
import com.example.oop.repository.PostRepository;
import com.example.oop.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceClass implements CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public String createComment(String commentBody, int postID, int userID) {
        Post post = postRepository.findById(postID).orElse(null);
        User user = userRepository.findById(userID).orElse(null);
        if (post != null && user != null) {
            Comment comment = new Comment(commentBody, postID, userID);
            commentRepository.save(comment);
            return "Comment created successfully";
        }

        else if (user != null ) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "Post does not exist");
            return errorJson.toString();
        }

        else {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "User does not exist");
            return errorJson.toString();
        }

    }

    @Override
    public CommentDTO viewComment(int commentID) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if (comment == null) {
            return null;
        }
        User user = userRepository.findById(comment.getUserID()).orElse(null);
        if (user == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentID(comment.getCommentID());
        commentDTO.setCommentBody(comment.getCommentBody());

        CommentCreator creator = new CommentCreator();
        creator.setUserID(user.getUserID());
        creator.setName(user.getName());

        commentDTO.setCommentCreator(creator);

        return commentDTO;
    }

    @Override
    public String editComment(int commentID, String commentBody) {
        Comment comment = commentRepository.findById(commentID).orElse(null);
        if (comment == null) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "Comment does not exist");
            return errorJson.toString();
        }

        comment.setCommentBody(commentBody);
        this.commentRepository.save(comment);
        return "Comment edited successfully";
    }

    @Override
    public String deleteComment(int commentID) {
        if (!commentRepository.existsById(commentID)) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "Comment does not exist");
            return errorJson.toString();
        }

        // Delete the comment
        commentRepository.deleteById(commentID);
        return "Comment deleted";
    }


}
