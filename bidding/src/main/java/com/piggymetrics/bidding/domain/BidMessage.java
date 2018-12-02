package com.piggymetrics.bidding.domain;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class BidMessage implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("userId")
    private int userId;
	
	@JsonProperty("auctionId")
    private int auctionId;
	
	@JsonProperty("itemId")
	private int itemId;

	@JsonProperty("amount")
	private double amount;

    // Default constructor is needed to de-serialize JSON
    public BidMessage() {
    }

    public BidMessage(int userId, int auctionId,  int itemId, double amount) {
        this.userId = userId;
        this.auctionId = auctionId;
		this.itemId = itemId;
		this.amount = amount;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAuctionId() {
		return auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}	

	// @Override
	// public String toString() {
	// 	return "CrawlSupplierData [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierUrl=" + supplierUrl + "]";
	// }

}