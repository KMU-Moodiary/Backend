package com.github.son_daehyeon.domain.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.son_daehyeon.common.api.dto.response.ApiResponse;
import com.github.son_daehyeon.common.security.util.UserContext;
import com.github.son_daehyeon.domain.user.dto.request.ChangeInfoRequest;
import com.github.son_daehyeon.domain.user.schema.User;
import com.github.son_daehyeon.domain.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PutMapping("/my/info")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<Void> changeInfo(@RequestBody @Valid ChangeInfoRequest request) {

		User user = UserContext.getUser();

		userService.changeInfo(user, request);

		return ApiResponse.ok();
	}
}
