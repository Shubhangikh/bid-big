package com.piggymetrics.auction.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    private Long itemId;
    
    @Column
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@NotNull
	private DateTime startTime;
	
    @Column
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	@NotNull
    private DateTime endTime;	

	@Column
	@NotNull
    private Date auctionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}	

	public Date getAuctionDate() {
		return auctionDate;
	}

	public void setAuctionDate(Date auctionDate) {
		this.auctionDate = auctionDate;
	}
}
