package com.piggymetrics.bidding.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import com.piggymetrics.bidding.domain.BidMessage;

import com.piggymetrics.bidding.service.BidService;


@Service
public class MessageListener {

	private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
	
	@Autowired
	private BidService bidService;
   
    @RabbitListener(queues = "${exchange.messageQueue}")
    public void receiveMessageForBid(final BidMessage msg) {
    	log.info("Received message: {} from msg queue.", msg);
		bidService.persistBid(msg);
    	// try {
    	// 	log.info("Making REST call to the API");
    	// 	//TODO: Code to make REST call
        // 	log.info("<< Exiting receiveMessageForApp1() after API call.");
    	// } catch(HttpClientErrorException  ex) {
    		
    	// 	if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
        // 		log.info("Delay...");
        // 		try {
    	// 			Thread.sleep(ApplicationConstant.MESSAGE_RETRY_DELAY);
    	// 		} catch (InterruptedException e) { }
    			
    	// 		log.info("Throwing exception so that message will be requed in the queue.");
    	// 		// Note: Typically Application specific exception should be thrown below
    	// 		throw new RuntimeException();
    	// 	} else {
    	// 		throw new AmqpRejectAndDontRequeueException(ex); 
    	// 	}
    		
    	// } catch(Exception e) {
    	// 	log.error("Internal server error occurred in API call. Bypassing message requeue {}", e);
    	// 	throw new AmqpRejectAndDontRequeueException(e); 
    	// }

    }



}