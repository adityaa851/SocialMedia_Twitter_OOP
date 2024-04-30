package com.example.oop.service;

import com.example.oop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.oop.repository.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.JSONObject;


@Service
public class PostServiceClass implements PostService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public String makePost(int userID, String postBody) {
        User user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "User does not exist");
            return errorJson.toString();
        }

        Post post = new Post();
        post.setPostBody(postBody);
        post.setUserID(userID, userRepository);
        this.postRepository.save(post);

        return "Post created successfully";
    }

    @Override
    public String editPost(int id, String postBody) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "Post does not exist");
            return errorJson.toString();
        }
        post.setPostBody(postBody);
        this.postRepository.save(post);
        return "Post edited successfully";
    }

    @Override
    public String deletePost(int postID) {
        if (!postRepository.existsById(postID)) {
            JSONObject errorJson = new JSONObject();
            errorJson.put("Error", "Post does not exist");
            return errorJson.toString();
        }

        // Delete the post
        postRepository.deleteById(postID);
        return "Post deleted";
    }

    @Override
    public PostShow getPost(int postID) {
        Post post = postRepository.findById(postID).orElse(null);

        if (post != null) {

            List<Comment> comments = commentRepository.findBypostID(postID);

            // Construct the response object
            PostShow postShow = new PostShow();
            postShow.setPostID(postID);
            postShow.setPostBody(post.getPostBody());
            postShow.setDate(post.getDate());

            List<CommentDTO> commentDTOs = comments.stream().map(comment -> {
                CommentDTO dto = new CommentDTO();
                dto.setCommentID(comment.getCommentID());
                dto.setCommentBody(comment.getCommentBody());

                // Retrieve the comment creator from the database
                User user = userRepository.findById(comment.getUserID()).orElse(null);
                CommentCreator creator = new CommentCreator();
                creator.setUserID(comment.getUserID());
                creator.setName(user.getName());

                dto.setCommentCreator(creator);

                return dto;
            }).collect(Collectors.toList());

            postShow.setComments(commentDTOs);
            return postShow;

        }
        else {
            return null; // Post does not exist
        }
    }

//    @Override
//    public List<PostShow> getAllPosts() {
//        List<PostShow> allPosts = new ArrayList<>();
//
//        // Retrieve the list of unique postIDs in descending order
//        List<Integer> postIDs = postRepository.findDistinctPostIDByOrderByPostIDDesc()
//                .stream()
//                .map(Post::getPostID)
//                .collect(Collectors.toList());
//        // Iterate over each postID
//        for (Integer postID : postIDs) {
//            allPosts.add(getPost(postID));
//        }
//
//        return allPosts;
//    }

    @Override
    public Stream<PostShow> getAllPosts() {
        // Retrieve the list of unique postIDs in descending order
        List<Integer> postIDs = postRepository.findDistinctPostIDByOrderByPostIDDesc()
                .stream()
                .map(Post::getPostID)
                .collect(Collectors.toList());

        // Return a stream of PostShow objects
        return postIDs.stream().map(this::getPost);
    }



}
