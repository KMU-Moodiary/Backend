package com.github.son_daehyeon.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangeInfoRequest(

	@NotBlank
	String nickname
) {
}
