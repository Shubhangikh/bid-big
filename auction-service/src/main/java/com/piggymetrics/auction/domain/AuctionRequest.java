package com.piggymetrics.auction.domain;

import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import java.util.Date;

public class AuctionRequest {

	@NotNull
	private Date auctionDate;

	@NotNull
    private DateTime startTime;

    @NotNull
    private DateTime endTime;

    @NotNull
    private int slots;

    public void setAuctionDate(Date auctionDate){
        this.auctionDate = auctionDate;
    }

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setStartTime(DateTime startTime){
        this.startTime = startTime;
    }

    public DateTime getStartTime() {
        return startTime;
    }
    
    public void setEndDate(DateTime endTime){
        this.endTime = endTime;
    }

    public DateTime getEndDate() {
        return endTime;
    }    

    public void setSlots(int slots){
        this.slots = slots;
    }

    public int getSlots() {
        return slots;
    }

}
