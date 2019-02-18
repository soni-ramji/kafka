package com.example.ramji.util;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.example.ramji.dto.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDeserializer implements Deserializer<Message> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public Message deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		Message object = null;
		try {
			object = mapper.readValue(data, Message.class);
		} catch (Exception exception) {
			System.out.println("Error in deserializing bytes " + exception);
		}
		return object;
	}

	@Override
	public void close() {
	}
}
