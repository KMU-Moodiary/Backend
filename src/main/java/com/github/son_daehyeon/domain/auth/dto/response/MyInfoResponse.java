package com.github.son_daehyeon.domain.auth.dto.response;

import com.github.son_daehyeon.domain.user.schema.User;

import lombok.Builder;

@Builder
public record MyInfoResponse(

	User user
) {
}
