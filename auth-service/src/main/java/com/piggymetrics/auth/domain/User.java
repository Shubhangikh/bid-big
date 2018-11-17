package com.piggymetrics.auth.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.validator.constraints.Email;

import java.util.List;

@Document(collection = "users")
public class User implements UserDetails {

	@Id
	private String username;

	private String password;

	private PasswordResetToken token;

	@Email
	private String email;

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setToken(PasswordResetToken token) {
		this.token = token;
	}

	public PasswordResetToken getToken() {
		return token;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
