package com.github.son_daehyeon.domain.diary.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateDiaryRequest(

	@NotBlank
	String content
) {
}
