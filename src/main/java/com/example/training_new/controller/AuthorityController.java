package com.example.training_new.controller;

import com.example.training_new.domain.Authority;
import com.example.training_new.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/authority")
public class AuthorityController {
    private final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public @ResponseBody Iterable<Authority> getAllAuthorities(){
        log.info("Getting all Authorities");
        return authorityService.getAllAuthorities();
    }

    @PostMapping(path = "/create")
    public @ResponseBody String createAuthority(@Valid @RequestBody Authority authority){
        log.info("Creating authority");
        authorityService.createAuthority(authority);
        return "Saved authority";
    }

    @PutMapping(value = "/update/{username}")
    public @ResponseBody String updateAuthority(@Valid @RequestBody Authority authority, @PathVariable String username){
        log.info("Finding authority has username = " + username);
        if (authorityService.checkIfAuthorityHasExited(username)){
            authorityService.updateAuthority(username,authority);
            return "Update authority successfully";
        }
        return username + "dose not exit";
    }

    @DeleteMapping(path = "/delete/{username}")
    public @ResponseBody String deleteAuthority(@PathVariable String username){
        log.info("Finding authority has username = " + username);
        if (authorityService.checkIfAuthorityHasExited(username)){
            authorityService.deleteAuthority(username);
            log.info("Delete authority has username = {} successfully", username);
            return "Delete authority successfully";
        }
        else {
            return "Delete Authority failed";
        }
    }

}
