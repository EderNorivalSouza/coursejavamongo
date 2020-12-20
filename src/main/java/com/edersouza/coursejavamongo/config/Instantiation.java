package com.edersouza.coursejavamongo.config;

import com.edersouza.coursejavamongo.domain.Post;
import com.edersouza.coursejavamongo.domain.User;
import com.edersouza.coursejavamongo.dto.AuthorDTO;
import com.edersouza.coursejavamongo.dto.CommentsDTO;
import com.edersouza.coursejavamongo.repository.PostRepository;
import com.edersouza.coursejavamongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public Instantiation(UserRepository userRepository,
                         PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        postRepository.deleteAll();
        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2019"), "Partiu Viagem",
                "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2019"), "Bom dia",
                "Acordei feliz hoje!", new AuthorDTO(maria));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));

        userRepository.save(maria);

        CommentsDTO comments1 = new CommentsDTO("Boa viagem mano!",
                sdf.parse("21/03/2019"), new AuthorDTO(alex));
        CommentsDTO comments2 = new CommentsDTO("Aproveite",
                sdf.parse("22/03/2019"), new AuthorDTO(bob));
        CommentsDTO comments3 = new CommentsDTO("Tenha um ótimo dia!",
                sdf.parse("24/03/2019"), new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(comments1, comments2));
        post2.getComments().addAll(Arrays.asList(comments3));

        postRepository.saveAll(Arrays.asList(post1, post2));

    }
}
//