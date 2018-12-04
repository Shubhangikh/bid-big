package com.bidbig.auction.service;

import com.bidbig.auction.domain.*;
import java.util.List;

public interface AuctionService {

	void createAuctions(AuctionRequest request);

	List<Auction> listAuctions(DateRange request);

	void updateAuction(Auction request);
}
