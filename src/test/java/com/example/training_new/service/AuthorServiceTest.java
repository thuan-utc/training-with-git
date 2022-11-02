package com.example.training_new.service;

import com.example.training_new.domain.Author;
import com.example.training_new.repository.AuthorRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {
    @InjectMocks
    AuthorService authorService;

    @Mock
    AuthorRepository authorRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthors() {
        List<Author> listAuthors = new ArrayList<>();
        listAuthors.add(new Author("john", "123", "john", "Dev", "john@123",
                LocalDate.of(2002, 10, 10), Instant.parse("2020-10-10")));
        listAuthors.add(new Author("ana", "123", "ana", "Dev", "ana@123",
                LocalDate.of(2002, 11, 11), Instant.parse("2020-10-10")));
        listAuthors.add(new Author("Mary", "123", "Mary", "Dev", "Mary@123",
                LocalDate.of(2002, 11, 11), Instant.parse("2016-10-01T11:25:30Z")));

        when(authorRepository.findAll()).thenReturn(listAuthors);

        Iterable<Author> iterableAuthor = authorService.getAllAuthors();
        List<Author> empListAuthors = new ArrayList<>();
        iterableAuthor.forEach(empListAuthors::add);

        Assertions.assertEquals(3, empListAuthors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void checkIfUserHasExited() {

    }

    @Test
    void createAuthor() {
    }

    @Test
    void updateAuthor() {
    }

    @Test
    void deleteAuthor() {
    }
}