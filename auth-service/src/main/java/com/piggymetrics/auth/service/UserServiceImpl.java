package com.piggymetrics.auth.service;

import com.piggymetrics.auth.domain.ResetPassword;
import com.piggymetrics.auth.domain.ResetRequest;
import com.piggymetrics.auth.domain.User;
import com.piggymetrics.auth.domain.NotificationType;
import com.piggymetrics.auth.domain.PasswordResetToken;
import com.piggymetrics.auth.repository.UserRepository;
import com.piggymetrics.auth.domain.Recipient;
import com.piggymetrics.auth.client.NotificationServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.UUID;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	@Autowired
	private Environment environment;

	@Autowired
	private NotificationServiceClient notificationClient;

	@Override
	public void create(User user) {

		Optional<User> existing = repository.findById(user.getUsername());

		existing.ifPresent(it-> {throw new IllegalArgumentException("user already exists: " + it.getUsername());});

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);

		repository.save(user);

		log.info("new user has been created: {}", user.getUsername());
	}

	@Override
	public void resetPassword(User user) {

		User userr = repository.findById(user.getUsername()).get();
		System.out.println(userr.getUsername());
		if(userr == null) {
			throw new IllegalArgumentException("user doesn't exists: " + user.getUsername());
		}
		String domain = environment.getProperty("domain");

		String token = UUID.randomUUID().toString();
		String urlString = domain + "uaa/" + "resetted/" + token;

		PasswordResetToken tokenObj = new PasswordResetToken();
		tokenObj.setExpiry(new Date());
		tokenObj.setToken(token);

		NotificationType notifType = NotificationType.RESET;

		Recipient recepient = new Recipient();

		recepient.setAccountName(user.getUsername());
		recepient.setEmail(user.getEmail());

		ResetRequest req = new ResetRequest();

		req.setType(notifType);
		req.setRecipient(recepient);
		req.setUrl(urlString);

		notificationClient.sendPasswordReset(req);

		user.setToken(tokenObj);

		repository.save(user);

		log.info("reset email has been sent: {}", user.getUsername());
	}

	@Override
	public void resettedPassword(ResetPassword resetPassword) {
 
		Optional<User> existing = repository.findById(resetPassword.getUsername());
		User user = existing.orElse(null);
		if(user == null) {
			throw new IllegalArgumentException("user doesn't exists: ");
		}

		if(!user.getToken().getToken().equals(resetPassword.getToken())) {
			throw new IllegalArgumentException("invalid token: " + resetPassword.getToken());
		}

		if(user.getToken().getExpiry().getTime() < System.currentTimeMillis()) {
			throw new IllegalArgumentException("token expired: " + resetPassword.getToken());
		}

		
		String hash = encoder.encode(resetPassword.getPassword());
		user.setPassword(hash);

		repository.save(user);

		log.info("user password has been updated: {}", user.getUsername());
	}		
}
