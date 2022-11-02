package com.example.training_new.controller;

import com.example.training_new.domain.Post;
import com.example.training_new.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody Iterable<Post> getAllPosts(){
        log.info("Getting all Posts");
        return postService.getAllPosts();
    }

    @PostMapping(path = "/create")
    public @ResponseBody String createPost(@Valid @RequestBody Post post){
        log.info("Creating new post");
        postService.createPost(post);
        return "Saved post";
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody String updatePost(@Valid @RequestBody Post post, @PathVariable int id){
        log.info("finding post has id = {}", id);
        if(postService.checkIfPostHasExited(id)){
            postService.updatePost(post,id);
            return "Update post successfully";
        }
        return (id + " dose not exit");
    }

    @GetMapping(value = "getByEmail/{email}")
    public @ResponseBody List<Post> getPostsOfAuthorSearchByEmail(@PathVariable String email){
        log.info("Searching post");
        return postService.getPostsOfAuthorSearchByEmail(email);
    }

    @GetMapping(path = "/getTop10Newest")
    public @ResponseBody List<Post> get10PostLatest(){
        log.info("Getting 10 post newest");
        return postService.getTop10PostsNewest();
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody String deletePost(@PathVariable Integer id){
        log.info("Finding post has id = " + id);
        if (postService.checkIfPostHasExited(id)){
            postService.deletePost(id);
            return "Delete successfully";
        }
        else{
            return "There is no post has id = " + id;
        }

    }
}
