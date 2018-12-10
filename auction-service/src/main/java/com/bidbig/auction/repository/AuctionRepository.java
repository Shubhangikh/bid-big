package com.bidbig.auction.repository;

import com.bidbig.auction.domain.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.cache.annotation.Cacheable;
import org.joda.time.DateTime;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Date;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Cacheable("findAllByAuctionDateBetween")
    public List<Auction> findAllByAuctionDateBetween(Date auctionDateStartTime, Date auctionDateEndTime);

    public List<Auction> findAllByAuctionDateBetweenAndItemIdNotNull(Date auctionDateStartTime, Date auctionDateEndTime);

    @CacheEvict(value = "findAllByAuctionDateBetween", allEntries = true)
    public Auction save(Auction auction);

    public Auction findOneByEndTimeGreaterThanAndStartTimeLessThanEqual(DateTime current, DateTime currentLast);

}

