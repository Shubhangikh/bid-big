package com.piggymetrics.notification.service;

import com.piggymetrics.notification.domain.Recipient;

import com.piggymetrics.notification.domain.*;

public interface NotificationService {

	void sendBackupNotifications();

	void sendRemindNotifications();

	void sendResetNotification(ResetRequest request);
}
