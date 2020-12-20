package com.edersouza.coursejavamongo.repository;

import com.edersouza.coursejavamongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTitleContainingIgnoreCase(String text);

    @Query(value = "{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> findByTitle(String text);
}
