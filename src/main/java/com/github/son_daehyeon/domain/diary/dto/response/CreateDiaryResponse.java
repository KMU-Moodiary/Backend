package com.github.son_daehyeon.domain.diary.dto.response;

import com.github.son_daehyeon.domain.diary.schema.Diary;

import lombok.Builder;

@Builder
public record CreateDiaryResponse(

	Diary diary
) {
}
