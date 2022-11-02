package com.example.training_new.service;

import com.example.training_new.domain.Post;
import com.example.training_new.repository.PostRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    @Before
    public void innit() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPosts() {
        List<Post> listPosts = new ArrayList<>();
        listPosts.add(new Post("David","Dog","about dog","Dog activity", LocalDate.of(2020,2,2)));
        listPosts.add(new Post("David","Cat","about cat","Cat activity", LocalDate.of(2020,2,2)));

        when(postRepository.findAll()).thenReturn(listPosts);

        Iterable<Post> iterablePost = postService.getAllPosts();
        List<Post> empListPost = new ArrayList<>();
        iterablePost.forEach(empListPost::add);

        Assertions.assertEquals(2, empListPost.size());
        verify(postRepository,times(1)).findAll();

    }

    @Test
    void createPost() {
    }

    @Test
    void checkIfPostHasExited() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void getPostsOfAuthorSearchByEmail() {
    }

    @Test
    void getTop10PostsNewest() {
    }

    @Test
    void deletePost() {
    }
}