package com.github.son_daehyeon.domain.diary.exception;

import org.springframework.http.HttpStatus;

import com.github.son_daehyeon.common.api.exception.ApiException;

public class AlreadyWriteDiaryException extends ApiException {

	public AlreadyWriteDiaryException() {
		super(HttpStatus.BAD_REQUEST, "오늘 일기는 이미 작성했습니다.");
	}
}
