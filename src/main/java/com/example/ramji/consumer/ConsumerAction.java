/**
 * 
 */
package com.example.ramji.consumer;

import java.io.IOException;
import java.time.Duration;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ramji.IKafkaConstants;
import com.example.ramji.dto.Message;
import com.example.ramji.util.MessageMarshaling;

/**
 * @author HP
 *
 */
@Component
public class ConsumerAction {
	
	@Autowired
	private MessageMarshaling marshaling;
	
	public void consumeMessage(Consumer<Long, String> consumer) throws IOException{
		int noMessageFound = 0;
        while (true) {
          //ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
        	ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
          // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
          if (consumerRecords.count() == 0) {
              noMessageFound++;
              if (noMessageFound > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                // If no message found count is reached to threshold exit loop.  
                break;
              else
                  continue;
          }
          //print each record. 
          consumerRecords.forEach(record -> {
              System.out.println("Record Key " + record.key());
              String value = record.value();
              System.out.println("Record value " + value);
              Message message = new Message();
			try {
				message = marshaling.unmarshalMessage(value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              System.out.println("Name in value found is +++++++++++"+ message.getName());
              //System.out.println("Record partition " + record.partition());
              System.out.println("Record offset " + record.offset());
           });
          // commits the offset of record to broker. 
           consumer.commitAsync();
        }
    consumer.close();
    }
	

}
