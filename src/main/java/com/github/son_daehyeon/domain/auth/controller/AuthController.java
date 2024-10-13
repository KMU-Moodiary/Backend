package com.github.son_daehyeon.domain.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.son_daehyeon.common.api.dto.response.ApiResponse;
import com.github.son_daehyeon.domain.auth.dto.request.LoginRequest;
import com.github.son_daehyeon.domain.auth.dto.response.LoginResponse;
import com.github.son_daehyeon.domain.auth.dto.response.MyInfoResponse;
import com.github.son_daehyeon.domain.auth.service.AuthService;
import com.github.son_daehyeon.domain.user.schema.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

		return ApiResponse.ok(authService.login(request));
	}

	@GetMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<MyInfoResponse> me(@AuthenticationPrincipal User user) {

		return ApiResponse.ok(authService.me(user));
	}
}
