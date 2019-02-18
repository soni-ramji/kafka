/**
 * 
 */
package com.example.ramji.producer;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ramji.IKafkaConstants;
import com.example.ramji.dto.Message;
import com.example.ramji.util.MessageMarshaling;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author HP
 *
 */
@Component
public class ProducerAction {
	
	@Autowired
	private MessageMarshaling marshaling;

	public boolean sendRecord(Producer<Long, String> producer) throws JsonProcessingException{
		boolean sent = true;
		//Producer<Long, String> producer= MessageProducerCreator.createProducer();
		for (int index = 0; index < 5; index++) {
			String message = marshaling.marshalMessage(new Long(index), "MyMessage"+index);
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
            message);
            try {
            RecordMetadata metadata = producer.send(record).get();
            
            System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
                 } 
            catch (ExecutionException e) {
                     System.out.println("Error in sending record");
                     System.out.println(e);
                  } 
             catch (InterruptedException e) {
                      System.out.println("Error in sending record");
                      System.out.println(e);
                  }
         }
    
		return sent;
	}
}
