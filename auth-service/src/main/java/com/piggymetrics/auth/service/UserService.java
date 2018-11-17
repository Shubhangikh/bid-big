package com.piggymetrics.auth.service;

import com.piggymetrics.auth.domain.ResetPassword;
import com.piggymetrics.auth.domain.User;

public interface UserService {

	void create(User user);

	void resetPassword(User user);

	void resettedPassword(ResetPassword resetPassword);
}
