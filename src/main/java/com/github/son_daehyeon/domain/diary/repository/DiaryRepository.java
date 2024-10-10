package com.github.son_daehyeon.domain.diary.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.son_daehyeon.domain.diary.schema.Diary;
import com.github.son_daehyeon.domain.user.schema.User;

@Repository
public interface DiaryRepository extends MongoRepository<Diary, String> {

	List<Diary> findAllByAuthor(User author);
}
