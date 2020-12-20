package com.edersouza.coursejavamongo.resources;

import com.edersouza.coursejavamongo.domain.UserEntity;
import com.edersouza.coursejavamongo.dto.UserDTO;
import com.edersouza.coursejavamongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserEntity> list = userService.findAll();
//       "map(item->new UserDTO(item))" its the same as "map(UserDTO::new)"
        List<UserDTO> dtoList = list.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        UserEntity userEntity = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(userEntity));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.fromDTO(userDTO);
        UserEntity user = userService.insert(userEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}