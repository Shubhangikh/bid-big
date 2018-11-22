package com.piggymetrics.auction.controller;

import javax.validation.Valid;

import com.piggymetrics.auction.domain.AuctionRequest;
import com.piggymetrics.auction.service.AuctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public void createAuction(@Valid @RequestBody AuctionRequest request) {
		auctionService.createAuctions( request);
	}
	
}
