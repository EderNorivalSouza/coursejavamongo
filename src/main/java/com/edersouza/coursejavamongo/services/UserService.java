package com.edersouza.coursejavamongo.services;

import com.edersouza.coursejavamongo.domain.UserEntity;
import com.edersouza.coursejavamongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

}
