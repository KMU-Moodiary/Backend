package com.github.son_daehyeon.domain.diary.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.son_daehyeon.domain.diary.dto.internal.MoodiaryInfo;
import com.github.son_daehyeon.domain.diary.dto.request.CreateDiaryRequest;
import com.github.son_daehyeon.domain.diary.dto.response.CreateDiaryResponse;
import com.github.son_daehyeon.domain.diary.dto.response.GetDiariesResponse;
import com.github.son_daehyeon.domain.diary.dto.response.GetDiaryResponse;
import com.github.son_daehyeon.domain.diary.exception.AlreadyWriteDiaryException;
import com.github.son_daehyeon.domain.diary.repository.DiaryRepository;
import com.github.son_daehyeon.domain.diary.schema.Diary;
import com.github.son_daehyeon.domain.user.schema.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final VertexAiGeminiChatModel model;
	private final ObjectMapper mapper;

	public GetDiaryResponse getDiary(String id) {

		Diary diary = diaryRepository.findById(id).orElseThrow();

		return GetDiaryResponse.builder()
							   .diary(diary)
							   .build();
	}

	public GetDiariesResponse getDiaries(User user) {

		List<Diary> diaries = diaryRepository.findAllByAuthor(user).stream()
											 .peek(diary -> diary.setContent(null))
											 .peek(diary -> diary.setFeedback(null))
											 .toList();

		return GetDiariesResponse.builder()
								 .diaries(diaries)
								 .build();
	}

	public CreateDiaryResponse createDiary(User user, CreateDiaryRequest dto) {

		String content = dto.content();

		LocalDate today = LocalDate.now();

		if (diaryRepository.findAllByAuthor(user).stream().anyMatch(diary -> diary.getCreatedAt().toLocalDate().equals(today))) {

			throw new AlreadyWriteDiaryException();
		}

		MoodiaryInfo moodiaryInfo = generateMoodiaryInfo(content);

		MoodiaryInfo.Emotion emotion = moodiaryInfo.emotion();
		String feedback = moodiaryInfo.feedback();

		Diary diary = Diary.builder()
						   .author(user)
						   .content(content)
						   .emotion(emotion)
						   .feedback(feedback)
						   .build();

		diary = diaryRepository.save(diary);

		return CreateDiaryResponse.builder()
								  .diary(diary)
								  .build();
	}

	private MoodiaryInfo generateMoodiaryInfo(String content) {

		try {
			String command = "아래 일기의 감정을 HAPPY, SAD, ANGRY, SURPRISED, NEUTRAL 중 하나로 분류하고 너가 이 일기장의 주인의 친구라고 생각하고 피드백을 해줘. 출력은 json으로 해주고, key는 emotion, feedback이고, json을 minify해서 답변해줘.\n{content}";
			PromptTemplate template = new PromptTemplate(command);
			template.add("content", content);
			Prompt prompt = template.create();

			ChatResponse call = model.call(prompt);
			String response = call.getResult().getOutput().getContent();

			return mapper.readValue(response, MoodiaryInfo.class);
		} catch (JsonProcessingException e) {
			return generateMoodiaryInfo(content);
		}
	}
}
