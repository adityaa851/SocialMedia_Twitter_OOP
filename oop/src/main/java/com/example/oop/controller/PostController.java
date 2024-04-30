package com.example.oop.controller;

import com.example.oop.model.CommentDTO;
import com.example.oop.model.Post;
import com.example.oop.model.PostShow;
import com.example.oop.model.User;
import com.example.oop.service.PostService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public String makePost(@RequestBody Post post) {
        int id = post.getUserID();
        String postBody = post.getPostBody();
        return postService.makePost(id, postBody);
    }

    @GetMapping("/post")
    public ResponseEntity<Object> getPost(@RequestParam int postID) {
        PostShow postShow = postService.getPost(postID);
        if (postShow == null) {
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("Error", "Post does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonResponse.toString());
        } else {
            return ResponseEntity.ok(postShow);
        }
    }

    @GetMapping("/")
    public Stream<PostShow> getAllPosts() {
        return postService.getAllPosts();
    }

    @PatchMapping("/post")
    public String editPost(@RequestBody Post post) {
        String postBody = post.getPostBody();
        int postID = post.getPostID();
        return postService.editPost(postID, postBody);
    }

    @DeleteMapping("/post")
    public String deletePost(@RequestParam int postID) {
        // Check if the post exists
        return postService.deletePost(postID);
    }



}
