package com.example.training_new.controller;

import com.example.training_new.domain.Author;
import com.example.training_new.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/author")
public class AuthorController {

    private final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody Iterable<Author> getAllAuthors(){
        log.info("Getting all Authors");
         return authorService.getAllAuthors();
    }

    @PutMapping(path = "/create")
    public @ResponseBody String createAuthor(@Valid @RequestBody Author author){
        if (!authorService.checkIfUserHasExited(author.getUsername())){
            log.info("Saving author has username = {}", author.getUsername());
            authorService.createAuthor(author);
            return "Saved author successfully";
        }
        else {
            return "Create author failed";
        }

    }

    @PutMapping(path = "/update/{username}")
    public @ResponseBody String updateAuthor(@Valid @RequestBody Author author, @PathVariable String username){
        if(authorService.checkIfUserHasExited(username)){
            authorService.updateAuthor(author,username);
            log.info("Update Author successfully");
            return "Update author successfully";
        }
        else{
            log.info("{} dose not exit",username);
            return username + "dose not exit";
        }
    }

    @DeleteMapping(path = "/delete/{username}")
    public @ResponseBody String deleteAuthor(@PathVariable String username){
        if(authorService.checkIfUserHasExited(username)){
            authorService.deleteAuthor(username);
            log.info("Delete Author successfully");
            return "Delete author successfully";
        }
        else{
            log.info("Delete author failed, {} dose not exit",username);
            return username + "dose not exit";
        }
    }

}
