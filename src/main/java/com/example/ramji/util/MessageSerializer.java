package com.example.ramji.util;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.example.ramji.dto.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageSerializer implements Serializer<Message> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public byte[] serialize(String topic, Message data) {
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(data).getBytes();
		} catch (Exception exception) {
			System.out.println("Error in serializing object" + data);
		}
		return retVal;
	}

	@Override
	public void close() {
	}
	
}