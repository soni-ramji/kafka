/**
 * 
 */
package com.example.ramji.util;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.example.ramji.dto.Message;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hduser
 *
 */
@Component
public class MessageMarshaling {
	
	public String marshalMessage(Long id, String name) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		Message message = new Message();
		message.setId(id);
		message.setName(name);
		String messageAsString = objectMapper.writeValueAsString(message);
		System.out.println("Message to send is "+ messageAsString);
		return messageAsString;
	}
	
	public Message unmarshalMessage(String message) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		Message returnValue = objectMapper.readValue(message, Message.class);
		return returnValue;
	}

}
