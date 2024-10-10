package com.github.son_daehyeon.domain.auth.service;

import org.springframework.stereotype.Service;

import com.github.son_daehyeon.common.property.KakaoProperty;
import com.github.son_daehyeon.common.security.jwt.JwtUtil;
import com.github.son_daehyeon.domain.auth.dto.internal.KakaoUserInfo;
import com.github.son_daehyeon.domain.auth.dto.request.LoginRequest;
import com.github.son_daehyeon.domain.auth.dto.response.LoginResponse;
import com.github.son_daehyeon.domain.auth.dto.response.MyInfoResponse;
import com.github.son_daehyeon.domain.user.repository.UserRepository;
import com.github.son_daehyeon.domain.user.schema.User;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.json.JSONObject;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final KakaoProperty kakaoProperty;

	private final JwtUtil jwtUtil;

	public LoginResponse login(LoginRequest dto) {

		String kakaoCode = dto.kakaoCode();

		String kakaoAccessToken = getKakaoAccessToken(kakaoCode);
		KakaoUserInfo kakaoUserInfo = getKakaoUserInfo(kakaoAccessToken);

		long kakaoId = kakaoUserInfo.id();

		User user = userRepository.findByKakaoId(kakaoId)
								  .orElseGet(() -> userRepository.save(User.builder().kakaoId(kakaoId).nickname(kakaoUserInfo.nickname()).build()));

		String token = jwtUtil.generateToken(user);

		return LoginResponse.builder()
							.token(token)
							.build();
	}

	private String getKakaoAccessToken(String kakaoCode) {

		HttpResponse<?> response = Unirest.post("https://kauth.kakao.com/oauth/token")
										  .header("Content-Type", "application/x-www-form-urlencoded")
										  .field("grant_type", "authorization_code")
										  .field("client_id", kakaoProperty.getClientId())
										  .field("redirect_uri", kakaoProperty.getRedirectUri())
										  .field("code", kakaoCode)
										  .asJson();

		return ((JsonNode) response.getBody()).getObject().getString("access_token");
	}

	private KakaoUserInfo getKakaoUserInfo(String accessToken) {

		HttpResponse<?> response = Unirest.get("https://kapi.kakao.com/v2/user/me")
										  .header("Authorization", "Bearer " + accessToken)
										  .asJson();

		JSONObject json = ((JsonNode)response.getBody()).getObject();

		return KakaoUserInfo.builder()
							.id(json.getLong("id"))
							.nickname(json.getJSONObject("properties").getString("nickname"))
							.build();
	}

	public MyInfoResponse me(User user) {

		return MyInfoResponse.builder()
							 .user(user)
							 .build();
	}
}
