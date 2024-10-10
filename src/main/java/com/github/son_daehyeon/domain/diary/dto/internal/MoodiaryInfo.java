package com.github.son_daehyeon.domain.diary.dto.internal;

public record MoodiaryInfo(

	Emotion emotion,
	String feedback
) {

	public enum Emotion {
		HAPPY,
		SAD,
		ANGRY,
		SURPRISED,
		NEUTRAL
	}
}
