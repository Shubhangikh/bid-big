package com.piggymetrics.auction.service;

import com.piggymetrics.auction.domain.*;
import java.util.List;

public interface AuctionService {

	void createAuctions(AuctionRequest request);

	List<Auction> listAuctions(DateRange request);
}
