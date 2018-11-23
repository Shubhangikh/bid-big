package com.piggymetrics.auction.controller;

import javax.validation.Valid;

import com.piggymetrics.auction.domain.AuctionRequest;
import com.piggymetrics.auction.domain.DateRange;
import com.piggymetrics.auction.domain.Auction;
import com.piggymetrics.auction.service.AuctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public void createAuction(@Valid @RequestBody AuctionRequest request) {
		auctionService.createAuctions( request);
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public List<Auction> listAuction(@Valid @RequestBody DateRange request) {
		return auctionService.listAuctions( request);
	}

	@RequestMapping(path = "/update", method = RequestMethod.POST)
	public void updateAuction(@Valid @RequestBody Auction request) {
		auctionService.updateAuction( request);
	}	
}
