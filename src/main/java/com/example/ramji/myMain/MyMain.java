/**
 * 
 */
package com.example.ramji.myMain;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.example.ramji.IKafkaConstants;
import com.example.ramji.producer.MessageProducerCreator;


/**
 * @author HP
 *
 */
public class MyMain {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyMain myMain = new MyMain();
		
		Producer<Long, String> producer = MessageProducerCreator.createProducer();
		myMain.sendRecord(producer);
		
	}*/
	
	public void  sendRecord(Producer<Long, String> producer){
		for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
            "This is record " + index);
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
    }
	

}
