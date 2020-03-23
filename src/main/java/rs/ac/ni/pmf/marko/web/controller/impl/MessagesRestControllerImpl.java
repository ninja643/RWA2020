package rs.ac.ni.pmf.marko.web.controller.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import rs.ac.ni.pmf.marko.web.controller.MessagesRestController;
import rs.ac.ni.pmf.marko.web.model.api.MessageDTO;

@RestController
public class MessagesRestControllerImpl implements MessagesRestController {

	@Override
	public List<MessageDTO> getMessages(int ticketId) {
		return Arrays.asList(MessageDTO.builder().id(1)
				.ticketId(ticketId)
				.title("Message").content("Message content")
				.build());
	}

	@Override
	public List<MessageDTO> getMessage(int ticketId, int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented, yet");
	}

	@Override
	public MessageDTO saveMessage(int ticketId, MessageDTO message) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented, yet");
	}

	@Override
	public void deleteMessage(int ticketId, int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented, yet");
	}

}
