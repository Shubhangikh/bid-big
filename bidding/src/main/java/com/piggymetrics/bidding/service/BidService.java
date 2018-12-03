package com.piggymetrics.bidding.service;

import com.piggymetrics.bidding.domain.*;

import java.util.List;

public interface BidService {

	void createBid(BidMessage bid);

	void persistBid(BidMessage bid);

	List<Bid> getBidsByItemId(int itemId);

	double findMaxBidByAuctionId(int auctionId);
}
