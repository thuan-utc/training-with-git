package com.example.training_new.service;

import com.example.training_new.domain.Authority;
import com.example.training_new.repository.AuthorityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {
    private final Logger log = LoggerFactory.getLogger(AuthorityService.class);
    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Iterable<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    public void createAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    public void deleteAuthority(String username) {
        if (authorityRepository.existsById(username)) {
            authorityRepository.deleteById(username);
            log.info("Delete authority has username = {} successfully", username);
        } else {
            log.info("Delete failed, {} dose not exit", username);
        }

    }

    public Boolean checkIfAuthorityHasExited(String username) {
        if (authorityRepository.existsById(username)) {
            log.info("{} has exited", username);
            return true;
        } else {
            log.info("{} dose not exit", username);
            return false;
        }
    }

    public void updateAuthority(String username, Authority authority) {
        Optional<Authority> oldAuthority = authorityRepository.findById(username);
        if (oldAuthority.isPresent()) {
            oldAuthority.get().setAuthority(authority.getAuthority());
            log.info("Updating authority");
            authorityRepository.save(oldAuthority.get());
        } else {
            log.info("update failed,Authority has username = {} dose not exit", username);
        }
    }
}
