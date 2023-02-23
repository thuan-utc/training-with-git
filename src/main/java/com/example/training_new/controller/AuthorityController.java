package com.example.training_new.controller;

import com.example.training_new.domain.Authority;
import com.example.training_new.exception.BusinessException;
import com.example.training_new.service.AuthorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/authority")
public class AuthorityController {
    private final Logger log = LoggerFactory.getLogger(AuthorityController.class);
    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping(value = "/getAll")
    public Iterable<Authority> getAllAuthorities() {
        log.info("Getting all Authorities");
        return authorityService.getAllAuthorities();
    }

    @PostMapping(path = "/create")
    public Authority createAuthority(@Valid @RequestBody Authority authority) {
        try {
            log.info("Creating authority");
            return authorityService.createAuthority(authority);
        } catch (Exception ex) {
            log.warn("Create authority failed, exception: {}", ex.getMessage());
            throw new BusinessException("Create authority failed");
        }
    }

    @PutMapping(value = "/update/{username}")
    public Authority updateAuthority(@Valid @RequestBody Authority authority, @PathVariable String username) {
        log.info("Finding authority has username = " + username);
        if (authorityService.checkIfAuthorityHasExited(username)) {
            return authorityService.updateAuthority(username, authority);
        }
        log.info("Can't not find authority has username = {}", username);
        throw new BusinessException("Update authority failed");
    }

    @DeleteMapping(path = "/delete/{username}")
    public void deleteAuthority(@PathVariable String username) {
        log.info("Finding authority has username = " + username);
        if (authorityService.checkIfAuthorityHasExited(username)) {
            authorityService.deleteAuthority(username);
            log.info("Delete authority has username = {} successfully", username);
            return;
        }
        log.info("Can't find authority has username = {}", username);
        throw new BusinessException("400 Bad request", "Delete authority failed");
    }

}
