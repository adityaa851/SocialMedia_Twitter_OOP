package com.example.oop.repository;
import com.example.oop.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findDistinctPostIDByOrderByPostIDDesc();
}
