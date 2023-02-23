package com.example.training_new.service;

import com.example.training_new.domain.Author;
import com.example.training_new.exception.BusinessException;
import com.example.training_new.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final Logger log = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Boolean checkIfUserHasExited(String username) {
        log.info("check if {} has exited", username);
        if (authorRepository.existsById(username)) {
            log.info("{} has exited", username);
            return true;
        } else {
            return false;
        }
    }

    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author createAuthor(Author author) {
        try {
            authorRepository.save(author);
            log.info("Saved author has username = {}", author.getUsername());
            Optional<Author> newAuthor = authorRepository.findById(author.getUsername());
            if (newAuthor.isPresent())
                return newAuthor.get();
        } catch (Exception ex) {
            log.info("Can't save author has username = {}, Exception: {}", author.getUsername(), ex.getMessage());
            throw new BusinessException("Create author failed");
        }
        throw new BusinessException("Create author failed");
    }

    public Author updateAuthor(Author author, String username) {
        Optional<Author> oldAuthor = authorRepository.findById(username);
        if (oldAuthor.isPresent()) {
            oldAuthor.get().setAdded(author.getAdded());
            oldAuthor.get().setDob(author.getDob());
            oldAuthor.get().setEmail(author.getEmail());
            oldAuthor.get().setPassword(author.getPassword());
            oldAuthor.get().setFirstName(author.getFirstName());
            oldAuthor.get().setLastName(author.getLastName());
            log.info("Updating author");
            authorRepository.save(oldAuthor.get());
            Optional<Author> updatedAuthor = authorRepository.findById(username);
            if (updatedAuthor.isPresent())
                return updatedAuthor.get();
        }
        throw new BusinessException("Update author failed");
    }

    public void deleteAuthor(String username) {
        if (authorRepository.existsById(username)) {
            authorRepository.deleteById(username);
            return;
        }
        throw new BusinessException("Delete author failed");
    }


}
