package com.example.training_new.service;

import com.example.training_new.domain.Authority;
import com.example.training_new.repository.AuthorityRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceTest {
    @InjectMocks
    AuthorityService authorityService;

    @Mock
    AuthorityRepository authorityRepository;

    @Before
    public void innit(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthorities() {
        List<Authority> listAuthorities = new ArrayList<>();
        listAuthorities.add(new Authority("john","admin"));
        listAuthorities.add(new Authority("anna","user"));

        when(authorityRepository.findAll()).thenReturn(listAuthorities);

        Iterable<Authority> iterableAuthority = authorityService.getAllAuthorities();
        List<Authority> empListAuthority = new ArrayList<>();
        iterableAuthority.forEach(empListAuthority::add);

        Assertions.assertEquals(2, empListAuthority.size());
        verify(authorityRepository,times(1)).findAll();
    }

    @Test
    void createAuthority() {
    }

    @Test
    void deleteAuthority() {
    }

    @Test
    void checkIfAuthorityHasExited() {
    }

    @Test
    void updateAuthority() {
    }
}