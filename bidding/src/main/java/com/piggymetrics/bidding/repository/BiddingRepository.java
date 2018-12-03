package com.piggymetrics.bidding.repository;

import com.piggymetrics.bidding.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingRepository extends JpaRepository<Bid, Long> {

    public List<Bid> findByItemId(int id);

    public Bid findTopByAuctionIdOrderByAmountDesc(int auctionId);

}
