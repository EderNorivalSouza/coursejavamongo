package com.edersouza.coursejavamongo.services;

import com.edersouza.coursejavamongo.domain.UserEntity;
import com.edersouza.coursejavamongo.dto.UserDTO;
import com.edersouza.coursejavamongo.repository.UserRepository;
import com.edersouza.coursejavamongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(String id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntity.orElseThrow(
                () -> new ObjectNotFoundException("Object not found"));
    }

    public UserEntity insert(UserEntity user) {
        return userRepository.insert(user);
    }

    public UserEntity fromDTO(UserDTO userDTO) {
        return new UserEntity(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
