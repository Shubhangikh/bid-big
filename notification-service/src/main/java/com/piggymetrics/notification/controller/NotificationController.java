package com.piggymetrics.notification.controller;

import com.piggymetrics.notification.domain.Recipient;
import com.piggymetrics.notification.domain.ResetRequest;
import com.piggymetrics.notification.domain.NotificationType;
import com.piggymetrics.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/notif")
public class NotificationController {

	@Autowired
	private NotificationService notifService;

	@RequestMapping(path = "/reset", method = RequestMethod.POST)
	public void sendResetNotification(@Valid @RequestBody ResetRequest request) {
		notifService.sendResetNotification( request);
	}
}
