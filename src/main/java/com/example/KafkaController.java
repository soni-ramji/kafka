/**
 * 
 */
package com.example;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ramji.consumer.ConsumerAction;
import com.example.ramji.consumer.MessageConsumerCreator;
import com.example.ramji.producer.MessageProducerCreator;
import com.example.ramji.producer.ProducerAction;
import com.fasterxml.jackson.core.JsonProcessingException;


/**
 * @author HP
 *
 */

@RestController
public class KafkaController {
	
	@Autowired
	private MessageProducerCreator producer;
	
	@Autowired
	private ProducerAction producerAction;
	
	@Autowired
	private MessageConsumerCreator consumer;
	
	@Autowired
	private ConsumerAction consumerAction;
	
	@GetMapping("send")
	public void sendA() throws JsonProcessingException{
		producerAction.sendRecord(producer.createProducer());
	}
	
	@GetMapping("recieve")
	public void recieve() throws IOException{
		consumerAction.consumeMessage(consumer.createConsumer());
	}

}
