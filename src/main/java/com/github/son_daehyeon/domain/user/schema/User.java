package com.github.son_daehyeon.domain.user.schema;

import org.springframework.data.mongodb.core.index.Indexed;

import com.github.son_daehyeon.common.schema.BaseSchema;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends BaseSchema {

	@Indexed(unique = true)
	long kakaoId;

	String nickname;
}

