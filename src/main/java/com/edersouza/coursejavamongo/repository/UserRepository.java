package com.edersouza.coursejavamongo.repository;

import com.edersouza.coursejavamongo.domain.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}
