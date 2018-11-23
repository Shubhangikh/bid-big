package com.piggymetrics.auction.service;

import com.piggymetrics.auction.domain.AuctionRequest;
import com.piggymetrics.auction.domain.TimeSlot;
import com.piggymetrics.auction.domain.Auction;
import com.piggymetrics.auction.domain.DateRange;

import com.piggymetrics.auction.repository.AuctionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class AuctionServiceImpl implements AuctionService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private AuctionRepository auctionRepository;

	// @Autowired
	// private RecipientService recipientService;

	// @Autowired
	// private EmailService emailService;

	@Autowired
	private Environment env;	

	// @Override
	// @Scheduled(cron = "${backup.cron}")
	// public void sendBackupNotifications() {

	// 	final NotificationType type = NotificationType.BACKUP;

	// 	List<Recipient> recipients = recipientService.findReadyToNotify(type);
	// 	log.info("found {} recipients for backup notification", recipients.size());

	// 	recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
	// 		try {
	// 			String attachment = client.getAccount(recipient.getAccountName());
	// 			emailService.send(type, recipient, attachment);
	// 			recipientService.markNotified(type, recipient);
	// 		} catch (Throwable t) {
	// 			log.error("an error during backup notification for {}", recipient, t);
	// 		}
	// 	}));
	// }

	// @Override
	// @Scheduled(cron = "${remind.cron}")
	// public void sendRemindNotifications() {

	// 	final NotificationType type = NotificationType.REMIND;

	// 	List<Recipient> recipients = recipientService.findReadyToNotify(type);
	// 	log.info("found {} recipients for remind notification", recipients.size());

	// 	recipients.forEach(recipient -> CompletableFuture.runAsync(() -> {
	// 		try {
	// 			emailService.send(type, recipient, null);
	// 			recipientService.markNotified(type, recipient);
	// 		} catch (Throwable t) {
	// 			log.error("an error during remind notification for {}", recipient, t);
	// 		}
	// 	}));
	// }

	@Override
	public List<Auction> listAuctions(DateRange request) {
		return auctionRepository.findAllByAuctionDateBetween(request.getStartDate(), request.getEndDate());
	}

	@Override
	public void updateAuction(Auction request) {
		auctionRepository.save(request);
	}	

	@Override
	public void createAuctions(AuctionRequest request) {

		Date auctionDate = request.getAuctionDate();
		DateTime startTime = request.getStartTime();
		DateTime endTime = request.getEndTime();
		int slots = request.getSlots();

		ArrayList<TimeSlot> auctionSlots = getSlots(startTime, endTime, slots);

		for(TimeSlot slot : auctionSlots) {
			System.out.println(slot.getStartTime());
			Auction auction = new Auction();
			auction.setStartTime(slot.getStartTime());
			auction.setEndTime(slot.getEndTime());
			auction.setAuctionDate(new Date());
			auctionRepository.save(auction);
		}

		// NotificationType notif = request.getType();
		// String url = request.getUrl();

		// Recipient recipient = request.getRecipient();

		// final String subject = env.getProperty(notif.getSubject());
		// final String text = MessageFormat.format(env.getProperty(notif.getText()), url);

		// try {
		// 	emailService.sendEmail(subject, text, recipient);
		// } catch(Throwable t) {
		// 	log.error("an error during reset notification for {}", recipient, t);
		// 	return "Failed";
		// }

		// return "Success";

	}

	private ArrayList<TimeSlot> getSlots(DateTime startTime, DateTime endTime, int slotC) {
		ArrayList<TimeSlot> slots = new ArrayList<>();

		long interval = (long)(endTime.getMillis() - startTime.getMillis())/slotC;
		
		for (long start = startTime.getMillis(); start+interval <= endTime.getMillis(); start += interval) {
			TimeSlot slot = new TimeSlot();
			slot.setStartTime(new DateTime(start));
			slot.setEndTime(new DateTime(start+interval));
			slots.add(slot);
		  }

		return slots;
	}
}
