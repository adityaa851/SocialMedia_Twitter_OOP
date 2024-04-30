package com.example.oop.service;

import com.example.oop.model.PostShow;

import java.util.List;
import java.util.stream.Stream;

public interface PostService {
    String makePost(int userID, String postBody);

    String editPost(int id, String postBody);

    String deletePost(int postID);

    PostShow getPost(int postID);

    Stream<PostShow> getAllPosts();
}
