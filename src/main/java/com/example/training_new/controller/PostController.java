package com.example.training_new.controller;

import com.example.training_new.domain.Post;
import com.example.training_new.exception.BusinessException;
import com.example.training_new.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/getAll")
    public Iterable<Post> getAllPosts() {
        log.info("Getting all Posts");
        return postService.getAllPosts();
    }

    @PostMapping(path = "/create")
    public Post createPost(@Valid @RequestBody Post post) {
        log.info("Creating new post");
        try {
            return postService.createPost(post);
        } catch (Exception ex){
            log.warn("Create post failed, exception: {}",ex.getMessage());
            throw new BusinessException("Create post failed");
        }

    }

    @PutMapping(path = "/update/{id}")
    public Post updatePost(@Valid @RequestBody Post post, @PathVariable int id) {
        log.info("finding post has id = {}", id);
        if (postService.checkIfPostHasExited(id)) {
            return postService.updatePost(post, id);
        }
        log.info("Can't not find Post has id = {}", id);
        throw new BusinessException("Update post failed");
    }

    @GetMapping(value = "getByEmail/{email}")
    public List<Post> getPostsOfAuthorSearchByEmail(@PathVariable String email) {
        log.info("Searching post");
        return postService.getPostsOfAuthorSearchByEmail(email);
    }

    @GetMapping(path = "/getTop10Newest")
    public List<Post> get10PostLatest() {
        log.info("Getting 10 post newest");
        return postService.getTop10PostsNewest();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePost(@PathVariable Integer id) {
        log.info("Finding post has id = " + id);
        if (postService.checkIfPostHasExited(id)) {
            postService.deletePost(id);
            return;
        }
        log.info("Can't find post has id = {}", id);
        throw new BusinessException("400 Bad request", "Delete post failed");

    }
}
