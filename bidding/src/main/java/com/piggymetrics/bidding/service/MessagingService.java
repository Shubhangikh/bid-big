package com.piggymetrics.bidding.service;

import com.piggymetrics.bidding.domain.*;

public interface MessagingService {

	void queueBid(BidMessage bid);

}
