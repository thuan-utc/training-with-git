package com.example.training_new.repository;

import com.example.training_new.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Integer> {
    @Query(value = "Select p from Post p join Author a on p.author = a.username where a.email = ?1")
    List<Post> findPostsOfAuthorSearchByEmail(String email);

    //@Query(value = "Select top(10) with ties * from Post order by date desc", nativeQuery = true)
    @Query(value = "SELECT * FROM Post ORDER BY date desc limit 10", nativeQuery = true)
    List<Post> findTop10Lastest();

}
