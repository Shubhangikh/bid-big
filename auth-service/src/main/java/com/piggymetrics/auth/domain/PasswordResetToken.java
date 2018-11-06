package com.piggymetrics.auth.domain;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PasswordResetToken {

	@NotNull
	private String token;

	@NotNull
	private Date expiry;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}
}
