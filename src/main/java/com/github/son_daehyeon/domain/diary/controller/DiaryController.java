package com.github.son_daehyeon.domain.diary.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.son_daehyeon.common.api.dto.response.ApiResponse;
import com.github.son_daehyeon.common.security.util.UserContext;
import com.github.son_daehyeon.domain.diary.dto.request.CreateDiaryRequest;
import com.github.son_daehyeon.domain.diary.dto.response.CreateDiaryResponse;
import com.github.son_daehyeon.domain.diary.dto.response.GetDiariesResponse;
import com.github.son_daehyeon.domain.diary.dto.response.GetDiaryResponse;
import com.github.son_daehyeon.domain.diary.service.DiaryService;
import com.github.son_daehyeon.domain.user.schema.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;

	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<GetDiaryResponse> getDiary(@PathVariable("id") String id) {

		return ApiResponse.ok(diaryService.getDiary(id));
	}

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<GetDiariesResponse> getDiary() {

		User user = UserContext.getUser();

		return ApiResponse.ok(diaryService.getDiaries(user));
	}

	@PostMapping("/create")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<CreateDiaryResponse> createDiary(@RequestBody @Valid CreateDiaryRequest request) {

		User user = UserContext.getUser();

		return ApiResponse.ok(diaryService.createDiary(user, request));
	}
}
