package com.github.son_daehyeon.domain.diary.schema;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.github.son_daehyeon.common.schema.BaseSchema;
import com.github.son_daehyeon.domain.diary.dto.internal.MoodiaryInfo;
import com.github.son_daehyeon.domain.user.schema.User;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class Diary extends BaseSchema {

	@DBRef
	User author;

	String content;

	MoodiaryInfo.Emotion emotion;
	String feedback;
}

