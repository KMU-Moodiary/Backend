package com.github.son_daehyeon.domain.user.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.son_daehyeon.domain.user.schema.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByKakaoId(long kakaoId);
}
