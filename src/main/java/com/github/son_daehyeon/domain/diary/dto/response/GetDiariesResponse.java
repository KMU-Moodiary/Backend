package com.github.son_daehyeon.domain.diary.dto.response;

import java.util.List;

import com.github.son_daehyeon.domain.diary.schema.Diary;

import lombok.Builder;

@Builder
public record GetDiariesResponse(

	List<Diary> diaries
) {
}
