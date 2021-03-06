package com.edersouza.coursejavamongo.resources;

import com.edersouza.coursejavamongo.domain.Post;
import com.edersouza.coursejavamongo.resources.util.URL;
import com.edersouza.coursejavamongo.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",
            defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> post = postService.findByTitle(text);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String min,
            @RequestParam(value = "maxDate", defaultValue = "") String max) {
        text = URL.decodeParam(text);
        Date dateMin = URL.convertDate(min, new Date(0L));
        Date dateMax = URL.convertDate(max, new Date());

        List<Post> post = postService.fullSearch(text, dateMin, dateMax);
        return ResponseEntity.ok().body(post);
    }
}