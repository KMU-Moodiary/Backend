package com.github.son_daehyeon.domain.diary.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.son_daehyeon.common.api.dto.response.ApiResponse;
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

	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<GetDiariesResponse> getDiaries(@AuthenticationPrincipal User user) {

		return ApiResponse.ok(diaryService.getDiaries(user));
	}

	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<GetDiaryResponse> getDiary(@PathVariable("id") String id) {

		return ApiResponse.ok(diaryService.getDiary(id));
	}

	@PostMapping("/create")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<CreateDiaryResponse> createDiary(@AuthenticationPrincipal User user, @RequestBody @Valid CreateDiaryRequest request) {

		return ApiResponse.ok(diaryService.createDiary(user, request));
	}

	@PatchMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<CreateDiaryResponse> updateDiary(@PathVariable("id") String id, @RequestBody @Valid CreateDiaryRequest request) {

		return ApiResponse.ok(diaryService.updateDiary(id, request));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ApiResponse<Void> deleteDiary(@PathVariable("id") String id) {

		diaryService.deleteDiary(id);

		return ApiResponse.ok();
	}
}
