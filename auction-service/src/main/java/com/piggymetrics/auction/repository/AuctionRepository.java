package com.piggymetrics.auction.repository;

import com.piggymetrics.auction.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllByAuctionDateBetween(
      Date auctionDateStartTime,
      Date auctionDateEndTime);

}
