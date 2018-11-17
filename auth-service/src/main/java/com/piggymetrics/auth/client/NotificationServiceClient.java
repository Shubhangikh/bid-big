package com.piggymetrics.auth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.piggymetrics.auth.domain.*;


@FeignClient(name = "notification-service")
public interface NotificationServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/notifications/notif/reset", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	String sendPasswordReset(ResetRequest req);

}
