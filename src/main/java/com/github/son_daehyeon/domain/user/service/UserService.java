package com.github.son_daehyeon.domain.user.service;

import org.springframework.stereotype.Service;

import com.github.son_daehyeon.domain.user.dto.request.ChangeInfoRequest;
import com.github.son_daehyeon.domain.user.repository.UserRepository;
import com.github.son_daehyeon.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void changeInfo(User user, ChangeInfoRequest dto) {

		String nickname = dto.nickname();

		user.setNickname(nickname);

		userRepository.save(user);
	}
}
