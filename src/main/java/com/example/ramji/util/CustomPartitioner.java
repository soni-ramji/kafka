/**
 * 
 */
package com.example.ramji.util;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

/**
 * @author HP
 *
 */
public class CustomPartitioner implements Partitioner{
	
	private static final int PARTITION_COUNT=50;

	@Override
	public void configure(Map<String, ?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int partition(String topicName, Object key, byte[] keyBytes, Object value,
			byte[] valueBytes, Cluster cluster) {
		// TODO Auto-generated method stub
		Integer keyInt=Integer.parseInt(key.toString());
		return keyInt % PARTITION_COUNT;
	}

}
