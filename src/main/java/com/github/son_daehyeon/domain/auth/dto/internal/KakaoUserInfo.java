package com.github.son_daehyeon.domain.auth.dto.internal;

import lombok.Builder;

@Builder
public record KakaoUserInfo(

	long id,
	String nickname
) {
}