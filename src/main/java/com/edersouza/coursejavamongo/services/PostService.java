package com.edersouza.coursejavamongo.services;

import com.edersouza.coursejavamongo.domain.Post;
import com.edersouza.coursejavamongo.repository.PostRepository;
import com.edersouza.coursejavamongo.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(
                () -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitle(text);
    }

    public List<Post> fullSearch(String text, Date min, Date max) {
        max = new Date(max.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, min, max);
    }
}
