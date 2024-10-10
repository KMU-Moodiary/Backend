package com.github.son_daehyeon.common.security.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.github.son_daehyeon.domain.user.schema.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class UserAuthentication implements Authentication {

	private final User user;

	@Getter
	@Setter
	private boolean authenticated = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(() -> "ROLE_USER");
	}

	@Override
	public String getCredentials() {

		return null;
	}

	@Override
	public Object getDetails() {

		return null;
	}

	@Override
	public User getPrincipal() {

		return user;
	}

	@Override
	public String getName() {

		return user.getId();
	}
}
